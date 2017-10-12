package com.sumscope.cdh.realtime.transfer.callback;

import com.rabbitmq.client.AMQP;
import com.sumscope.cdh.sumscopemq4j.MqReceiverCallback;

/**
 * Created by liu.yang on 2017/9/28.
 */
public abstract class AbstractCallback implements MqReceiverCallback {

    @Override
    public boolean processString(String message, AMQP.BasicProperties basicProperty) {
        return false;
    }

    @Override
    public boolean processBytes(byte[] message, AMQP.BasicProperties basicProperty) {
        return false;
    }
}
