package com.example.ftkj.mycook.net;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


/**
 * Created by FTKJ on 2017/5/23.
 */

public class NetWorkUtil {
    private static OkHttpClient okHttpClient;
    private static ServiceApi sApi;

    /**
     * 初始化okhttp
     */
    private static void initOkhttp() {
        if (okHttpClient == null) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
        }

    }
    public static ServiceApi getServices() {
        initOkhttp();
        if (sApi==null) {
            synchronized (NetWorkUtil.class) {
                if (sApi == null) {
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(UrlConstant.BASEURL)
                            .client(okHttpClient)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addConverterFactory(ScalarsConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .build();

                    sApi = retrofit.create(ServiceApi.class);
                }
            }
        }
        return sApi;
    }

}
