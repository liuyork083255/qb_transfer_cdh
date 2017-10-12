package com.sumscope.cdh.realtime.transfer.config;

import com.alibaba.fastjson.JSON;
import com.sumscope.cdh.realtime.transfer.callback.BboCallback;
import com.sumscope.cdh.realtime.transfer.callback.SingleBboCallback;
import com.sumscope.cdh.realtime.transfer.callback.TradeCallback;
import com.sumscope.cdh.sumscopemq4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by liu.yang on 2017/9/28.
 */
@Configuration
public class RabbitMQConfig {

    @Autowired
    private Environment configParam;
    @Autowired
    private BboCallback bboCallback;
    @Autowired
    private TradeCallback tradeCallback;
    @Autowired
    private SingleBboCallback singleBboCallback;

    /**
     * common rabbitMQ sender config
     * @return
     */
    private CreateOptions getCreateOptions(){
        CreateOptions createOptions = new CreateOptions();
        createOptions.setHost(configParam.getProperty("rabbitmq.host"));
        createOptions.setPort(Integer.parseInt(configParam.getProperty("rabbitmq.port")));
        createOptions.setSenderType(CreateOptions.SenderType.FANOUT);
        return createOptions;
    }

    /**
     * config and inject TradeSender
     * @return
     * @throws Exception
     */
    @Bean(name = "TradeSender")
    public Sender getTradeSender() throws Exception {
        CreateOptions createOptions = getCreateOptions();
        createOptions.setRequestedHeartbeat(Integer.parseInt(configParam.getProperty("rabbitmq.sender.heart")));
        createOptions.setExchangeName(configParam.getProperty("rabbitmq.sender.exchange.trade"));
        return SenderFactory.newSender(createOptions);
    }

    /**
     * config and inject BboSender
     * @return
     * @throws Exception
     */
    @Bean(name = "BboSender")
    public Sender getBboSender() throws Exception{
        CreateOptions createOptions = getCreateOptions();
        createOptions.setRequestedHeartbeat(Integer.parseInt(configParam.getProperty("rabbitmq.sender.heart")));
        createOptions.setExchangeName(configParam.getProperty("rabbitmq.sender.exchange.bbo"));
        return SenderFactory.newSender(createOptions);
    }

    /**
     * config and inject TradeReceiver
     * @return
     * @throws IOException
     * @throws TimeoutException
     */
    @Bean(name = "TradeReceiver")
    public Receiver getTradeReceiver() throws IOException, TimeoutException {
        CreateOptions createOptions = getCreateOptions();
        createOptions.setRequestedHeartbeat(Integer.parseInt(configParam.getProperty("rabbitmq.receiver.heart")));
        createOptions.setExchangeName(configParam.getProperty("rabbitmq.receiver.exchange.trade"));
        return ReceiverFactory.newReceiver(createOptions,tradeCallback);
    }

    /**
     * config and inject BboReceiver
     * @return
     * @throws IOException
     * @throws TimeoutException
     */
    @Bean(name = "BboReceiver")
    public Receiver getBboReceiver() throws IOException, TimeoutException {
        CreateOptions createOptions = getCreateOptions();
        createOptions.setRequestedHeartbeat(Integer.parseInt(configParam.getProperty("rabbitmq.receiver.heart")));
        createOptions.setExchangeName(configParam.getProperty("rabbitmq.receiver.exchange.bbo"));
        return ReceiverFactory.newReceiver(createOptions,bboCallback);
    }

    /**
     * config and inject SingleBboReceiver
     * @return
     * @throws IOException
     * @throws TimeoutException
     */
    @Bean(name = "SingleBboReceiver")
    public Receiver getSingleBboReceiver() throws IOException, TimeoutException {
        CreateOptions createOptions = getCreateOptions();
        createOptions.setRequestedHeartbeat(Integer.parseInt(configParam.getProperty("rabbitmq.receiver.heart")));
        createOptions.setExchangeName(configParam.getProperty("rabbitmq.receiver.exchange.single.bbo"));
        return ReceiverFactory.newReceiver(createOptions,singleBboCallback);
    }

}
