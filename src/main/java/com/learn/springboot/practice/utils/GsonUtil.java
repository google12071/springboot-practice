package com.learn.springboot.practice.utils;


import com.google.common.reflect.TypeToken;
import com.google.gson.*;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author xuechai
 * @date 2019/1/15
 */
public class GsonUtil {
    private static Gson gson = null;

    static {
        if (gson == null) {
            gson = new GsonBuilder()
                    .registerTypeAdapter(java.util.Date.class, new GsonDateSerializer()).setDateFormat(DateFormat.LONG)
                    .registerTypeAdapter(java.util.Date.class, new GsonDateDeserializer()).setDateFormat(DateFormat.LONG)
                    .registerTypeAdapter(new TypeToken<Map<String, Object>>() {}.getType(), new GsonTypeAdapter())
                    .create();
        }
    }

    private GsonUtil() {
    }


    /**
     * 将object对象转成json字符串
     *
     * @param object
     * @return
     */
    public static String toString(Object object) {
        String gsonString = null;
        if (gson != null) {
            gsonString = gson.toJson(object);
        }
        return gsonString;
    }

    /**
     * 将gsonString转成泛型bean
     *
     * @param gsonString
     * @param cls
     * @return
     */
    public static <T> T toBean(String gsonString, Class<T> cls) {
        T t = null;
        if (gson != null) {
            t = gson.fromJson(gsonString, cls);
        }
        return t;
    }

    /**
     * 转成list
     * 解决泛型问题
     *
     * @param json
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> List<T> jsonToList(String json, Class<T> cls) {
        List<T> list = new ArrayList<T>();
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        for (final JsonElement elem : array) {
            list.add(gson.fromJson(elem, cls));
        }
        return list;
    }

    /**
     * json -> map转换
     * @param json
     * @param <T>
     * @return
     */
    public static <T> Map<String, T> jsonToMap(String json) {
        return gson.fromJson(json, new TypeToken<Map<String, Object>>() {}.getType());
    }
}
