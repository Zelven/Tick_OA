package com.qianfeng.vo;

/**
 * @author Zelven
 * @date 2019/4/16/016
 */

//针对非查询操作的数据返回
public class VResult {

    private int code;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public VResult(int code) {
        this.code = code;
    }

    public VResult(String msg) {
        this.msg = msg;
    }

    public VResult(int code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }
    public VResult() {
        super();
    }
    public static VResult setOk(String msg){
        return  new VResult(1000,msg);
    }

    public static  VResult setERROR(String msg){
        return new VResult(2000,msg);
    }
}
