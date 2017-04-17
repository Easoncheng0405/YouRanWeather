package com.jilinuniversity.youranweather.util;


import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by 程杰 on 2017/4/15.
 */
//发送网络请求
public class HttpUtil {
    public static void sendOkHttpRequest(String address,okhttp3.Callback callback){
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);

    }
}
