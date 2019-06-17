package com.wd.tech.presenter;

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
import com.wd.tech.contract.IContract;
import com.wd.tech.model.ScienceModel;
import com.wd.tech.zhangn.bean.AllBkBean;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author：Y
 * @E-mail： 2447892835@qq.com
 * @Date：2019.6.9 22:07
 * @Description：YangXinYu
 */
public class SciencePresenter<T> implements IContract.IPresenter,IContract.PSheQv,IContract.AllBkP {

    T t;
    private final ScienceModel scienceModel;

    public SciencePresenter(T t) {
        scienceModel = new ScienceModel();
        this.t = t;
    }

    @Override
    public void login(String phone, String pwd) {
        scienceModel.login(phone, pwd, new ScienceModel.SetLogin() {
            @Override
            public void succeed(LoginBean loginBean) {
                IContract.ILogin iLogin = (IContract.ILogin) t;
                iLogin.login(loginBean);
            }
        });

    }

    @Override
    public void register(String phone, String nickName, String pwd) {
        scienceModel.register(phone, nickName, pwd, new ScienceModel.SetRegister() {
            @Override
            public void succeed(RegisterBean registerBean) {
                IContract.IRegister iRegister = (IContract.IRegister) t;
                iRegister.register(registerBean);
            }
        });
    }
    @Override
    public void wechatLogin(String code) {
        scienceModel.wechatLogin(code, new ScienceModel.SetWechatLogin() {
            @Override
            public void succeed(WechatBean registerBean) {
                IContract.IWechatLogin iWechatLogin = (IContract.IWechatLogin) t;
                iWechatLogin.WechatLogin(registerBean);
            }
        });
    }
    @Override
    public void friend(int userld, String sessionld, String phone) {
        scienceModel.friends(userld, sessionld, phone, new ScienceModel.AddFriend() {
            @Override
            public void succeed(LookFroFriendBeen lookFroFriendBeen) {
                IContract.IFriend iFriend= (IContract.IFriend) t;
                iFriend.friend(lookFroFriendBeen);
            }
        });
    }

    @Override
    public void collect(int page, int count) {
        scienceModel.collect(page, count, new ScienceModel.SetCollect() {
            @Override
            public void succeed(CollectBean registerBean) {
                IContract.ICollect iCollect = (IContract.ICollect) t;
                iCollect.collect(registerBean);
            }
        });
    }

    @Override
    public void user() {
        scienceModel.user(new ScienceModel.SetUser() {
            @Override
            public void succeed(UserBean registerBean) {
                IContract.ISet iSet = (IContract.ISet) t;
                iSet.user(registerBean);
            }
        });
    }

    @Override
    public void sginature(String aa) {
        scienceModel.signature(aa, new ScienceModel.SetSignature() {
            @Override
            public void succeed(SignatureBean registerBean) {
                IContract.ISginature iSginature = (IContract.ISginature) t;
                iSginature.sginature(registerBean);
            }
        });
    }

    @Override
    public void head(File file) {
        scienceModel.head(file, new ScienceModel.SetHead() {
            @Override
            public void succeed(HeadBean registerBean) {
                IContract.ISet iSet = (IContract.ISet) t;
                iSet.head(registerBean);
            }
        });
    }





    @Override
    public void PSheQv(int page, int count,int UserId,String SessionId) {
        HashMap<String,Object> map=new HashMap<>();
        map.put("page",page);
        map.put("count",count);
        scienceModel.Community(map, UserId, SessionId, new ScienceModel.MyCallBreak() {
            @Override
            public void sressco(Object o) {
                IContract.VSheQv vSheQv= (IContract.VSheQv) t;
                CommunityBean beans= (CommunityBean) o;
                vSheQv.VSheQv(beans.getResult());
            }
        });
    }

    @Override
    public void onDrestrey() {

    }

    @Override
    public void toCommunity_Great(int communityId) {
        HashMap<String,Object> map=new HashMap<>();
        map.put("communityId",communityId);
        scienceModel.dianzan(map, MyApplication.UserId, MyApplication.SessionId, new ScienceModel.MyCallBreak() {
            @Override
            public void sressco(Object o) {
                IContract.VSheQv communityFragment = (IContract.VSheQv) t;
                communityFragment.showCommunityGreat(o);
            }
        });
    }

    @Override
    public void toCommunity_NoGreat(int communityId) {
        HashMap<String,Object> map=new HashMap<>();
        map.put("communityId",communityId);
        scienceModel.dianzan(map, MyApplication.UserId, MyApplication.SessionId, new ScienceModel.MyCallBreak() {
            @Override
            public void sressco(Object o) {
                IContract.VSheQv communityFragment = (IContract.VSheQv) t;
                communityFragment.showCommunityGreat(o);
            }
        });
    }



    //所有版块
    @Override
    public void request() {
        scienceModel.allbk(new ScienceModel.SetAllBk() {
            @Override
            public void succeed(AllBkBean bean) {
                IContract.AllBkView allBkP= (IContract.AllBkView) t;
                allBkP.show(bean);
            }
        });
    }
}
