package com.sumscope.cdh.realtime.transfer.performance;

import com.sumscope.cdh.sumscopemq4j.CreateOptions;
import com.sumscope.cdh.sumscopemq4j.Sender;
import com.sumscope.cdh.sumscopemq4j.SenderFactory;

/**
 * Created by liu.yang on 2017/10/12.
 */
public class Bbo1Test {

    private int port = 5672;
    private String host = "rabbitmq-cdh.dev.sumscope.com";
    private String message = "{\"AckMsgHead\":{\"exectime\":0.0,\"total\":1,\"retcode\":0,\"idx\":0},\"AckMsgBody\":{\"BusinessCode\":\"BOND\",\"IMQ_OUTCOME\":[{\"fbar\":\"1\",\"vol\":\"1000\",\"pri\":\"4.80\",\"fbad\":\"false\",\"gc\":\"101558056\",\"id\":\"6196f91d93de4c7ea8ba55d281c9773d\",\"cn\":\"\\u56fd\\u5229\",\"ps\":\"1\",\"ver\":7109,\"lm\":\"CIB\",\"reb\":\"0\",\"ct\":1507516774,\"fr\":\"0\",\"fv\":\"false\",\"bklm\":\"D0001152015CORLMN01CIB\",\"yd\":\"4.80\",\"sym\":\"-1\",\"bk\":\"D0001152015CORLMN01\",\"np\":\"98.1655\",\"ds\":\"0\",\"qt\":\"3\",\"cid\":\"1\",\"pdesc\":\"1000(*)\",\"mt\":1507516774,\"sts\":\"1\",\"gsn\":\"15\\u5927\\u8fde\\u5efa\\u6295MTN001\"},{\"fbar\":\"1\",\"vol\":\"2000\",\"pri\":\"5.25\",\"fbad\":\"false\",\"gc\":\"101580014\",\"id\":\"45e771d30dd34f7aa5c80001ea96dd75\",\"cn\":\"\\u56fd\\u5229\",\"ps\":\"1\",\"ver\":7110,\"lm\":\"CIB\",\"reb\":\"0\",\"ct\":1507516774,\"fr\":\"0\",\"fv\":\"false\",\"bklm\":\"J0001532015CORLMN02CIB\",\"yd\":\"5.25\",\"sym\":\"-1\",\"bk\":\"J0001532015CORLMN02\",\"np\":\"99.2529\",\"ds\":\"0\",\"qt\":\"3\",\"cid\":\"1\",\"pdesc\":\"2000(*)\",\"mt\":1507516774,\"sts\":\"1\",\"gsn\":\"15\\u666f\\u56fd\\u8d44MTN002\"},{\"fbar\":\"1\",\"vol\":\"1000\",\"pri\":\"4.75\",\"fbad\":\"false\",\"gc\":\"101561019\",\"id\":\"382face332f244aaa88fa879430921db\",\"cn\":\"\\u56fd\\u5229\",\"ps\":\"1\",\"ver\":7111,\"lm\":\"CIB\",\"reb\":\"0\",\"ct\":1507516774,\"fr\":\"0\",\"fv\":\"false\",\"bklm\":\"J0004292015CORLMN01CIB\",\"yd\":\"4.75\",\"sym\":\"-1\",\"bk\":\"J0004292015CORLMN01\",\"np\":\"100.8851\",\"ds\":\"0\",\"qt\":\"3\",\"cid\":\"1\",\"pdesc\":\"1000(*)\",\"mt\":1507516774,\"sts\":\"1\",\"gsn\":\"15\\u91d1\\u534e\\u57ce\\u5efaMTN001\"},{\"fbar\":\"1\",\"vol\":\"2000\",\"pri\":\"5.00\",\"fbad\":\"false\",\"gc\":\"101754055\",\"id\":\"b578391f8e104c03a5fff35f86364ea2\",\"cn\":\"\\u56fd\\u5229\",\"ps\":\"1\",\"ver\":7112,\"lm\":\"CIB\",\"reb\":\"0\",\"ct\":1507516774,\"fr\":\"0\",\"fv\":\"false\",\"bklm\":\"L0001602017CORLMN03CIB\",\"yd\":\"5.00\",\"sym\":\"-1\",\"bk\":\"L0001602017CORLMN03\",\"np\":\"101.1205\",\"ds\":\"0\",\"qt\":\"3\",\"cid\":\"1\",\"pdesc\":\"2000(*)\",\"mt\":1507516774,\"sts\":\"1\",\"gsn\":\"17\\u7eff\\u57ce\\u623f\\u4ea7MTN003\"},{\"fbar\":\"1\",\"vol\":\"3000\",\"pri\":\"4.40\",\"fbad\":\"false\",\"gc\":\"1282399\",\"id\":\"74ec8abf949646919cf5bf635d419cc2\",\"cn\":\"\\u56fd\\u5229\",\"ps\":\"1\",\"ver\":7113,\"lm\":\"CIB\",\"reb\":\"0\",\"ct\":1507516774,\"fr\":\"0\",\"fv\":\"false\",\"bklm\":\"N0000442012CORLMN02CIB\",\"yd\":\"4.399\",\"sym\":\"-1\",\"bk\":\"N0000442012CORLMN02\",\"np\":\"100.0149\",\"ds\":\"0\",\"qt\":\"3\",\"cid\":\"1\",\"pdesc\":\"3000(*)\",\"mt\":1507516774,\"sts\":\"1\",\"gsn\":\"12\\u5357\\u65b0\\u5de5MTN2\"},{\"fbar\":\"1\",\"vol\":\"1000\",\"pri\":\"4.70\",\"fbad\":\"false\",\"gc\":\"101456043\",\"id\":\"f562215ce3db43d09f002a5f357bcbf4\",\"cn\":\"\\u56fd\\u5229\",\"ps\":\"1\",\"ver\":7114,\"lm\":\"CIB\",\"reb\":\"0\",\"ct\":1507516774,\"fr\":\"0\",\"fv\":\"false\",\"bklm\":\"N0000652014CORLMN01CIB\",\"yd\":\"4.70\",\"sym\":\"-1\",\"bk\":\"N0000652014CORLMN01\",\"np\":\"102.7519\",\"ds\":\"0\",\"qt\":\"3\",\"cid\":\"1\",\"pdesc\":\"1000(*)\",\"mt\":1507516774,\"sts\":\"1\",\"gsn\":\"14\\u5b81\\u4ea4\\u6295MTN001\"},{\"fbar\":\"1\",\"vol\":\"2000\",\"pri\":\"5.45\",\"fbad\":\"false\",\"gc\":\"101663013\",\"id\":\"d6736273574e49689d3be9b57b860e5d\",\"cn\":\"\\u56fd\\u5229\",\"ps\":\"1\",\"ver\":7115,\"lm\":\"CIB\",\"reb\":\"0\",\"ct\":1507516774,\"fr\":\"0\",\"fv\":\"false\",\"bklm\":\"N0000982016CORLMN01CIB\",\"yd\":\"5.45\",\"sym\":\"-1\",\"bk\":\"N0000982016CORLMN01\",\"np\":\"96.5794\",\"ds\":\"0\",\"qt\":\"3\",\"cid\":\"1\",\"pdesc\":\"2000(*)\",\"mt\":1507516774,\"sts\":\"1\",\"gsn\":\"16\\u5175\\u56e2\\u4e8c\\u5e08MTN001\"},{\"fbar\":\"1\",\"vol\":\"3000\",\"pri\":\"5.00\",\"fbad\":\"false\",\"gc\":\"101558012\",\"id\":\"ec2c8ffc5c3542139db053a9c89a8c20\",\"cn\":\"\\u56fd\\u5229\",\"ps\":\"1\",\"ver\":7116,\"lm\":\"CIB\",\"reb\":\"0\",\"ct\":1507516774,\"fr\":\"0\",\"fv\":\"false\",\"bklm\":\"S0001732015CORLMN02CIB\",\"yd\":\"5.00\",\"sym\":\"-1\",\"bk\":\"S0001732015CORLMN02\",\"np\":\"100.0533\",\"ds\":\"0\",\"qt\":\"3\",\"cid\":\"1\",\"pdesc\":\"3000(*)\",\"mt\":1507516774,\"sts\":\"1\",\"gsn\":\"15\\u534e\\u5f3aMTN002\"},{\"fbar\":\"1\",\"vol\":\"3000\",\"pri\":\"5.00\",\"fbad\":\"false\",\"gc\":\"101575020\",\"id\":\"79003970f3a74142bd6a9cffc86b707c\",\"cn\":\"\\u56fd\\u5229\",\"ps\":\"1\",\"ver\":7117,\"lm\":\"CIB\",\"reb\":\"0\",\"ct\":1507516774,\"fr\":\"0\",\"fv\":\"false\",\"bklm\":\"Y0000392015CORLMN01CIB\",\"yd\":\"5.00\",\"sym\":\"-1\",\"bk\":\"Y0000392015CORLMN01\",\"np\":\"98.3897\",\"ds\":\"0\",\"qt\":\"3\",\"cid\":\"1\",\"pdesc\":\"3000(*)\",\"mt\":1507516774,\"sts\":\"1\",\"gsn\":\"15\\u5b9c\\u6625\\u57ce\\u6295MTN001\"},{\"fbar\":\"1\",\"vol\":\"5000\",\"pri\":\"5.05\",\"fbad\":\"false\",\"gc\":\"101472008\",\"id\":\"942cf5db596847789b3995077ea0da3a\",\"cn\":\"\\u56fd\\u5229\",\"ps\":\"1\",\"ver\":7118,\"lm\":\"CIB\",\"reb\":\"0\",\"ct\":1507516774,\"fr\":\"0\",\"fv\":\"false\",\"bklm\":\"Y0001192014CORLMN01CIB\",\"yd\":\"5.05\",\"sym\":\"-1\",\"bk\":\"Y0001192014CORLMN01\",\"np\":\"103.0659\",\"ds\":\"0\",\"qt\":\"3\",\"cid\":\"1\",\"pdesc\":\"5000(*)\",\"mt\":1507516774,\"sts\":\"1\",\"gsn\":\"14\\u96c5\\u5b89\\u53d1\\u5c55MTN001\"},{\"fbar\":\"1\",\"vol\":\"2000\",\"pri\":\"4.45\",\"fbad\":\"false\",\"gc\":\"1382161\",\"id\":\"df560624998c4ecab70e0c5447d37625\",\"cn\":\"\\u56fd\\u5229\",\"ps\":\"1\",\"ver\":7119,\"lm\":\"CIB\",\"reb\":\"0\",\"ct\":1507516774,\"fr\":\"0\",\"fv\":\"false\",\"bklm\":\"Z0003862013CORLMN01CIB\",\"yd\":\"4.4501\",\"sym\":\"-1\",\"bk\":\"Z0003862013CORLMN01\",\"np\":\"100.2553\",\"ds\":\"0\",\"qt\":\"3\",\"cid\":\"1\",\"pdesc\":\"2000(*)\",\"mt\":1507516774,\"sts\":\"1\",\"gsn\":\"13\\u6e1d\\u6c34\\u6295MTN1\"},{\"fbar\":\"1\",\"vol\":\"2000\",\"pri\":\"4.70\",\"fbad\":\"false\",\"gc\":\"101458029\",\"id\":\"78279c91422a4788afd33fad121f4c25\",\"cn\":\"\\u56fd\\u5229\",\"ps\":\"1\",\"ver\":7120,\"lm\":\"CIB\",\"reb\":\"0\",\"ct\":1507516774,\"fr\":\"0\",\"fv\":\"false\",\"bklm\":\"Z0009052014CORLMN01CIB\",\"yd\":\"4.70\",\"sym\":\"-1\",\"bk\":\"Z0009052014CORLMN01\",\"np\":\"102.216\",\"ds\":\"0\",\"qt\":\"3\",\"cid\":\"1\",\"pdesc\":\"2000(*)\",\"mt\":1507516774,\"sts\":\"1\",\"gsn\":\"14\\u73e0\\u6d77\\u534e\\u53d1MTN001\"}]},\"seq\":336460}";

    public static void main(String[] args) throws Exception {
        Bbo1Test bbo1Test = new Bbo1Test();
        Sender sender = bbo1Test.getSender();

        // 预热 编译
        bbo1Test.preheat(sender,1000);

        bbo1Test.run(sender,100000);
    }

    public void run(Sender sender,int number) throws Exception {

        long startTime = System.currentTimeMillis();
        for (int i=0;i<number;i++){
            sender.send(message.getBytes());
        }
        long endTime = System.currentTimeMillis();
        System.out.println("bbo_1 spend time => " + (endTime - startTime));
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
        createOptions.setExchangeName("bond.best.offer");
        Sender sender = SenderFactory.newSender(createOptions);
        return sender;
    }
}
