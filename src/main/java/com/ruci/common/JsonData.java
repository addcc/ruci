package com.ruci.common;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
//封装http传过来的json请求
public class JsonData {

    //判断json请求是否已经处理完成
    private boolean ret;
    //状态码
    private Integer code;
    //错误信息
    private String message;
    //后端数据
    private Object data;



    private JsonData(boolean ret) {
        this.ret = ret;
    }

    //返回成功的状态码
    public static JsonData success(){
        JsonData jsonData=new JsonData(true);
        jsonData.code=200;
        return jsonData;
    }

    //返回成功的状态码和数据
    public static JsonData success(Integer code,Object object){
        JsonData jsonData=new JsonData(true);
        jsonData.code=code;
        jsonData.data=object;
        return jsonData;
    }


    //返回失败的数据
    public static JsonData fail(Integer code,String message){
        JsonData jsonData=new JsonData(false);
        jsonData.code=code;
        jsonData.message=message;
        return jsonData;
    }

    public Map<String ,Object> toMap(){
        Map<String ,Object> result=new HashMap<>();
        result.put("ret",ret);
        result.put("message",message);
        result.put("data",data);
        return result;
    }

}
