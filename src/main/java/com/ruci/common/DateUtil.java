package com.ruci.common;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static String newDate(){
        SimpleDateFormat sdf=new SimpleDateFormat("YYYYMMDDhhmmss");
        String str=sdf.format(new Date());
        return str;
    }
}
