package com.wd.tech.zhangn.utils;

import com.wd.tech.url.URl;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class zReUtils {

    private static zReUtils netUtils;

    private zReUtils() {

    }

    public static <T> T getinstance(Class<T> service) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URl.URL_OUTERNET)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        T t = retrofit.create(service);

        return t;

    }

}
