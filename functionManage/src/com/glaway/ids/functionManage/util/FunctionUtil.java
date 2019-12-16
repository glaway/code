/*
 * 文件名：FunctionUtil.java
 * 版权：Copyright by www.glaway.com
 * 描述：
 * 修改人：Administrator
 * 修改时间：2018年3月26日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.glaway.ids.functionManage.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;

public class FunctionUtil {

    public static boolean isEmpty(String string) {
        return ((string == null) || (string.isEmpty()));
    }

    public static boolean isEmpty(Object[] array) {
        return ((array == null) || (array.length == 0));
    }

    public static boolean isEmpty(Collection<?> collection) {
        return ((collection == null) || (collection.isEmpty()));
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return ((map == null) || (map.isEmpty()));
    }

    @SuppressWarnings("rawtypes")
    public static boolean isEmpty(Object value) {
        boolean result = true;

        if (value == null) {
            result = true;
        }
        else if (value instanceof String) {
            result = ((String)value).isEmpty();
        }
        else if (value instanceof Collection) {
            result = ((Collection)value).isEmpty();
        }
        else if (value instanceof Map) {
            result = ((Map)value).isEmpty();
        }
        else if (value.getClass().isArray()) {
            result = Array.getLength(value) == 0;
        }
        else {
            result = (value.toString() == null) || (value.toString().isEmpty());
        }

        return result;
    }

    public static boolean isBlank(String string) {
        return ((isEmpty(string)) || (string.trim().isEmpty()));
    }

    public static boolean isNumber(String string) {
        try {
            Long.parseLong(string);
            return true;
        }
        catch (Exception e) {}
        return false;
    }

    public static boolean isDecimal(String string) {
        try {
            Double.parseDouble(string);
            return true;
        }
        catch (Exception e) {}
        return false;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static <E> List<E> copyTo(List<?> source, Class<E> destinationClass)
        throws Exception {
        if (source.size() == 0) {
            return new ArrayList();
        }
        List res = new ArrayList(source.size());
        for (Iterator localIterator = source.iterator(); localIterator.hasNext();) {
            Object o = localIterator.next();
            Object e = destinationClass.newInstance();
            BeanUtils.copyProperties(e, o);
            res.add(e);
        }
        return res;
    }
}
