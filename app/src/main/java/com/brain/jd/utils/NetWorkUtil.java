package com.brain.jd.utils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 网络请求工具类, 注意使用此工具类需要设置网络权限
 * @author : Brian
 * @date : 2017/6/22
 */

public class NetWorkUtil {

    private static final String TAG = "NetWorkUtil";
    /**
     * POST 请求
     * @param urlStr    地址
     * @param params 参数
     * @return       结果
     */
    public static String doPost(String urlStr, HashMap<String, String> params) {
        try {
            // 1. 创建URL
            URL url = new URL(urlStr);
            // 2. 打开连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // 3. 设置请求方法
            conn.setRequestMethod("POST");
            // 4. 组装参数
            String paramStr = "";
            Set<Map.Entry<String, String>> entries = params.entrySet();
            for (Map.Entry<String, String> entry: entries) {
                paramStr += "&" + entry.getKey() + "=" + entry.getValue();
            }
            paramStr = paramStr.substring(1);
            Log.d(TAG, "doPost: param == " + paramStr);

            // 5. 写数据
            conn.setDoOutput(true);
            conn.getOutputStream().write(paramStr.getBytes());

            Log.d(TAG, "doPost ResponseCode == " + conn.getResponseCode());
            // 6. 获取服务器响应
            if (conn.getResponseCode() == 200) {
                InputStream is = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                Log.d(TAG, "doPost: 200");
                return reader.readLine();
            }
            Log.d(TAG, "doPost: " + conn.getResponseCode());

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "doPost: "+ e.toString());
        }
        return "";
    }

    /**
     * 发送get请求
     * @param urlStr url地址
     * @return
     */
    public static String doGet(String urlStr) {
        try {
            // 1. 创建URL
            URL url = new URL(urlStr);
            // 2. 打开连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // 3. 设置请求方法
            conn.setRequestMethod("POST");
            // 6. 获取服务器响应
            if (conn.getResponseCode() == 200) {
                InputStream is = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                return reader.readLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



}
