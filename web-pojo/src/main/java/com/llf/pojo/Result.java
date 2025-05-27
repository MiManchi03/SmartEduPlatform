package com.llf.pojo;




public class Result {
    private Integer code; // 0表示失败，1表示成功
    private String msg;// 提示信息
    private Object data;// 返回的数据
    //下面这三个方法伴随着每一次结果的返回都要用的的，是通用的，所有提前在这里写好，方便使用，别的地方只需要调用即可

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static Result success() {
        //请求成功且不需要给前端页面返回具体数据时调用这个方法
        Result result = new Result();
        result.code = 1;
        result.msg = "操作成功";
        return result;
    }
    public static Result success(Object data) {
        //请求成功且需要给前端页面返回具体数据时调用这个方法
        Result result = new Result();
        result.code = 1;
        result.msg = "操作成功";
        result.data = data;
        return result;
    }
    public static Result error(String msg) {
        //请求失败时调用这个方法
        Result result = new Result();
        result.code = 0;
        result.msg = msg;
        return result;
    }
}
