package com.meiya.order.enums;

public enum ResponseCodeEnum {
    SUCCESS("0", "成功"),
    FAIL("1", "失败");
    public String code;
    public String desc;

    ResponseCodeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
