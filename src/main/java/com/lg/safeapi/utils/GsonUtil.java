package com.lg.safeapi.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author 正能量导师
 * @date 2022/6/15-10:01
 * @Description
 */
public class GsonUtil {

    public static String toJson(Object object){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(object);
    }
}
