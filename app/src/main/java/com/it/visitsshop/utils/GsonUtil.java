package com.it.visitsshop.utils;

import com.google.gson.Gson;

/**
 * Created by he on 17-7-14.
 */

public class GsonUtil {

    private GsonUtil(){};

    private static volatile Gson sInstance = null;

    public static Gson getInstance() {
        if (null == sInstance) {
            synchronized (GsonUtil.class){
                if (null == sInstance) {
                    sInstance = new Gson();
                }
            }
        }
        return sInstance;
    }

    /**
     * 转json
     * @param object
     * @return
     */
    public String gsonToJson(Object object){
        String json = null;
        if (null != object) {
            json = getInstance().toJson(object);
        }
        return json;
    }


    public <T> T gsonToBean(String json, Class<T> clazz){
        T type = null;
        if (null != json) {
            type = getInstance().fromJson(json, clazz);
        }
        return type;
    }

}
