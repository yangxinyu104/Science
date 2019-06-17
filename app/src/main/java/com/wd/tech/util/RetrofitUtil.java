package com.wd.tech.util;

import android.os.Environment;
import android.util.Log;

import com.wd.tech.api.Api;
import com.wd.tech.app.MyApplication;
import com.wd.tech.url.URl;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Author：Y
 * @E-mail： 2447892835@qq.com
 * @Date：2019.5.9 21:29
 * @Description：YangXinYu
 */
public class RetrofitUtil {

    private final Api api;
    private static RetrofitUtil retrofitUtil;
    private RetrofitUtil(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cache(new Cache(new File(Environment.getExternalStorageDirectory()+"okHttpClient"),100))
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .sslSocketFactory(SSLSocketFactoryUtils.createSSLSocketFactory())
                .readTimeout(1000, TimeUnit.SECONDS)
                .build();
        api = new Retrofit
                .Builder()
                .baseUrl(URl.URL_OUTERNET)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(Api.class);
    }

    //单例模式
    public static synchronized RetrofitUtil GetInstance(){
        if (retrofitUtil == null){
            retrofitUtil = new RetrofitUtil();
        }
            return retrofitUtil;
    }

    public void doPost(String url, HashMap<String,Object> map, HttpListener httpListener){

        Observable<ResponseBody> observable = api.Post(url,map);
        SetObservable(observable,httpListener);
    }
    public void doWechatLoginPost(String url,String ak,String code, HttpListener httpListener){
        Observable<ResponseBody> observable = api.toWechat(url,ak,code);
        SetObservable(observable,httpListener);
    }

    public void doGets(String url,int userId,String sessionId,HttpListener httpListener){
        Observable<ResponseBody> observable = api.Gets(url,userId,sessionId);
        SetObservable(observable,httpListener);
    }
    public void doPut(String url,int userId,String sessionId,HashMap<String,Object> map,HttpListener httpListener){
        Observable<ResponseBody> observable = api.Put(url,userId,sessionId,map);
        SetObservable(observable,httpListener);
    }
    public void doUpFile(String url,int userId,String sessionId,File file,HttpListener httpListener){
        RequestBody requestFile = RequestBody.create(MediaType.parse("application/otcet-stream"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), requestFile);
        Observable<ResponseBody> observable = api.PostUpdateHead(url,userId,sessionId,body);
        SetObservable(observable,httpListener);
    }

    public void doGet(String url,int userld,String sessionld, HashMap<String,Object> map, HttpListener httpListener){
        Observable<ResponseBody> observable = api.Gets(url,userld,sessionld,map);
        SetObservable(observable,httpListener);
    }

    public void diazan(String url, int userld, String sessionld, HashMap<String,Object> map, HttpListener httpListener){
        Observable<ResponseBody> observable = api.dianzan(url,userld,sessionld,map);
        SetObservable(observable,httpListener);
    }

    public void qxdiazan(int userld, String sessionld, HashMap<String,Object> map, HttpListener httpListener){
        Observable<ResponseBody> observable = api.qxdianzan(userld,sessionld,map);
        SetObservable(observable,httpListener);
    }


    public void SetObservable(Observable<ResponseBody> observable,HttpListener httpListener ){
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(GetObserver(httpListener));
    }



    private Observer GetObserver(final HttpListener httpListener) {
        Observer<ResponseBody> observer = new Observer<ResponseBody>() {

            @Override
            public void onError(Throwable e) {
                Log.e("tag",e.getMessage());
                httpListener.Error(e.getMessage());
            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ResponseBody responseBody) {
                try {
                    httpListener.Succeed(responseBody.string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        return observer;
    }

    public interface HttpListener{
        void Succeed(String s);
        void Error(String s);
    }
}
