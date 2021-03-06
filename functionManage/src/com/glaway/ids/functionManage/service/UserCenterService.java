package com.glaway.ids.functionManage.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.glaway.ids.functionManage.common.DataType;
import com.glaway.ids.functionManage.common.OrgType;
import com.glaway.ids.functionManage.properties.CommonProperties;
import com.glaway.ids.functionManage.util.DateUtil;
import com.glaway.ids.functionManage.util.FileUtils;
import com.glaway.ids.functionManage.util.HttpClientPoolUtil;
import com.glaway.ids.functionManage.util.WSCallVpmServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.File;
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
    private static final String ORGANIZATION = "orginaztion";
    private static final String TRUE = "true";


    private static WSCallVpmServices wsCallVpmServices = null;

    public UserCenterService() {
        wsCallVpmServices = new WSCallVpmServices();
    }

    public String getToken() {
        LOGGER.info("获取Token,放入缓存");
        String host = CommonProperties.getStringProperty("tokenHost");
        String path = host + CommonProperties.getStringProperty("tokenPath");
        String accessToken = CommonProperties.getStringProperty("access_token");
        String token = CommonProperties.getStringProperty("token");
        String tenantId = CommonProperties.getStringProperty("tenant_id");
        Map<String, String> headerMap = new HashMap<String, String>(16);
        headerMap.put("access_token", accessToken);
        headerMap.put("Content-type", "application/json");
        headerMap.put("charset", "utf-8");
        Map<String, String> bodyMap = new HashMap<String, String>(16);
        bodyMap.put("token", token);
        bodyMap.put("tenant_id", tenantId);
        String data = JSONObject.toJSONString(bodyMap);
        return HttpClientPoolUtil.doPost(path, data, headerMap, null);
    }

    @SuppressWarnings("unchecked")
    public void getOrg() {
        LOGGER.info("获取组织信息!");
        String orgHost = CommonProperties.getStringProperty("orgHost");
        String path = orgHost + CommonProperties.getStringProperty("orgPath");
        String uuserKey = CommonProperties.getStringProperty("uuserKey");
        Map<String, String> headerMap = new HashMap<String, String>(16);
        String token = getToken();
        LOGGER.info("获取接口Token==>{}",token);
        headerMap.put("access_token", token);
        headerMap.put("Content-type", "application/json");
        Map<String, Object> bodyMap = new HashMap<String, Object>(16);
        bodyMap.put("uuserKey", uuserKey);
        Map<String, String> queryMap = new HashMap<String, String>(16);
        queryMap.put("orgType", OrgType.BUSINESS.getValue().toString());
        queryMap.put("dataType", DataType.HR.getMessage());
        bodyMap.put("query", queryMap);
        String data = JSONObject.toJSONString(bodyMap);
        LOGGER.info("org path url ::: {} --- 参数是: {}", path, data);
        String result = HttpClientPoolUtil.doPost(path, data, headerMap, null);
        LOGGER.info("org path url result ===> {}", result);
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
                        String textName = "userUpdatePO";
                        String fileName = textPath + textName + "_" + DateUtil.getDateString("yyyyMMddHHmmss");
                        LOGGER.info("组织信息 ===> {}",orgList.size());
                        StringBuilder orgLine = new StringBuilder();
                        for (int i = 0; i < orgList.size(); i++) {
                            Map<String,String> orgInfo = orgList.get(i);
                            //读取到的信息，写入到
                            String orgId = orgInfo.get("code");
                            String parentId = orgInfo.get("parentCode");
                            String orgName = orgInfo.get("name");
                            orgLine.append("*ORG ").append(orgId).append(";").append(parentId).append(";$;").append(orgName).append(";$");
                            if(i != orgList.size() - 1) {
                                orgLine.append("\n");
                            } else {
                                orgLine.append("\n// -----------------------------------")
                                        .append("\n// End of export file.")
                                        .append("\n// -----------------------------------");
                            }
                        }
                        FileUtils.writeText(fileName,orgLine.toString());
                        //最后执行导入命令
                        LOGGER.info("组织信息导入VPM系统开始...");
                        if (wsCallVpmServices == null) {
                            wsCallVpmServices = new WSCallVpmServices();
                        }
                        String isUploadVPM = CommonProperties.getStringProperty("isUploadVPM");
                        if(TRUE.equals(isUploadVPM)){
                            wsCallVpmServices.callVpmServices("", fileName);
                        }
                        LOGGER.info("组织信息导入VPM系统完毕!");
                    } else {
                        LOGGER.error("组织信息没有数据！");
                    }
                } else {
                    LOGGER.info("return code  ====> {}", code);
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
            Map<String, String> map = new HashMap<String, String>(16);
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
