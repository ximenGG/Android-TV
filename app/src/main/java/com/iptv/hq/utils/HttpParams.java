package com.iptv.hq.utils;
import android.text.TextUtils;
import org.json.JSONObject;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 请求参数的封装。可以是get，也可以是post
 */
public class HttpParams {

    private final Map<String, String> mParams = new HashMap<>();

    /**
     * 获取某个key对应的value
     *
     * @return 返回某个key对应的value
     */
    public String get(String key) {

        return mParams.get(key);
    }


    /**
     * 设置一个key=value的http 参数
     *
     * @param key   参数的key
     * @param value 参数的value
     * @return 返回HttpParams本身，便于链式编程
     */
    public HttpParams put(String key, String value) {
        mParams.put(key, value);
        return this;
    }

    /**
     * 返回一个get请求格式的字符串,如:?age=18&name=seny
     *
     * @return get请求的字符串结构
     */
    public String toGetParams() {
        StringBuilder buffer = new StringBuilder();
        if (!mParams.isEmpty()) {
            buffer.append("/?");
            for (Map.Entry<String, String> entry : mParams.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (TextUtils.isEmpty(key) || TextUtils.isEmpty(value)) {
                    continue;
                }
                try {
                    buffer.append(URLEncoder.encode(key, "UTF-8"));
                    buffer.append("=");
                    buffer.append(URLEncoder.encode(value, "UTF-8"));
                    buffer.append("&");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

        }
        String str = buffer.toString();
        //去掉最后的&
        if (str.length() > 1 && str.endsWith("&")) {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }


    /**
     * 返回封装了http params的Map集合
     *
     * @return
     */
    public Map<String, String> getParams() {
        return mParams;
    }
    /*
    *转化成json字符串
    */
    public String toJson() {
        return new JSONObject(mParams).toString();
    }
    /*
    *集合转化成json字符串
    */
    public String toJson(Map hashMap) {
        return new JSONObject(hashMap).toString();
    }
}
