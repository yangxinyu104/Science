package com.wd.tech.bean;

/**
 * @Author：Y
 * @E-mail： 2447892835@qq.com
 * @Date：2019.6.10 16:06
 * @Description：YangXinYu
 */
public class WechatBean {

    public String status;
    public String message;
    public ResultBean result;

    public static class ResultBean {
        public int userId;
        public String sessionId;
        public String nickName;
        public String headPic;
    }
    }

