package com.zsxk.online.common.response;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Result {

    private boolean success;
    private Integer code;
    private String message;
    private Map<String,Object> data = new HashMap<>();



    private Result(){}
    public static Result ok(){
        Result r = new Result();
        r.setSuccess(true);
        r.setCode(StatusVO.success);
        r.setMessage("操作成功");
        return r;
    }
    public static Result error(){
        Result r = new Result();
        r.setSuccess(false);
        r.setCode(StatusVO.err);
        r.setMessage("操作失败");
        return r;
    }
//    public static Result setResult(StatusVO statusVO){
//        Result r = new Result();
//        r.setSuccess(resultCodeEnum.getSuccess());
//        r.setCode(resultCodeEnum.getCode());
//        r.setMessage(resultCodeEnum.getMessage());
//        return r;
//    }
    public Result success(Boolean success){
        this.setSuccess(success);
        return this;
    }
    public Result message(String message){
        this.setMessage(message);
        return this;
    }
    public Result code(Integer code){
        this.setCode(code);
        return this;
    }
    public Result data(String key, Object value){
        this.data.put(key, value);
        return this;
    }
    public Result data(Map<String, Object> map){
        this.setData(map);
        return this;
    }


}
