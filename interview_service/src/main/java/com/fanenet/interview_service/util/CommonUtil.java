package com.fanenet.interview_service.util;

import com.alibaba.fastjson.JSONObject;

import java.util.UUID;

/**
 * @version: V1.0
 * @description: 通用工具类
 * @author: Administrator
 **/
public class CommonUtil {

    /**
     * 功能：获取UUID并去除“-”
     */
    public static String getUUID() {

        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("\\-", "");
    }

    /**
     * 设置接口返回信息
     *
     * @param jsonObject
     * @param code
     * @param msg
     * @param data
     * @param count
     */
    public static void setMsgObject(JSONObject jsonObject, int code, String msg,Object data,long count) {
        jsonObject.put("code", code);
        jsonObject.put("count",count);
        jsonObject.put("data", data);
        jsonObject.put("msg", msg);
    }
}
