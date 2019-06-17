package com.wd.tech.api;


import android.support.v4.view.ViewPager;

import com.wd.tech.zhangn.bean.DzBean;
import com.wd.tech.zhangn.bean.PingLMesBean;
import com.wd.tech.zhangn.bean.QxdzBean;
import com.wd.tech.zhangn.bean.ZnLbt;
import com.wd.tech.zhangn.bean.ZnZxBean;
import com.wd.tech.zhangn.bean.ZnZxXqBean;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;

import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;

import retrofit2.http.Query;

import retrofit2.http.QueryMap;
import retrofit2.http.QueryMap;

import retrofit2.http.Url;

/**
 * @Author：Y
 * @E-mail： 2447892835@qq.com
 * @Date：2019.5.9 21:53
 * @Description：YangXinYu
 */
public interface Api {

    /**
     * @param url url路径或地址
     * @param
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST
    public Observable<ResponseBody> Post(@Url String url, @FieldMap HashMap<String, Object> map);

    @FormUrlEncoded
    @POST
    public Observable<ResponseBody> toWechat(@Url String url, @Header("ak") String ak, @Field("code") String code);

    //轮播图
    @GET("information/v1/bannerShow")
    Call<ZnLbt> znlbt();

    //2资讯
    @GET("information/v1/infoRecommendList")
    Call<ZnZxBean> zx(@Header("userid")int userid,@Header("sessionid")String sessionid,
                      @Query("plateid") int plateId, @Query("page") int page, @Query("count") int count);

    @GET
    public Observable<ResponseBody> Gets(@Url String url, @Header("userId") int userld, @Header("sessionId") String sessionld, @QueryMap HashMap<String, Object> map);

    @PUT
    public Observable<ResponseBody> Put(@Url String url, @Header("userId")int userId, @Header("sessionId") String sessionId, @QueryMap HashMap<String,Object> map);

    @GET
    public Observable<ResponseBody> Gets(@Url String url, @Header("userId")int userId, @Header("sessionId") String sessionId);

    @Multipart
    @POST
    public Observable<ResponseBody> PostUpdateHead(@Url String url, @Header("userId") int userId, @Header("sessionId") String sessionId, @Part MultipartBody.Part file);

    //所有版块查询
    @GET
    public Observable<ResponseBody> allbk(@Url String url);


    //3资讯详情
    @GET("information/v1/findInformationDetails")
    Call<ZnZxXqBean> zxxq(@Header("userid")int userid, @Header("sessionid")String sessionid,
                          @Query("id")int id);

    //收藏
    @POST("user/verify/v1/addCollection")
    Call<DzBean> DZ(@Header("userid")int userid,@Header("sessionid")String sessionid,
                    @Query("infoId")int infoId);

    //取消收藏
    @DELETE("user/verify/v1/cancelCollection")
    Call<QxdzBean> qx(@Header("userid")int userid,@Header("sessionid")String sessionid,
                      @Query("infoId")int infoId);

    //查询用户评论列表
    @GET("information/v1/findAllInfoCommentList")
    Call<PingLMesBean> sepl(@Header("userid")int userid,@Header("sessionid")String sessionid,
                            @Query("infoId")int infoId,@Query("page")int page,
                            @Query("count")int count);


    //点赞
    @FormUrlEncoded
    @POST
    public Observable<ResponseBody> dianzan(@Url String url,@Header("userId") int userId, @Header("sessionId") String sessionId, @FieldMap HashMap<String, Object> map);
    //取消点赞
    @DELETE("icommunity/verify/v1/cancelCommunityGreat")
    Observable<ResponseBody> qxdianzan(@Header("userid")int userid, @Header("sessionid")String sessionid,
                                       @QueryMap HashMap<String,Object> map);





}
