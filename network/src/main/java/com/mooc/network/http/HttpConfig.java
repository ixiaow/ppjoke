package com.mooc.network.http;

import android.text.TextUtils;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.collection.ArrayMap;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class HttpConfig {
    public static final String JSON_KEY = "json";

    /**
     * http get请求方式
     */
    public static final int GET = 0;
    /**
     * http post请求方式
     */
    public static final int POST = 1;
    /**
     * 缓存策略  仅仅使用缓存
     */
    public static final int CACHE_ONLY = 0;
    /**
     * 缓存策略  先使用缓存，然后请求网络更新数据
     */
    public static final int CACHE_FIRST = 1;
    /**
     * 缓存策略 仅仅使用网络数据
     */
    public static final int NET_ONLY = 2;
    /**
     * 缓存策略 仅仅使用网络数据，后将数据保缓存
     */
    public static final int NET_CACHE = 3;
    /**
     * http请求方式
     */
    @Method
    public int method = GET;
    /**
     * 缓存策略
     */
    @CacheStrategy
    public int cacheStrategy = NET_CACHE;
    /**
     * 设置post提交方式
     */
    public FormData formData;
    /*
     * 网络数据主动取消的tag
     */
    public Object tag;
    /**
     * http请求的基础url
     */
    public String baseUrl;
    /**
     * http请求的业务url
     */
    public String url;
    /**
     * 同步请求还是异步请求
     */
    public boolean isAsync = true;
    /**
     * 数据请求的转换类型
     */
    public Type type = Void.class;
    /**
     * http请求头部信息
     */
    private ArrayMap<String, String> headers;
    /**
     * http请求的参数
     */
    private ArrayMap<String, Object> params;

    public HttpConfig() {
        headers = new ArrayMap<>();
        params = new ArrayMap<>();
    }

    /**
     * 添加header信息
     *
     * @param name   header名称
     * @param header header值
     */
    public void addHeader(String name, String header) {
        headers.put(name, header);
    }

    /**
     * 添加header信息
     *
     * @param headers header的map集合
     */
    public void addHeaders(Map<String, String> headers) {
        this.headers.putAll(headers);
    }

    @NonNull
    public ArrayMap<String, String> getHeaders() {
        return headers;
    }

    /**
     * 添加param信息
     *
     * @param name  param 名称
     * @param param param值
     */
    public void addParam(String name, Object param) {
        params.put(name, param);
    }

    /**
     * 添加params
     *
     * @param params params集合
     */
    public void addParams(Map<String, Object> params) {
        this.params.putAll(params);
    }

    @NonNull
    public ArrayMap<String, Object> getParams() {
        return params;
    }

    public String url() {
        if (!TextUtils.isEmpty(baseUrl)) {
            return baseUrl + url;
        }
        return url;
    }

    public void setPostType(FormData formData) {
        this.formData = formData;
    }


    @IntDef({GET, POST})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Method {
    }


    @IntDef({CACHE_ONLY, CACHE_FIRST, NET_ONLY, NET_CACHE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface CacheStrategy {
    }
}
