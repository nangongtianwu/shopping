package com.longyi.shopping.common;

import lombok.Data;

@Data
public class Result {
    private int code;//编码 200成功  400失败
    private String msg;//返回消息提示
    private Long total;//返回记录数
    private Object data;//返回数据

    public static Result fail() {
        return result(400, "失败", 0L, null);
    }

    public static Result fail(String msg) {
        return result(400, msg, 0L, null);
    }

    public static Result suc() {
        return result(200, "成功", 0L, null);
    }

    public static Result suc(Object data) {
        return result(200, "成功", 0L, data);
    }

    public static Result suc(Long total, Object data) {
        return result(200, "成功", total, data);
    }

    public static Result suc(String msg) {
        return result(200, msg, 0L, null);
    }

    private static Result result(int code, String msg, Long total, Object data) {
        Result res = new Result();
        res.setCode(code);
        res.setMsg(msg);
        res.setTotal(total);
        res.setData(data);
        return res;
    }
}