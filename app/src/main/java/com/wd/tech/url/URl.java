package com.wd.tech.url;

/**
 * @Author：Y
 * @E-mail： 2447892835@qq.com
 * @Date：2019.5.9 22:10
 * @Description：YangXinYu
 */
public class URl {

    //外网地址
    public static  String URL_OUTERNET = "https://mobile.bwstudent.com/techApi/";

    //内网地址
    public static String URL_INTRANET =  "https://172.17.8.100/techApi/";

    //登录
    public  static String URL_LOGIN = "user/v1/login";

    //微信登陆
    public  static  String URL_WECHATLOGIN = "user/v1/weChatLogin";

    //注册
    public  static String URL_REGISTER = "user/v1/register";

    //按手机号查询
    public static String URL_PHONE="user/verify/v1/findUserByPhone";

    //社区展示
    public static String Community="community/v1/findCommunityList";
    //点赞
    public static String DZ="community/verify/v1/addCommunityGreat";


    public  static String URL_COLLECT = "user/verify/v1/findAllInfoCollection";

    public static String URL_SET = "user/verify/v1/getUserInfoByUserId";

    public static  String URL_SIGNATURE  =  "user/verify/v1/modifySignature";

    public static String URL_HEAD = "user/verify/v1/modifyHeadPic";

    //所有版块查询
    public static String ALL_BK="information/v1/findAllInfoPlate";



}
