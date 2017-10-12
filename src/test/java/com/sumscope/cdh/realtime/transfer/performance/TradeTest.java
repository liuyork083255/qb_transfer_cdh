package com.sumscope.cdh.realtime.transfer.performance;

import com.sumscope.cdh.sumscopemq4j.CreateOptions;
import com.sumscope.cdh.sumscopemq4j.Sender;
import com.sumscope.cdh.sumscopemq4j.SenderFactory;

import java.io.IOException;

/**
 * Created by liu.yang on 2017/10/12.
 */
public class TradeTest {
    private int port = 5672;
    private String host = "rabbitmq-cdh.dev.sumscope.com";
    private String message = "{\"AckMsgHead\":{\"exectime\":0.0,\"total\":1,\"retcode\":0,\"idx\":0},\"AckMsgBody\":{\"BusinessCode\":\"BOND\",\"IMQ_OUTCOME\":[{\"MS_OPERATE\":\"2\",\"MS_GOODS_CODE\":\"018005\",\"MS_BOND_KEY_LISTED_MARKET\":\"G0001242017FINPBB01SHSSE\",\"MS_TYPE\":\"2\",\"MS_BOND_KEY\":\"G0001242017FINPBB01SH\",\"MS_BODY\":{\"MS_id\":\"2480118dc0864070800fcaebef6a2103\",\"MS_price\":\"4.2962\",\"MS_fullPrice\":\"101.1762\",\"MS_netPrice\":\"99.250\",\"MS_pre_close_price\":\"99.350\",\"MS_goodsShortName\":\"\\u56fd\\u5f001701\",\"MS_dealStatus\":\"3\",\"MS_volume\":\"1007.1\",\"MS_goodsCode\":\"018005\",\"MS_createTime\":1507516802,\"MS_yield\":\"4.2962\"},\"MS_COMPANY_ID\":\"e\",\"MS_CREATE_TIME\":1507516802,\"MS_Version\":3079,\"MS_MODIFY_TIME\":1507516802,\"MS_LISTED_MARKET\":\"SSE\"},{\"MS_OPERATE\":\"2\",\"MS_GOODS_CODE\":\"132002\",\"MS_BOND_KEY_LISTED_MARKET\":\"T0000712015CORSCV01SSE\",\"MS_TYPE\":\"2\",\"MS_BOND_KEY\":\"T0000712015CORSCV01\",\"MS_BODY\":{\"MS_id\":\"96dbd9ffc87049bd82c69d5dbafd7c0f\",\"MS_dealStatus\":\"3\",\"MS_netPrice\":\"103.130\",\"MS_pre_close_price\":\"103.600\",\"MS_goodsShortName\":\"15\\u5929\\u96c6EB\",\"MS_volume\":\"0.2\",\"MS_goodsCode\":\"132002\",\"MS_createTime\":1507516833},\"MS_COMPANY_ID\":\"e\",\"MS_CREATE_TIME\":1507516833,\"MS_Version\":3080,\"MS_MODIFY_TIME\":1507516833,\"MS_LISTED_MARKET\":\"SSE\"}]},\"seq\":336721}";

    public static void main(String[] args) throws Exception {
        TradeTest tradeTest = new TradeTest();
        Sender sender = tradeTest.getSender();


        //预热 编译
        tradeTest.preheat(sender,1000);

        tradeTest.run(sender,100000);
    }

    public void run(Sender sender,int number) throws Exception {

        long startTime = System.currentTimeMillis();
        for (int i=0;i<number;i++){
            sender.send(message.getBytes());
        }
        long endTime = System.currentTimeMillis();
        System.out.println("spend time : " + (endTime - startTime));
    }

    public void preheat(Sender sender,int preheatNumber) throws Exception {
        // preheat
        for (int i=0;i<preheatNumber;i++){
            sender.send(message.getBytes());
        }
    }

    public Sender getSender() throws Exception {
        CreateOptions createOptions = new CreateOptions();
        createOptions.setHost(host);
        createOptions.setPort(port);
        createOptions.setRequestedHeartbeat(5);
        createOptions.setDurable(false);
        createOptions.setSenderType(CreateOptions.SenderType.FANOUT);
        createOptions.setExchangeName("bond.trade");
        Sender sender = SenderFactory.newSender(createOptions);
        return sender;
    }
}
