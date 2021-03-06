package com.aires.kafka.monitor.domain.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by aires on 9/25/16.
 */
public class JsonUtils {
    private JsonUtils() {
    }

    public static <T> String serialize(T object) {
        return JSON.toJSONString(object);
    }

    public static <T> T deserialize(String string, Class<T> clz) {
        return JSON.parseObject(string, clz);
    }


    public static JSONObject obj2JSON(Object object) {
        return (JSONObject) JSON.toJSON(object);
    }
}
