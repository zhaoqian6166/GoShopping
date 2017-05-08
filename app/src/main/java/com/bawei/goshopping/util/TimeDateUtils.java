package com.bawei.goshopping.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by yibai on 2016/8/4.
 */
public class TimeDateUtils {

    /**
     * 输入毫秒数，获取相应格式的时间
     */
    public static String getFormattedDate(long timeMillis, String format) {
        String s = "";
        Calendar c = Calendar.getInstance();

        SimpleDateFormat formatter = new SimpleDateFormat(format);
        c.setTimeInMillis(timeMillis);
        s = formatter.format(c.getTime());
        return s;
    }


    /**
     * 输入时间日期，转为毫秒数
     */
    public static long getMillis(String date) throws ParseException {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        return f.parse(date).getTime();
    }
}
