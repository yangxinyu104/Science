package com.wd.tech.zhangn.MVP;

import com.wd.tech.zhangn.bean.DzBean;
import com.wd.tech.zhangn.bean.PingLMesBean;
import com.wd.tech.zhangn.bean.QxdzBean;
import com.wd.tech.zhangn.bean.ZnLbt;
import com.wd.tech.zhangn.bean.ZnZxBean;
import com.wd.tech.zhangn.bean.ZnZxXqBean;

import java.lang.ref.SoftReference;

public class ZnPresenter implements ZnBaseConstract.Presenter {

    ZnBaseConstract.View view;
    private SoftReference<ZnBaseConstract.View> reference;
    private ZnModel model;//资讯

    @Override
    public void attachView(ZnBaseConstract.View view) {
        this.view = view;
        reference = new SoftReference<>(view);
        model = new ZnModel();
    }

    @Override
    public void dattachView(ZnBaseConstract.View view) {
        reference.clear();
    }


    //资讯详情
    @Override
    public void requestDatazxxq(int userId, String sessionId, int id) {
        model.reponseDatazxxq(userId, sessionId,id, new ZnBaseConstract.Model.CallBack() {
            @Override
            public void oncall(ZnLbt result) {

            }

            @Override
            public void oncall2(ZnZxBean bean) {

            }

            @Override
            public void oncallxq(ZnZxXqBean bean) {
                view.showData3(bean);
            }


            @Override
            public void oncalldz(DzBean bean) {

            }

            @Override
            public void oncallqx(QxdzBean bean) {

            }

            @Override
            public void oncallsepl(PingLMesBean bean) {

            }
        });
    }

    //点赞
    @Override
    public void requestDatasc(int userId, String sessionId, int id) {
        model.reponseDatasc(userId, sessionId,id, new ZnBaseConstract.Model.CallBack() {
            @Override
            public void oncall(ZnLbt result) {

            }

            @Override
            public void oncall2(ZnZxBean bean) {

            }

            @Override
            public void oncallxq(ZnZxXqBean bean) {

            }

            //收藏
            @Override
            public void oncalldz(DzBean bean) {
                view.showDatasc(bean);
            }

            @Override
            public void oncallqx(QxdzBean bean) {

            }

            @Override
            public void oncallsepl(PingLMesBean bean) {

            }
        });
    }

    //取消点赞
    @Override
    public void requestDataqx(int userId, String sessionId, int id) {
        model.reponseDataqx(userId, sessionId, id, new ZnBaseConstract.Model.CallBack() {
            @Override
            public void oncall(ZnLbt result) {

            }

            @Override
            public void oncall2(ZnZxBean bean) {

            }

            @Override
            public void oncallxq(ZnZxXqBean bean) {

            }

            @Override
            public void oncalldz(DzBean bean) {

            }

            @Override
            public void oncallqx(QxdzBean bean) {
                view.showDataqx(bean);
            }

            @Override
            public void oncallsepl(PingLMesBean bean) {

            }
        });
    }

    @Override
    public void requestDatazx(int userId, String sessionId) {
        //轮播图
        model.reponseData(userId,sessionId,new ZnBaseConstract.Model.CallBack() {
            @Override
            public void oncall(ZnLbt result) {
                view.showData(result);
            }
            @Override
            public void oncall2(ZnZxBean bean) {
                //资讯
                view.showData2(bean);
            }

            @Override
            public void oncallxq(ZnZxXqBean bean) {

            }

            @Override
            public void oncalldz(DzBean bean) {

            }

            @Override
            public void oncallqx(QxdzBean bean) {

            }

            @Override
            public void oncallsepl(PingLMesBean bean) {

            }
        });
    }


    public void requestDataplmsg(int userId, String sessionId, int id) {
        model.reponseDatazxxq(userId, sessionId,id, new ZnBaseConstract.Model.CallBack() {
            @Override
            public void oncall(ZnLbt result) {

            }

            @Override
            public void oncall2(ZnZxBean bean) {

            }

            @Override
            public void oncallxq(ZnZxXqBean bean) {

            }


            @Override
            public void oncalldz(DzBean bean) {

            }

            @Override
            public void oncallqx(QxdzBean bean) {

            }

            @Override
            public void oncallsepl(PingLMesBean bean) {
                view.showDatasepl(bean);
            }
        });
    }
}
