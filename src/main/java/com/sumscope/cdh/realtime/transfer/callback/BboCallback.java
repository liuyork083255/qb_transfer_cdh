package com.sumscope.cdh.realtime.transfer.callback;

import com.lmax.disruptor.dsl.Disruptor;
import com.sumscope.cdh.realtime.transfer.model.handler.BondType;
import com.sumscope.cdh.realtime.transfer.model.handler.SourceEventModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Created by liu.yang on 2017/9/28.
 */
@Component
public class BboCallback extends AbstractCallback {

    private static final Logger LOGGER = LoggerFactory.getLogger(BboCallback.class);


    @Autowired
    @Qualifier("SourceDisruptor")
    private Disruptor<SourceEventModel> sourceDisruptor;

    @Override
    public boolean processString(String message) {
        LOGGER.info("message coming from [bbo_2]");
        try {
            sourceDisruptor.publishEvent((event, seq, arg) -> {event.setMessage(message);event.setBondType(BondType.BBO);}, message);
        } catch (Exception e) {
            LOGGER.error(String.format("sourceDisruptor publish bbo_2 event fail,exception=>[%s].  message=>[%s]",e.getMessage(),message));
        }
        return true;
    }

    @Override
    public boolean processBytes(byte[] message) {
        return processString(new String(message));
    }
}
