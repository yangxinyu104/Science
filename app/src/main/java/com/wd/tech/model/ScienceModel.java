package com.wd.tech.model;

import android.util.Log;

import com.google.gson.Gson;
import com.wd.tech.app.MyApplication;
import com.wd.tech.bean.CollectBean;
import com.wd.tech.bean.CommunityBean;
import com.wd.tech.bean.HeadBean;
import com.wd.tech.bean.LoginBean;
import com.wd.tech.bean.LookFroFriendBeen;
import com.wd.tech.bean.RegisterBean;
import com.wd.tech.bean.SignatureBean;
import com.wd.tech.bean.UserBean;
import com.wd.tech.bean.WechatBean;
import com.wd.tech.url.URl;
import com.wd.tech.util.RetrofitUtil;
import com.wd.tech.zhangn.bean.AllBkBean;

import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;

/**
 * @Author：Y
 * @E-mail： 2447892835@qq.com
 * @Date：2019.6.9 22:07
 * @Description：YangXinYu
 */
public class ScienceModel {
    SetLogin setLogin;

    public interface SetLogin {
        void succeed(LoginBean loginBean);
    }

    SetRegister setRegister;

    public interface SetRegister {
        void succeed(RegisterBean registerBean);
    }

    public void login(String phone, String pwd, final SetLogin setLogin) {
        this.setLogin = setLogin;
        HashMap<String, Object> map = new HashMap();
        map.put("phone", phone);
        map.put("pwd", pwd);
        RetrofitUtil.GetInstance().doPost(URl.URL_LOGIN, map, new RetrofitUtil.HttpListener() {
            @Override
            public void Succeed(String s) {
                LoginBean loginBean = new Gson().fromJson(s, LoginBean.class);
                setLogin.succeed(loginBean);
            }

            @Override
            public void Error(String s) {
                Log.e("tag", "login      " + s);

            }
        });
    }

    public void register(String phone, String nickName, String pwd, final SetRegister setRegister) {
        this.setRegister = setRegister;
        HashMap<String, Object> map = new HashMap();
        map.put("phone", phone);
        map.put("nickName", nickName);
        map.put("pwd", pwd);
        RetrofitUtil.GetInstance().doPost(URl.URL_REGISTER, map, new RetrofitUtil.HttpListener() {
            @Override
            public void Succeed(String s) {
                RegisterBean registerBean = new Gson().fromJson(s, RegisterBean.class);
                setRegister.succeed(registerBean);
            }

            @Override
            public void Error(String s) {
                Log.e("tag", "register      " + s);
            }
        });
    }

    SetWechatLogin setWechatLogin;

    public interface SetWechatLogin {
        void succeed(WechatBean registerBean);
    }

    public void wechatLogin(String code, final SetWechatLogin setWechatLogin) {
        this.setWechatLogin = setWechatLogin;
        HashMap<String, Object> map = new HashMap();
        map.put("code", code);
        RetrofitUtil.GetInstance().doWechatLoginPost(URl.URL_WECHATLOGIN, "0110010010000", code, new RetrofitUtil.HttpListener() {
            @Override
            public void Succeed(String s) {
                WechatBean registerBean = new Gson().fromJson(s, WechatBean.class);
                setWechatLogin.succeed(registerBean);
            }

            @Override
            public void Error(String s) {
                Log.e("tag", "wechatLogin      " + s);
            }
        });
    }
    AddFriend addFriend;
    public interface AddFriend{
        void succeed(LookFroFriendBeen lookFroFriendBeen);
    }

    public void friends(int userld,String sessionld,String phone,final AddFriend addFriend){
        this.addFriend=addFriend;
        HashMap<String,Object> map=new HashMap();
        map.put("phone",phone);
        RetrofitUtil.GetInstance().doGet(URl.URL_PHONE, userld, sessionld, map, new RetrofitUtil.HttpListener() {
            @Override
            public void Succeed(String s) {
                LookFroFriendBeen lookFroFriendBeen = new Gson().fromJson(s, LookFroFriendBeen.class);
                Log.e( "Succeed: ",s );
                addFriend.succeed(lookFroFriendBeen);
            }

            @Override
            public void Error(String s) {

            }
        });

    }
    SetCollect setCollect;
    public interface SetCollect{
        void succeed(CollectBean registerBean);
    }
    public void collect(int page,int count, final SetCollect setCollect){
        this.setCollect = setCollect;
        HashMap<String,Object> map = new HashMap();
        map.put("page",page);
        map.put("count",count);
        RetrofitUtil.GetInstance().doGet(URl.URL_COLLECT,MyApplication.UserId,MyApplication.SessionId,map,new RetrofitUtil.HttpListener() {
            @Override
            public void Succeed(String s) {
                CollectBean registerBean = new Gson().fromJson(s, CollectBean.class);

                setCollect.succeed(registerBean);
            }


            @Override
            public void Error(String s) {
                Log.e("tag","collect      " +s);
            }
        });
    }
    SetUser setUser;
    public interface SetUser{
        void succeed(UserBean registerBean);
    }
    public void user(final SetUser setUser){
        this.setUser = setUser;

        RetrofitUtil.GetInstance().doGets(URl.URL_SET,MyApplication.UserId,MyApplication.SessionId,new RetrofitUtil.HttpListener() {
            @Override
            public void Succeed(String s) {
                UserBean registerBean = new Gson().fromJson(s, UserBean.class);

                setUser.succeed(registerBean);
            }

            @Override
            public void Error(String s) {
                Log.e("tag","user      " +s);
            }
        });
    }

    SetSignature setSignature;
    public interface SetSignature{
        void succeed(SignatureBean registerBean);
    }
    public void signature(String signature,final SetSignature setSignature){
        this.setSignature = setSignature;
        HashMap<String,Object> map = new HashMap();
        map.put("signature",signature);
        RetrofitUtil.GetInstance().doPut(URl.URL_SIGNATURE,MyApplication.UserId,MyApplication.SessionId,map,new RetrofitUtil.HttpListener() {
            @Override
            public void Succeed(String s) {
                SignatureBean registerBean = new Gson().fromJson(s, SignatureBean.class);

                setSignature.succeed(registerBean);
            }

            @Override
            public void Error(String s) {
                Log.e("tag","signature      " +s);
            }
        });
    }


    SetHead setHead;
    public interface SetHead{
        void succeed(HeadBean registerBean);
    }
    public void head(File signature, final SetHead setHead){
        this.setHead = setHead;
        RetrofitUtil.GetInstance().doUpFile(URl.URL_HEAD,MyApplication.UserId,MyApplication.SessionId,signature,new RetrofitUtil.HttpListener() {
            @Override
            public void Succeed(String s) {
                HeadBean registerBean = new Gson().fromJson(s, HeadBean.class);

                setHead.succeed(registerBean);
            }

            @Override
            public void Error(String s) {
                Log.e("tag","head      " +s);
            }
        });
    }



    SetAllBk setAllBk;
    public interface SetAllBk{
        void succeed(AllBkBean bean);
    }
    public void allbk(final SetAllBk setAllBk){
        RetrofitUtil.GetInstance().doGets(URl.ALL_BK,0,"",new RetrofitUtil.HttpListener() {
            @Override
            public void Succeed(String s) {
                AllBkBean bean = new Gson().fromJson(s, AllBkBean.class);
                setAllBk.succeed(bean);
            }

            @Override
            public void Error(String s) {
                Log.e("tag","signature      " +s);
            }
        });
    }

    //社区
    public void Community(HashMap<String,Object> map, int UserId, String SessionId, final MyCallBreak callBreak){
        RetrofitUtil.GetInstance().doGet(URl.Community, UserId, SessionId, map, new RetrofitUtil.HttpListener() {
            @Override
            public void Succeed(String s) {
                Log.e("aaa", "Succeed: "+s );
                Gson gson=new Gson();
                CommunityBean beans=gson.fromJson(s,CommunityBean.class);
                callBreak.sressco(beans);
            }

            @Override
            public void Error(String s) {
                Log.e("tag","社区的展示      " +s);
            }
        });
    }

    public void dianzan( HashMap<String, Object> map,int UserId, String SessionId,final MyCallBreak myCallBack) {
        RetrofitUtil.GetInstance().diazan(URl.DZ, UserId, SessionId, map, new RetrofitUtil.HttpListener() {
            @Override
            public void Succeed(String s) {
                Log.e("aaa", "Succeed: "+s );
                try {
                    JSONObject object = new JSONObject(s);
                    String m = object.getString("message");
                    myCallBack.sressco(m);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void Error(String s) {
                Log.e("tag","社区的点赞      " +s);
            }
        });
    }

    public void qxdianzan( HashMap<String, Object> map,int UserId, String SessionId,final MyCallBreak myCallBack) {
        RetrofitUtil.GetInstance().qxdiazan( UserId, SessionId, map, new RetrofitUtil.HttpListener() {
            @Override
            public void Succeed(String s) {
                Log.e("aaa", "Succeed: "+s );
                try {
                    JSONObject object = new JSONObject(s);
                    String m = object.getString("message");
                    myCallBack.sressco(m);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void Error(String s) {
                Log.e("tag","社区的点      " +s);
            }
        });
    }


    //设置接口
    public interface MyCallBreak{
        public void sressco(Object o);
    }





}
