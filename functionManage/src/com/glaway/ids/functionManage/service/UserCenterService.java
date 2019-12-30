package com.glaway.ids.functionManage.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.glaway.ids.functionManage.properties.CommonProperties;
import com.glaway.ids.functionManage.util.FileUtils;
import com.glaway.ids.functionManage.util.HttpClientPoolUtil;
import com.glaway.ids.functionManage.util.WSCallVpmServices;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author HASEE
 */
@Service
public class UserCenterService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserCenterService.class);

    private static final String SUCCESS = "200";
    private static final String FAILED = "500";
    private static final String ORGANIZATION = "orginaztion";


    private static WSCallVpmServices wsCallVpmServices = null;

    public UserCenterService() {
        wsCallVpmServices = new WSCallVpmServices();
    }

    public String getToken() {
        LOGGER.info("获取Token,放入缓存");
        String host = CommonProperties.getStringProperty("userHost");
        String path = host + CommonProperties.getStringProperty("tokenPath");
        Map<String, String> headerMap = new HashMap<String, String>(16);
        headerMap.put("access_token", "d3bfc54f-600d-4d03-88e3-ef0cd4780e02");
        headerMap.put("Content-type", "application/json");
        headerMap.put("charset", "utf-8");
        Map<String, String> bodyMap = new HashMap<String, String>(16);
        bodyMap.put("token", "11b8b684-4f7d-4b93-8107-883337f3b9b5");
        bodyMap.put("tenant_id", "uuser");
        String data = JSONObject.toJSONString(bodyMap);
        return HttpClientPoolUtil.doPost(path, data, headerMap, null);
    }

    @SuppressWarnings("unchecked")
    public void getOrg() {
        LOGGER.info("获取组织信息!");
        String host = CommonProperties.getStringProperty("userHost");
        String path = host + CommonProperties.getStringProperty("orgPath");
        String uuserKey = CommonProperties.getStringProperty("uuserKey");
        Map<String, String> headerMap = new HashMap<String, String>(16);
        String token = getToken();
        headerMap.put("access_token", token);
        headerMap.put("Content-type", "application/json");
        Map<String, String> bodyMap = new HashMap<String, String>();
        bodyMap.put("pageNo", Integer.toString(1));
        bodyMap.put("pageSize", Integer.toString(10));
        bodyMap.put("uuserKey", uuserKey);
        String data = JSONObject.toJSONString(bodyMap);
        LOGGER.info("path url ::: {} --- 参数是: {}", path, data);
        String result = HttpClientPoolUtil.doPost(path, data, headerMap, null);
        Map dataMap = JSON.parseObject(result, Map.class);
        if (dataMap != null && dataMap.size() > 0) {
            Map<String, Object> orgMap = (Map<String, Object>) dataMap.get("data");
            String code = (String) orgMap.get("code");
            String message = (String) orgMap.get("message");
            LOGGER.info("return message  ====> {}", message);
            try {
                if (SUCCESS.equals(code)) {
                    List<Map<String, String>> orgList = (List<Map<String, String>>) orgMap.get("orgs");
                    if (!CollectionUtils.isEmpty(orgList)) {
                        String textPath = CommonProperties.getStringProperty("testFilePath");
                        String textName = "PO_query";
                        String fileName = textPath + textName;
                        if (wsCallVpmServices == null) {
                            wsCallVpmServices = new WSCallVpmServices();
                        }
//                        wsCallVpmServices.callVpmServices("export", fileName);
                        File queryFile = new File(fileName);
                        if (!queryFile.exists()) {
                            Thread.sleep(1000 * 6);
                        }

                        List<Map<String, String>> orgDataList = queryOrgDataByType(ORGANIZATION, queryFile);

                        for (Map<String, String> orgInfo : orgList) {
                            //读取到的信息，写入到
                            String orgId = orgInfo.get("code");
                            String parentId = orgInfo.get("parentCode");
                            String orgName = orgInfo.get("name");
                            LOGGER.info("组织信息 ===> *ORG {};{};$;" + orgName + ";$", orgId, parentId);
                            for (Map<String, String> vpmOrgInfo : orgDataList) {
                                vpmOrgInfo.put(orgId, parentId + ";;;" + orgName);
                            }
                        }
                    } else {
                        LOGGER.error("组织信息没有数据！");
                    }
                }
            } catch (Exception e) {
                LOGGER.error("callVpmServices error :::", e);
                throw new RuntimeException(e);
            }
        } else {
            LOGGER.error("组织信息未返回数据! 入参：{}", data);
        }
    }


    private List<Map<String, String>> queryOrgDataByType(String type, File file) {
        // 用户以及对于的上下文数据
        List<Map<String, String>> orgDataList = new ArrayList<Map<String, String>>();
        if (ORGANIZATION.equals(type)) {
            // 组织数据
            List<String> orgList = new ArrayList<String>();
            Map<String, String> map = new HashMap<String, String>();
            // type为5的话为组织查询
            map.put("type", "5");
            FileUtils.readFileByLines(file.getPath(), map, orgList);
            //0,1,3===组织Id,父组织Id,组织名称
            for (String data : orgList) {
                Map<String, String> mapVo = new HashMap<String, String>(16);
                String[] tempStringArray = data.split(";");
                String temp = tempStringArray[1].trim() + ";;;" + tempStringArray[3];
                mapVo.put(tempStringArray[0].split("\\s+")[1], temp);
                orgDataList.add(mapVo);
            }
        }
        return orgDataList;
    }
}
