package com.sumscope.cdh.realtime.transfer.rabbitmq;

import com.sumscope.cdh.realtime.transfer.callback.AbstractCallback;
import com.sumscope.cdh.sumscopemq4j.CreateOptions;
import com.sumscope.cdh.sumscopemq4j.Receiver;
import com.sumscope.cdh.sumscopemq4j.ReceiverFactory;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by liu.yang on 2017/10/11.
 */
public class Receive {
    private String host = "rabbitmq-cdh.dev.sumscope.com";
    private int port = 5672;
    private CreateOptions createOptions;

    @Before
    public void init(){
        createOptions = new CreateOptions();
        createOptions.setHost(host);
        createOptions.setPort(port);
        createOptions.setRequestedHeartbeat(5);
        createOptions.setDurable(false);
        createOptions.setSenderType(CreateOptions.SenderType.FANOUT);
    }
    @Test
    public void receiverBBO() throws IOException, TimeoutException {
        createOptions.setExchangeName("bond.transfer.best");
        Receiver receiver = ReceiverFactory.newReceiver(createOptions, new AbstractCallback() {
            @Override
            public boolean processString(String message) {
                System.out.println(message);
                return false;
            }

            @Override
            public boolean processBytes(byte[] message) {
                processString(new String(message));
                return false;
            }
        });

        receiver.receive();
    }


    @Test
    public void receiverTrade(){
        createOptions.setExchangeName("bond.transfer.trade");


    }
}
