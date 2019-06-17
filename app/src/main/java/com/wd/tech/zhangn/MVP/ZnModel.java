package com.wd.tech.zhangn.MVP;

import android.util.Log;

import com.wd.tech.api.Api;
import com.wd.tech.zhangn.bean.DzBean;
import com.wd.tech.zhangn.bean.PingLMesBean;
import com.wd.tech.zhangn.bean.QxdzBean;
import com.wd.tech.zhangn.bean.ZnLbt;
import com.wd.tech.zhangn.bean.ZnZxBean;
import com.wd.tech.zhangn.bean.ZnZxXqBean;
import com.wd.tech.zhangn.utils.zReUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ZnModel implements ZnBaseConstract.Model {
    //轮播图
    @Override
    public void reponseData(int userId, String sessionId, final CallBack callBack) {
        zReUtils.getinstance(Api.class).znlbt().enqueue(new Callback<ZnLbt>() {
            @Override
            public void onResponse(Call<ZnLbt> call, Response<ZnLbt> response) {
                ZnLbt lbt = response.body();
                callBack.oncall(lbt);
            }

            @Override
            public void onFailure(Call<ZnLbt> call, Throwable t) {

            }
        });
        //资讯
        zReUtils.getinstance(Api.class).zx(userId, sessionId, 1, 1, 5).enqueue(new Callback<ZnZxBean>() {
            @Override
            public void onResponse(Call<ZnZxBean> call, Response<ZnZxBean> response) {
                ZnZxBean bean = response.body();
                callBack.oncall2(bean);
            }

            @Override
            public void onFailure(Call<ZnZxBean> call, Throwable t) {
            }
        });
    }

    //详情
    @Override
    public void reponseDatazxxq(int userId, String sessionId, int id, final CallBack callBack) {
        zReUtils.getinstance(Api.class).zxxq(userId, sessionId, id).enqueue(new Callback<ZnZxXqBean>() {
            @Override
            public void onResponse(Call<ZnZxXqBean> call, Response<ZnZxXqBean> response) {
                ZnZxXqBean bean = response.body();
                callBack.oncallxq(bean);
            }

            @Override
            public void onFailure(Call<ZnZxXqBean> call, Throwable t) {

            }
        });
        //查询评论信息
        zReUtils.getinstance(Api.class).sepl(userId, sessionId, id, 1, 10).enqueue(new Callback<PingLMesBean>() {
            @Override
            public void onResponse(Call<PingLMesBean> call, Response<PingLMesBean> response) {
                PingLMesBean bean = response.body();
                callBack.oncallsepl(bean);
                //Log.d("ZnModel", bean.getMessage()+"-------------");
            }

            @Override
            public void onFailure(Call<PingLMesBean> call, Throwable t) {
                Log.d("ZnModel", "t:" + t);
            }
        });

    }

    //收藏
    @Override
    public void reponseDatasc(int userId, String sessionId, int id, final CallBack callBack) {
        zReUtils.getinstance(Api.class).DZ(userId, sessionId, id).enqueue(new Callback<DzBean>() {
            @Override
            public void onResponse(Call<DzBean> call, Response<DzBean> response) {
                DzBean bean = response.body();
                callBack.oncalldz(bean);
                Log.d("InformationFragment", bean.getMessage());
            }

            @Override
            public void onFailure(Call<DzBean> call, Throwable t) {
                Log.d("InformationFragment", "t:" + t);
            }
        });
    }

    //取消点赞
    @Override
    public void reponseDataqx(int userId, String sessionId, int id, final CallBack callBack) {
        zReUtils.getinstance(Api.class).qx(userId, sessionId, id).enqueue(new Callback<QxdzBean>() {
            @Override
            public void onResponse(Call<QxdzBean> call, Response<QxdzBean> response) {
                QxdzBean bean = response.body();
                callBack.oncallqx(bean);
            }

            @Override
            public void onFailure(Call<QxdzBean> call, Throwable t) {

            }
        });
    }


}
