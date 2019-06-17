package com.wd.tech.contract;

import com.wd.tech.bean.CollectBean;
import com.wd.tech.bean.CommunityBean;
import com.wd.tech.bean.HeadBean;
import com.wd.tech.bean.LoginBean;
import com.wd.tech.bean.LookFroFriendBeen;
import com.wd.tech.bean.RegisterBean;
import com.wd.tech.bean.SignatureBean;
import com.wd.tech.bean.UserBean;
import com.wd.tech.bean.WechatBean;
import com.wd.tech.zhangn.bean.AllBkBean;

import java.io.File;
import java.util.List;

/**
 * @Author：Y
 * @E-mail： 2447892835@qq.com
 * @Date：2019.6.9 22:07
 * @Description：YangXinYu
 */
public interface IContract {
    public interface IWechatLogin{
        void WechatLogin(WechatBean registerBean);
    }
    public interface ICollect{
        void collect(CollectBean registerBean);
    }
    public interface ISet{
        void user(UserBean registerBean);
        void head(HeadBean headBean);
    }
    public interface ISginature{
        void sginature(SignatureBean registerBean);
    }
    public interface ILogin{
       void login(LoginBean loginBean);
    }
    public interface IRegister{
        void register(RegisterBean registerBean);
    }
    //
    public interface IFriend{
        void friend(LookFroFriendBeen lookFroFriendBeen);
    }
    public interface IPresenter{
        void login(String phone,String pwd);
        void register(String phone, String nickName, String pwd);
        void wechatLogin(String code);
        void friend(int userld,String sessionld,String phone);
        void collect(int page,int count);
        void user();
        void sginature(String aa);
        void head(File file);
    }
    //社区的V层
    public interface VSheQv{
        //社区展示V
        public void VSheQv(List<CommunityBean.ResultBean> lit);

        void showCommunityGreat(Object object);

    }
    //社区的P层
    public interface PSheQv{
        //社区展示P
        public void PSheQv(int page,int count,int userId,String sessionId);
        public void onDrestrey();
        //社区点赞@取消点赞
        void toCommunity_Great(int communityId);
        void toCommunity_NoGreat(int communityId);
    }






    //所有版块v
    public interface AllBkView{
        void show(AllBkBean bean);
    }
    public interface AllBkP{
        void request();
    }





}
