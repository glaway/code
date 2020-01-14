package com.glaway.ids.functionManage.util;


import org.apache.commons.lang.StringUtils;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class HttpClientPoolUtil {

    private static Logger logger = LoggerFactory.getLogger(HttpClientPoolUtil.class);
    public static PoolingHttpClientConnectionManager cm = null;
    public static CloseableHttpClient httpClient = null;

    private static final String DEFAULT_CONTENT_TYPE = "text/html;charset=UTF-8";

    private static final int DEFAUL_TTIME_OUT = 2;
    private static final int COUNT = 1000;
    private static final int TOTAL_COUNT = 2000;
    private static final int HTTP_DEFAULT_KEEP_TIME = 16;

    public static synchronized void initPools() {
        if (httpClient == null) {
            cm = new PoolingHttpClientConnectionManager();
            cm.setDefaultMaxPerRoute(1000);
            cm.setMaxTotal(2000);
            httpClient = HttpClients.custom().setKeepAliveStrategy(defaultStrategy)
                    .setConnectionManager(cm).setMaxConnTotal(400)
                    .setMaxConnPerRoute(150).evictExpiredConnections().build();
        }
    }

    public static ConnectionKeepAliveStrategy defaultStrategy = new ConnectionKeepAliveStrategy() {
        @Override
        public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
            HeaderElementIterator it = new BasicHeaderElementIterator(response.headerIterator("Keep-Alive"));
            int keepTime = 16;
            while (it.hasNext()) {
                HeaderElement he = it.nextElement();
                String param = he.getName();
                String value = he.getValue();
                if ((value != null) && ("timeout".equalsIgnoreCase(param))) {
                    try {
                        return Long.parseLong(value) * 1000L;
                    } catch (Exception e) {
                        e.printStackTrace();
                        HttpClientPoolUtil.logger.error("format KeepAlive timeout exception, exception:" + e.toString());
                    }
                }
            }
            return keepTime * 1000;
        }
    };

    public static CloseableHttpClient getHttpClient() {
        return httpClient;
    }

    public static PoolingHttpClientConnectionManager getHttpConnectionManager() {
        return cm;
    }

    public static String doPost(String uri, String data, Map<String, String> headerMap, String cookie) {
        long startTime = System.currentTimeMillis();
        HttpEntity httpEntity = null;
        HttpEntityEnclosingRequestBase method = null;
        try {
            if (httpClient == null) {
                initPools();
            }
            method = (HttpEntityEnclosingRequestBase) getRequest(uri, "POST", "application/json;charset=UTF-8", 0, headerMap, cookie);
            method.setEntity(new StringEntity(data,"utf-8"));
            HttpContext context = HttpClientContext.create();
            CloseableHttpResponse httpResponse = httpClient.execute(method, context);
            httpEntity = httpResponse.getEntity();
            return EntityUtils.toString(httpEntity, "UTF-8");
        } catch (Exception e) {
            if (method != null) {
                method.abort();
            }
            e.printStackTrace();
            logger.error("execute post request exception, url:" + uri + ", exception:" + e.toString() + ", cost time(ms):" + (
                    System.currentTimeMillis() - startTime));
        } finally {
            if (httpEntity != null) {
                try {
                    EntityUtils.consumeQuietly(httpEntity);
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error("close response exception, url:" + uri + ", exception:" + e.toString() + ", cost time(ms):" + (
                            System.currentTimeMillis() - startTime));
                }
            }
        }
        return null;
    }

    public static HttpRequestBase getRequest(String uri, String methodName, String contentType, int timeout, Map<String, String> headerMap, String cookie) {
        if (httpClient == null) {
            initPools();
        }
        HttpRequestBase method = null;
        if (timeout <= 0) {
            timeout = 2;
        }

        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeout * 1000).setConnectTimeout(timeout * 1000).setConnectionRequestTimeout(timeout * 1000).setExpectContinueEnabled(false).build();
        if ("PUT".equalsIgnoreCase(methodName)) {
            method = new HttpPut(uri);
        } else if ("POST".equalsIgnoreCase(methodName)) {
            method = new HttpPost(uri);
        } else if ("GET".equalsIgnoreCase(methodName)) {
            method = new HttpGet(uri);
        } else {
            method = new HttpPost(uri);
        }
        if (StringUtils.isBlank(contentType)) {
            contentType = "text/html;charset=UTF-8";
            method.addHeader("Content-Type", contentType);
            method.addHeader("Accept", contentType);
        }
        for (Map.Entry<String, String> e : headerMap.entrySet()) {
            method.addHeader(e.getKey(), e.getValue());
        }
        if (StringUtils.isNotEmpty(cookie)) {
            method.addHeader("cookie", cookie);
        }
        method.setConfig(requestConfig);
        return method;
    }

    public static String doGet(String uri, String cookie, int timeout, Map<String, String> headerMap) {
        long startTime = System.currentTimeMillis();
        HttpEntity httpEntity = null;
        HttpRequestBase method = null;
        String responseBody = "";
        try {
            if (httpClient == null) {
                initPools();
            }
            method = getRequest(uri, "GET", "text/html;charset=UTF-8", timeout, headerMap, cookie);
            HttpContext context = HttpClientContext.create();
            CloseableHttpResponse httpResponse = httpClient.execute(method, context);
            httpEntity = httpResponse.getEntity();
            if (httpEntity != null) {
                responseBody = EntityUtils.toString(httpEntity, "UTF-8");
                logger.info("请求URL: " + uri + "+ 返回状态码：" + httpResponse.getStatusLine().getStatusCode());
            }

            return responseBody;
        } catch (Exception e) {
            if (method != null) {
                method.abort();
            }
            e.printStackTrace();
            logger.error("execute get request exception, url:" + uri + ", exception:" + e.toString() + ",cost time(ms):" + (
                    System.currentTimeMillis() - startTime));
        } finally {
            if (httpEntity != null) {
                try {
                    EntityUtils.consumeQuietly(httpEntity);
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error("close response exception, url:" + uri + ", exception:" + e.toString() + ",cost time(ms):" + (
                            System.currentTimeMillis() - startTime));
                }
            }
        }
        return "";
    }
}
