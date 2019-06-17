package com.wd.tech.zhangn.MVP;

import com.wd.tech.zhangn.bean.DzBean;
import com.wd.tech.zhangn.bean.PingLMesBean;
import com.wd.tech.zhangn.bean.QxdzBean;
import com.wd.tech.zhangn.bean.ZnLbt;
import com.wd.tech.zhangn.bean.ZnZxBean;
import com.wd.tech.zhangn.bean.ZnZxXqBean;

/**
 * 张娜
 */
public interface ZnBaseConstract {


    interface View {
        void showData(ZnLbt result);
        void showData2(ZnZxBean bean);

        void showData3(ZnZxXqBean bean);

        void showDatasc(DzBean bean);

        void showDataqx(QxdzBean bean);

        void showDatasepl(PingLMesBean bean);
    }
    interface Presenter<T extends View> {
        void attachView(T t);

        void dattachView(T t);



        //资讯详情
        void requestDatazxxq(int userId, String sessionId, int id);

        void requestDatasc(int userId, String sessionId, int id);

        void requestDataqx(int userId, String sessionId, int id);

        //轮播图 资讯
        void requestDatazx(int userId, String sessionId);
        void requestDataplmsg(int userId, String sessionId, int id);

    }


    interface Model {
        //轮播图 资讯
        void reponseData(int userId, String sessionId, CallBack callBack);

        void reponseDatazxxq(int userId, String sessionId, int id, CallBack callBack);

        void reponseDatasc(int userId, String sessionId, int id, CallBack callBack);
        void reponseDataqx(int userId, String sessionId, int id, CallBack callBack);


        interface CallBack {
            void oncall(ZnLbt result);

            void oncall2(ZnZxBean bean);

            void oncallxq(ZnZxXqBean bean);
            void oncalldz(DzBean bean);

            void oncallqx(QxdzBean bean);

            void oncallsepl(PingLMesBean bean);
        }





    }


}
