package com.sumscope.cdh.realtime.transfer;

import com.lmax.disruptor.dsl.Disruptor;
import com.sumscope.cdh.realtime.transfer.model.handler.SourceEventModel;
import com.sumscope.cdh.realtime.transfer.model.handler.TargetEventModel;
import com.sumscope.cdh.sumscopemq4j.Receiver;
import com.sumscope.cdh.sumscopemq4j.Sender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Component;
import javax.annotation.PreDestroy;

/**
 * Created by liu.yang on 2017/9/27.
 */
@Component
public class Start {

    private static final Logger LOGGER = LoggerFactory.getLogger(Start.class);

    @Autowired
    @Qualifier("TradeReceiver")
    private Receiver tradeReceiver;
    @Autowired
    @Qualifier("BboReceiver")
    private Receiver bboReceiver;
    @Autowired
    @Qualifier("SingleBboReceiver")
    private Receiver singleBboReceiver;
    @Autowired
    @Qualifier("TradeSender")
    private Sender tradeSender;
    @Autowired
    @Qualifier("BboSender")
    private Sender bboSender;

    @Autowired
    @Qualifier("SourceDisruptor")
    private Disruptor<SourceEventModel> sourceDisruptor;
    @Autowired
    @Qualifier("TargetDisruptor")
    private Disruptor<TargetEventModel> targetDisruptor;

    public void start(){
        sourceDisruptor.start();
        targetDisruptor.start();
        LOGGER.info("disruptor start success");

        bboReceiver.receive();
        singleBboReceiver.receive();
        tradeReceiver.receive();
        LOGGER.info("MQ receiver start success");
    }

    @PreDestroy
    public void close(){
        bboReceiver.stop();
        tradeReceiver.stop();
        singleBboReceiver.stop();
        bboSender.close();
        tradeSender.close();
        LOGGER.info("MQ receiver and sender close success");

        sourceDisruptor.shutdown();
        targetDisruptor.shutdown();
        LOGGER.info("disruptor close success");
    }

    public static void main(String[] args) {
        SpringApplication.run(DataStandardApplication.class).getBean(Start.class).start();
    }
}
