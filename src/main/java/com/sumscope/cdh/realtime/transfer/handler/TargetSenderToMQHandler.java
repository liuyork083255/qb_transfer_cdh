package com.sumscope.cdh.realtime.transfer.handler;

import com.alibaba.fastjson.JSON;
import com.lmax.disruptor.EventHandler;
import com.sumscope.cdh.realtime.transfer.model.handler.TargetEventModel;
import com.sumscope.cdh.sumscopemq4j.Sender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created by liu.yang on 2017/9/30.
 */
@Component
public class TargetSenderToMQHandler implements EventHandler<TargetEventModel> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TargetSenderToMQHandler.class);

    @Autowired
    @Qualifier("BboSender")
    private Sender bboSender;
    @Autowired
    @Qualifier("TradeSender")
    private Sender tradeSender;

    @Override
    public void onEvent(TargetEventModel event, long sequence, boolean endOfBatch) throws Exception {
        switch (event.getBondType()){
            case BBO:
                try {
                    if(event.getTargetBboDBModelList().size() != 0)
                        bboSender.send(JSON.toJSONString(event.getTargetBboDBModelList()).getBytes(Charset.forName("UTF-8")));
                } catch (Exception e) {
                    LOGGER.error(String.format("send bbo_2 message fail,exception=>[%s]   message=>[%s]",e.getMessage(),JSON.toJSONString(event.getTargetBboDBModelList())));
                }
                break;
            case TRADE:
                try {
                    if(event.getTargetTradeDBModelList().size() != 0)
                        tradeSender.send(JSON.toJSONString(event.getTargetTradeDBModelList()).getBytes(Charset.forName("UTF-8")));
                } catch (Exception e) {
                    LOGGER.error(String.format("send trade message fail,exception=>[%s]   message=>[%s]",e.getMessage(),JSON.toJSONString(event.getTargetTradeDBModelList())));
                }
                break;
            case SINGLEBBO:
                break;
            default:
                LOGGER.error("no match Model type.");
        }
    }
}
