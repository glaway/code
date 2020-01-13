package com.glaway.ids.functionManage.util;

import java.io.UnsupportedEncodingException;

public class EncodeUtil {

    public static String getEncoding(String str) {
        String encode = "GB2312";
        try {
            String s = checkString(str, encode);
            if (s != null) return s;
        } catch (Exception exception) {
        }
        encode = "ISO-8859-1";
        try {
            String s1 = checkString(str, encode);
            if (s1 != null) return s1;
        } catch (Exception exception1) {
        }
        encode = "UTF-8";
        try {
            String s2 = checkString(str, encode);
            if (s2 != null) return s2;
        } catch (Exception exception2) {
        }
        encode = "GBK";
        try {
            String s3 = checkString(str, encode);
            if (s3 != null) return s3;
        } catch (Exception exception3) {
        }
        return "";
    }

    private static String checkString(String str, String encode) throws UnsupportedEncodingException {
        if (str.equals(new String(str.getBytes(encode), encode))) {
            String s = new String(str.getBytes(encode), "UTF-8");
            return s;
        }
        return null;
    }


}
