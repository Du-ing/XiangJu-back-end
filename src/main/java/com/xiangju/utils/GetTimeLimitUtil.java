package com.xiangju.utils;

import java.util.Date;

/**
 * 通过输入的时间类型返回准确的时间
 */
public class GetTimeLimitUtil {
    public static Date getTimeLimit(String time){
        Date date = new Date();
//        System.out.println(date);
        if (time.equals("三天内")){
            date.setDate(date.getDay() - 2);
        }else if (time.equals("一周内")){
            date.setDate(date.getDay() - 6);
        }else if (time.equals("半年内")){
            date.setMonth(date.getMonth() - 5);
        }else {
            date.setYear(date.getYear() - 10);
        }
//        System.out.println(date);
        return date;
    };

//    public static void main(String[] args) {
//        getStartime("半年内");
//    }
}
