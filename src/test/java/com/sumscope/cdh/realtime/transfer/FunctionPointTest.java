package com.sumscope.cdh.realtime.transfer;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by liu.yang on 2017/10/11.
 */
public class FunctionPointTest {

    @Test
    public void fun(){
//        long time = 1507714591;
        long time = 1507714591*1000l;
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time);
        System.out.println(format);

        Long timestamp = Long.parseLong("1507714591")*1000;
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(timestamp));
        System.out.println(date);
    }
}
