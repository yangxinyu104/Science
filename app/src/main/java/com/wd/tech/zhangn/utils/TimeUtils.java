package com.wd.tech.zhangn.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {


    // 将long类型转为时间戳
    public static String longToDate(long lo) {

        Date date = new Date(lo);

        SimpleDateFormat sd = new SimpleDateFormat("HH:mm:ss");

        return sd.format(date);
    }
}
