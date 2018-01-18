package com.example.order.net;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dell on 2018-01-09  10:31
 */

public class RetrofitHelper {
    private static Retrofit retrofit;

    private static ServiceApi serviceApi;

    /**
     * 初始化Retrofit 单例模式
     */
    private static Retrofit getRetrofit() {
        //线程安全
        if (retrofit == null) {
            synchronized (RetrofitHelper.class) {
                if (retrofit == null) {
                    //设置拦截器
                    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
                    logging.setLevel(HttpLoggingInterceptor.Level.BODY);
                    OkHttpClient client = new OkHttpClient.Builder()
                            .addInterceptor(new MyInterceptor())
                            .connectTimeout(5, TimeUnit.SECONDS)//设置连接超时
                            .addInterceptor(logging)//添加拦截器
                            //.addNetworkInterceptor() //网路拦截器
                            .build();


                    retrofit = new Retrofit.Builder()
                            .baseUrl(Urls.BASE_URL)
                            .client(client)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .build();
                }
            }
        }

        return retrofit;
    }


    public static ServiceApi getAPI() {
        if (serviceApi == null) {
            synchronized (RetrofitHelper.class) {
                if (serviceApi == null) {
                    serviceApi = getRetrofit().create(ServiceApi.class);
                }
            }
        }
        return serviceApi;
    }

    /**
     * 添加公共参数拦截器
     */
    static class MyInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            HttpUrl httpUrl = request
                    .url()
                    .newBuilder()
                    .addQueryParameter("source", "android")
                    .build();
            Request requestNew = request
                    .newBuilder()
                    .url(httpUrl)
                    .build();
            return chain.proceed(requestNew);
        }
    }
}
