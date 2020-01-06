package com.glaway.ids.functionManage.common;

/**
 * @author HASEE
 */
public interface BaseEnum<E,T> {
    /**
     * 获取枚举值
     * @return 返回值
     */
    E getValue();

    /**
     * 获取枚举消息
     * @return 返回消息
     */
    T getMessage();
}
