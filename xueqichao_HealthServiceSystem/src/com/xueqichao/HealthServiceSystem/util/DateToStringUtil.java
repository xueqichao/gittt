package com.xueqichao.HealthServiceSystem.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateToStringUtil
{
    public static String dateToString(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }

}
