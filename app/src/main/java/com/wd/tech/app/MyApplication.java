package com.wd.tech.app;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.wd.tech.throwable.ThrowDispose;

/**
 * @Author：Y
 * @E-mail： 2447892835@qq.com
 * @Date：2019.6.9 14:42
 * @Description：YangXinYu
 */
public class MyApplication extends Application {
    public static String signature;

    //个人信息数据
    public static String Head;

    public static String name;

    public static int vipfalg;
    //UserId
    public static  int UserId ;
    //SessionId
    public static  String SessionId ;
    private static Context applicationContext;
    public static IWXAPI mWxApi;
    @Override
    public void onCreate() {

        super.onCreate();
        //设置磁盘缓存
        DiskCacheConfig diskCacheConfig =  DiskCacheConfig.newBuilder(this)
                .setBaseDirectoryName("science.bw")
                .setBaseDirectoryPath(Environment.getExternalStorageDirectory())
                .build();
        //设置磁盘缓存的配置,生成配置文件
        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
                .setMainDiskCacheConfig(diskCacheConfig)
                .build();
        Fresco.initialize(this,config);
        applicationContext = getApplicationContext();
        LoginTools.initLoginTools(this);
        ThrowDispose.GetInstance().Init(getApplicationContext());
    }
    public static class LoginTools {

        public static void initLoginTools(Application mContext){
//AppConst.WEIXIN.APP_ID是指你应用在微信开放平台上的AppID，记得替换。
            mWxApi = WXAPIFactory.createWXAPI(mContext, "wx4c96b6b8da494224", false);
// 将该app注册到微信
            mWxApi.registerApp("wx4c96b6b8da494224");
        }
        public static boolean handleIntent(Intent intent, IWXAPIEventHandler var2){
            if (intent==null){
                return false;
            }
            return mWxApi.handleIntent(intent,var2);
        }

    }
    public static void wxLogin(Context mContext) {
        if (!mWxApi.isWXAppInstalled()) {
            Toast.makeText(mContext, "你还没有安装微信", Toast.LENGTH_SHORT).show();
            return;
        }
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "diandi_wx_login";
        mWxApi.sendReq(req);
    }

    public static  Context GetContext(){
        return applicationContext;
    }

    //判断是否登录
    public static boolean Remember(){
        if (UserId==0){
            return false;
        }else{
            return true;
        }
    }


}
