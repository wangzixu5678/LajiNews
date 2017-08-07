package com.example.ftkj.mycook.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by FTKJ on 2017/5/24.
 */

public class SimpleDateUtil {

    public static String parseDatetime(String time,String format){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        long aLong = Long.parseLong(time);
        Date date = new Date(aLong);
        String s = simpleDateFormat.format(date);
        return s;
    }

    public static String parseDatetime(long time,String format){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date date = new Date(time);
        String s = simpleDateFormat.format(date);
        return s;
    }

}
