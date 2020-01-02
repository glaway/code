package com.glaway.ids.functionManage.common;

public enum DataType implements BaseEnum<Integer, String> {
    /**
     * 数据类型
     */
    HR(1,"HR"),
    self(2,"self")
    ;
    private Integer value;
    private String message;
    DataType(Integer value, String message) {
        this.value = value;
        this.message = message;
    }

    @Override
    public Integer getValue() {
        return value;
    }


    @Override
    public String getMessage() {
        return message;
    }
}
