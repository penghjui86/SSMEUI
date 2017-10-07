package com.fceg.result;

public class BaseResult {

    public final static int CODE_OK=200;
    public final static int CODE_FAIL=300;

    //业务状态
    protected int code;
    //业务状态消息
    protected String msg;

    //名字不能改变
    private int total;
    private Object rows;

    public BaseResult() {

    }

    public BaseResult(int code, String msg, Object rows) {
        this.code = code;
        this.msg = msg;
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Object getRows() {
        return rows;
    }

    public void setRows(Object rows) {
        this.rows = rows;
    }

    public static int getCodeOk() {
        return CODE_OK;
    }

    public static int getCodeFail() {
        return CODE_FAIL;
    }

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

    public static BaseResult ok(String msg){
        return ok(msg,null);
    }
    public static BaseResult ok(String msg,Object data){
        return new BaseResult(BaseResult.CODE_OK,msg,data);
    }
    public static BaseResult fail(String msg){
        return fail(msg,null);
    }
    public static BaseResult fail(String msg,Object data){
        return new BaseResult(BaseResult.CODE_FAIL,msg,data);
    }
}
