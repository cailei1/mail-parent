package com.cailei.base.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum StateCodeEnum {

    FAILED(500,"请求失败"),
    SUCCESS(200,"请求成功");

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
