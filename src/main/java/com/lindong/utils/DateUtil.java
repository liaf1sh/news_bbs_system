package com.lindong.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    /**
     * 时间字符串转换成date方法
     * @param dateStr
     * @return
     */
    public static Date strToDate(String dateStr){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = null;
        try {
            date = dateFormat.parse(dateStr);
        } catch (ParseException e) {
            System.out.println("======== 时间字符串转换时间异常!");
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 时间转字符串
     * @param date
     * @return
     */
    public static String dateToStr(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String format = sdf.format(date);
        try {
            System.out.println(sdf.parse(format));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return format;
    }

    /**
     * 计算时间的相隔天数
     * @param date
     * @return
     */
    public static int isContinuous(Date date){

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long startDateTime = 0;
        long endDateTime = 0 ;
        try {
            startDateTime = dateFormat.parse(dateFormat.format(date)).getTime();
            endDateTime = dateFormat.parse(dateFormat.format(new Date())).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int i = (int)((endDateTime - startDateTime) / (1000 * 3600 * 24));
        //System.out.println(i);

        return i;

    }

    /**
     * 判断某个时间是否为本月 1 号
     * @param date
     * @return
     */
    public static boolean isFirstDayOfMonth(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        //System.out.println(calendar.get(Calendar.MONTH));
        return calendar.get(Calendar.DAY_OF_MONTH) == 1;
    }

    /*public static void main(String[] args) throws ParseException {


    }*/
}
