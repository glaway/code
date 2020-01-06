package com.glaway.ids.functionManage.common;

public enum OrgType implements BaseEnum<Integer, String> {
    /**
     * VPM业务组织
     */
    BUSINESS(1,"业务组织"),
    HR(2,"人事组织"),
    PARTY_MASSES(3,"党群组织")
    ;
    private Integer value;
    private String message;
    OrgType(Integer value, String message) {
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
