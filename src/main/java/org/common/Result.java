package org.common;

import lombok.Data;

@Data
public class Result {
    private int code; // 200为成功
    private String msg;
    private Object data;

    // 业务成功
    public static Result success(){
        Result r = new Result();
        r.setCode(200);
        r.setMsg("");
        r.setData(null);
        return r;
    }
    public static Result success(String msg){
        Result r = new Result();
        r.setCode(200);
        r.setMsg(msg);
        r.setData(null);
        return r;
    }
    public static Result success(Object data){
        Result r = new Result();
        r.setCode(200);
        r.setMsg("");
        r.setData(data);
        return r;
    }

    // 业务失败
    public static Result fail(String msg){
        Result r = new Result();
        r.setCode(500);
        r.setMsg(msg);
        r.setData(null);
        return r;
    }
}
