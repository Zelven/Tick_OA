package com.qianfeng.utils;

public class JsonIp {

    private int code;
    private IpInfo data;
    public void setCode(int code) {
        this.code = code;
    }
    public int getCode() {
        return code;
    }

    public IpInfo getData() {
        return data;
    }

    public void setData(IpInfo data) {
        this.data = data;
    }

    public JsonIp(int code, IpInfo data) {
        this.code = code;
        this.data = data;
    }

    public JsonIp() {}
}
