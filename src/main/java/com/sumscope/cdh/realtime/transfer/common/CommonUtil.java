package com.sumscope.cdh.realtime.transfer.common;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by liu.yang on 2017/9/30.
 */
@Component
public class CommonUtil {

    public String getMessage(String message){
        return message.substring(message.indexOf("[{"),message.indexOf("}]}")+2);
    }

    public String getUUID(){
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }

    public String convertLongToStringDatetime(long t){
        if(t == 0)
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        else
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(t);
    }

    public int getDBPartition(){
        return Integer.parseInt(new SimpleDateFormat("yyyyMM").format(new Date()));
    }
}
