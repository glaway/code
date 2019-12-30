package com.glaway.ids.functionManage.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author HASEE
 */
public class DateUtil {

    public static String getDateString() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    public static String getDateString(String format) {
        return new SimpleDateFormat(format).format(new Date());
    }
}
