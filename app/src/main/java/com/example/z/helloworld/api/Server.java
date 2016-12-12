package com.example.z.helloworld.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

/**
 * Created by Z on 2016/12/12.
 */

public class Server {
    static OkHttpClient client;

    static {
        CookieJar cookieJar = new CookieJar() {
            Map<HttpUrl, List<Cookie>> cookiemap = new HashMap<HttpUrl, List<Cookie>>();

            @Override
            public void saveFromResponse(HttpUrl key, List<Cookie> value) {
                cookiemap.put(key, value);
            }

            @Override
            public List<Cookie> loadForRequest(HttpUrl key) {
                List<Cookie> cookies = cookiemap.get(key);
                if(cookies==null){
                    return new ArrayList<Cookie>();
                }else{
                    return cookies;
                }
            }
        };

        client = new OkHttpClient.Builder()
                .cookieJar(cookieJar)
                .build();
    }
    public static OkHttpClient getSharedClient(){
        return client;
    }

    public static String serverAdress(String api){
        return "http://169.254.80.80:8080/membercenter/api/"+api;
    }
}