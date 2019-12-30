package com.glaway.ids.functionManage.util;

import com.glaway.ids.functionManage.properties.CommonProperties;

import java.io.*;
import java.io.BufferedReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author HASEE
 */
public class FileUtils {

    /**
     * 更新文本中的数据
     *
     * @param userId    用户id
     * @param userLevel 用户登机
     * @param text      文本内容
     */
    static void addOrUpdateFile(String userId, String userLevel, String text) {
        // 模拟数据库文件路径
        String textPath = CommonProperties.getStringProperty("userAndPwdPath");
        String fileName;
        String textName = "userAndPwdFile";
        fileName = textPath + textName;
        FileWriter fw = null;
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
                fw = new FileWriter(file, true);
                fw.write("\n");
                fw.write(text);
            } else {
                FileModify fileModify = new FileModify();
                // 如果存在,读取修改用户等级
                if (fileModify.ifExitsUser(fileName, userId)) {
                    fileModify.write(fileName, fileModify.readLevel(fileName, userId, userLevel));
                } else {
                    // 如果不存在就写
                    fw = new FileWriter(file, true);
                    fw.write("\n");
                    fw.write(text);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void readFileByLines(String fileName, Map<String, String> map, List<String> datalist) {
        File file = new File(fileName);
        java.io.BufferedReader reader = null;
        try {
            List<String> tempList = new ArrayList<String>();
            InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "GBK");
            reader = new BufferedReader(isr);
            String tempString = null;
            // (针对po导出的文件部分数据一行数据中间null存在的情况)用户读取不到的问题
            if (!"2".equals(map.get("type"))) {
                int i = 0;
                int y = 0;
                String halfString = "";
                do {
                    tempString = reader.readLine();
                    if (tempString != null && !"null".equals(tempString)) {
                        if (y == i && !"".equals(halfString)) {
                            tempList.remove(halfString);
                            tempString = halfString + tempString;
                        }
                        i++;
                        tempList.add(tempString);
                        halfString = tempString;
                    } else {
                        y = i;
                        tempString = "a";
                    }
                } while (!tempString.contains("End of export file"));
                // 遍历数据
                for (String string : tempList) {
                    if (!string.startsWith("//")) {
                        // 用户查询
                        if ("1".equals(map.get("type"))) {
                            if (string.contains("*PERSON")) {
                                if (!"".equals(map.get("viewOrUpdateFullName"))
                                        && map.get("viewOrUpdateFullName") != null
                                        && !"".equals(map.get("viewOrUpdateUserId"))
                                        && map.get("viewOrUpdateUserId") != null) {
                                    if (string.contains(map.get("viewOrUpdateUserId"))
                                            && string.contains(map.get("viewOrUpdateFullName"))) {
                                        datalist.add(string);
                                    }
                                } else if ("".equals(map
                                        .get("viewOrUpdateUserId"))
                                        && map.get("viewOrUpdateUserId") == null
                                        && !"".equals(map
                                        .get("viewOrUpdateFullName"))
                                        && map.get("viewOrUpdateFullName") != null) {
                                    if (string.contains(map
                                            .get("viewOrUpdateFullName"))) {
                                        datalist.add(string);
                                    }
                                } else if (!"".equals(map
                                        .get("viewOrUpdateUserId"))
                                        && map.get("viewOrUpdateUserId") != null
                                        && "".equals(map
                                        .get("viewOrUpdateFullName"))
                                        && map.get("viewOrUpdateFullName") == null) {
                                    if (string.contains(map
                                            .get("viewOrUpdateUserId"))) {
                                        datalist.add(string);
                                    }
                                } else {
                                    datalist.add(string);
                                }
                            }
                            // 权限查询
                        } else if ("3".equals(map.get("type"))) {
                            if (string.contains("*PRIV 1")) {
                                if (!"".equals(map
                                        .get("queryOrUpdatecontextId"))
                                        && map.get("queryOrUpdatecontextId") != null) {
                                    if (string.split(";")[1].contains(map
                                            .get("queryOrUpdatecontextId"))
                                            && string.contains("*PRIV 1")) {
                                        datalist.add(string);
                                    }
                                } else {
                                    datalist.add(string);
                                }
                            }
                        } else if ("4".equals(map.get("type"))) {
                            // 项目查询
                            if (string.contains("*PRJ")) {
                                datalist.add(string);
                            }
                        } else if ("5".equals(map.get("type"))) {
                            // 组织数据查询
                            if (string.contains("*ORG")) {
                                datalist.add(string);
                            }
                        } else if ("6".equals(map.get("type"))) {
                            // 上下文查询
                            if (string.contains("*CTX")) {
                                datalist.add(string);
                            }
                        } else if ("7".equals(map.get("type"))) {
                            // 角色查询
                            if (string.contains("*ROLE")) {
                                datalist.add(string);
                            }
                        }
                    }
                }
            } else {
                // 日志文件数据
                do {
                    tempString = reader.readLine();
                    if (tempString != null && !"null".equals(tempString)) {
                        if (map.get("operationType") == null) {
                            if (tempString.contains("UpdatePerson")) {
                                tempString = tempString.replaceAll(
                                        "UpdatePerson", "用户删除");
                                // 注意，由于并不是真正的从P&O中删除该用户而是修改用户信息，所以删除用户这个操作在VPM的日志中变更为UpdatePerson，因此WEB端在查询日志的时候需要注意。
                            }
                            datalist.add(tempString);
                        } else {
                            if (tempString.contains(map.get("operationType"))) {
                                if ("".equals(map.get("startDate"))
                                        || null == map.get("startDate")) {
                                    datalist.add(tempString);
                                } else {
                                    String[] tempStringArray = tempString
                                            .split("\t");
                                    int startCompare = tempStringArray[0]
                                            .substring(0, 10).compareTo(
                                                    map.get("startDate")
                                                            .toString());
                                    int endCompare = tempStringArray[0]
                                            .substring(0, 10).compareTo(
                                                    map.get("endDate")
                                                            .toString());
                                    if ("添加上下文用户".equals(map
                                            .get("operationType"))
                                            || "删除上下文用户".equals(map
                                            .get("operationType"))) {// 由于aix系统写入日期前俩数字乱码,日期比较无效,web端写人的日志不要求时间限制
                                        datalist.add(tempString);
                                    } else {
                                        if (startCompare >= 0
                                                && endCompare <= 0) {
                                            if ("用户行为审计".equals(map
                                                    .get("operationWay"))) {
                                                tempString = tempString
                                                        .replaceAll("EV5ADM",
                                                                "vpmsysadmin");
                                            } else {
                                                tempString = tempString
                                                        .replaceAll("EV5ADM",
                                                                "vpmsecadmin");
                                                if (tempString
                                                        .contains("UpdatePerson")) {
                                                    tempString = tempString
                                                            .replaceAll(
                                                                    "UpdatePerson",
                                                                    "用户删除");
                                                    // 注意，由于并不是真正的从P&O中删除该用户而是修改用户信息，所以删除用户这个操作在VPM的日志中变更为UpdatePerson，因此WEB端在查询日志的时候需要注意。
                                                }
                                            }
                                            datalist.add(tempString);
                                        }
                                    }
                                }
                            }
                        }

                    }
                } while ((tempString = reader.readLine()) != null);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }


    public static void writeText(String fileName, String text) {
        FileWriter fw = null;
        try {
            File file = new File(fileName);
            boolean isFileExit = file.exists();
            fw = new FileWriter(file, true);
            if (isFileExit){
                fw.write("\n");
            }
            fw.write(text);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
