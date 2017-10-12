package com.sumscope.cdh.realtime.transfer.performance;

import com.sumscope.cdh.sumscopemq4j.Sender;

/**
 * Created by liu.yang on 2017/10/12.
 */
public class AllTest {
    public static void main(String[] args) throws Exception {
        Bbo1Test bbo1Test = new Bbo1Test();
        Bbo2Test bbo2Test = new Bbo2Test();
        TradeTest tradeTest = new TradeTest();

        Sender bbo1Sender = bbo1Test.getSender();
        Sender bbo2Sender = bbo2Test.getSender();
        Sender tradeSender = tradeTest.getSender();

        //预热 1000 次
        bbo1Test.preheat(bbo1Sender,1000);
        bbo2Test.preheat(bbo2Sender,1000);
        tradeTest.preheat(tradeSender,1000);


        // 发送 10000*10 次
        bbo1Test.run(bbo1Sender,10000*10);
        bbo2Test.run(bbo2Sender,10000*10);
        tradeTest.run(tradeSender,10000*10);
    }
}
