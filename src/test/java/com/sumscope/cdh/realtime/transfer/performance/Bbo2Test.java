package com.sumscope.cdh.realtime.transfer.performance;

import com.sumscope.cdh.sumscopemq4j.CreateOptions;
import com.sumscope.cdh.sumscopemq4j.Sender;
import com.sumscope.cdh.sumscopemq4j.SenderFactory;

import java.io.IOException;

/**
 * Created by liu.yang on 2017/10/12.
 */
public class Bbo2Test {
    private int port = 5672;
    private String host = "rabbitmq-cdh.dev.sumscope.com";
    private String message = "{\"AckMsgHead\":{\"exectime\":0.0,\"total\":1,\"retcode\":0,\"idx\":0},\"AckMsgBody\":{\"BusinessCode\":\"BOND\",\"IMQ_OUTCOME\":[{\"MS_OPERATE\":\"0\",\"MS_GOODS_CODE\":\"136061\",\"MS_BOND_KEY_LISTED_MARKET\":\"D0000402015FINSEB01SSE\",\"MS_BestVersion\":7095,\"MS_TYPE\":\"1\",\"MS_BOND_KEY\":\"D0000402015FINSEB01\",\"MS_BODY\":{\"MS_ofrFlagRelation\":\"0\",\"MS_ofrPriceDescription\":\"5000(*)+4054(*)+3000(*)+5000(*)\",\"MS_ofrOfferId\":\"bfae84ea50424da6841f35d4b5199a13\",\"MS_ofrVolume\":\"5000+4054+3000+5000\",\"MS_ofrExercise\":\"1\",\"MS_ofrFlagBargain\":\"1\",\"MS_ofrNetPrice\":\"97.982\",\"MS_ofrPrice\":\"4.60\",\"MS_ofrRebate\":\"0\",\"MS_ofrYield\":\"4.60\",\"MS_goodsCode\":\"136061\",\"MS_createTime\":1507516711},\"MS_COMPANY_ID\":\"1\",\"MS_CREATE_TIME\":1507516711,\"MS_Version\":32363,\"MS_MODIFY_TIME\":1507516711,\"MS_LISTED_MARKET\":\"SSE\"},{\"MS_OPERATE\":\"0\",\"MS_GOODS_CODE\":\"1480522\",\"MS_BOND_KEY_LISTED_MARKET\":\"J0003692014CORLEB01CIB\",\"MS_BestVersion\":7096,\"MS_TYPE\":\"1\",\"MS_BOND_KEY\":\"J0003692014CORLEB01\",\"MS_BODY\":{\"MS_ofrFlagRelation\":\"0\",\"MS_ofrPriceDescription\":\"4000(*)\",\"MS_ofrOfferId\":\"1998a80c436f4216bcf8718ce1142241\",\"MS_ofrVolume\":\"4000\",\"MS_ofrExercise\":\"1\",\"MS_ofrFlagBargain\":\"1\",\"MS_ofrNetPrice\":\"82.8908\",\"MS_ofrPrice\":\"5.65\",\"MS_ofrRebate\":\"0\",\"MS_ofrYield\":\"5.65\",\"MS_goodsCode\":\"1480522\",\"MS_createTime\":1507516711},\"MS_COMPANY_ID\":\"1\",\"MS_CREATE_TIME\":1507516711,\"MS_Version\":32364,\"MS_MODIFY_TIME\":1507516711,\"MS_LISTED_MARKET\":\"CIB\"},{\"MS_OPERATE\":\"0\",\"MS_GOODS_CODE\":\"127415\",\"MS_BOND_KEY_LISTED_MARKET\":\"S0008362016CORLEB01SSE\",\"MS_BestVersion\":7097,\"MS_TYPE\":\"1\",\"MS_BOND_KEY\":\"S0008362016CORLEB01\",\"MS_BODY\":{\"MS_ofrFlagRelation\":\"0\",\"MS_ofrPriceDescription\":\"5000(*)\",\"MS_ofrOfferId\":\"267bc98021de4aa896c42fc764f0cb9b\",\"MS_ofrVolume\":\"5000\",\"MS_ofrExercise\":\"1\",\"MS_ofrFlagBargain\":\"1\",\"MS_ofrNetPrice\":\"96.0628\",\"MS_ofrPrice\":\"4.95\",\"MS_ofrRebate\":\"0\",\"MS_ofrYield\":\"4.95\",\"MS_goodsCode\":\"127415\",\"MS_createTime\":1507516711},\"MS_COMPANY_ID\":\"1\",\"MS_CREATE_TIME\":1507516711,\"MS_Version\":32365,\"MS_MODIFY_TIME\":1507516711,\"MS_LISTED_MARKET\":\"SSE\"}]},\"seq\":336223}";

    public static void main(String[] args) throws Exception {
        Bbo2Test bbo2Test = new Bbo2Test();
        Sender sender = bbo2Test.getSender();

        //预热 编译
        bbo2Test.preheat(sender,1000);

        bbo2Test.run(sender,100000);
    }

    public void run(Sender sender,int number) throws Exception {

        long startTime = System.currentTimeMillis();
        for (int i=0;i<number;i++){
            sender.send(message.getBytes());
        }
        long endTime = System.currentTimeMillis();
        System.out.println("bbo_2 spend time => " + (endTime - startTime));
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
        createOptions.setExchangeName("bond.market.stream");
        Sender sender = SenderFactory.newSender(createOptions);
        return sender;
    }
}
