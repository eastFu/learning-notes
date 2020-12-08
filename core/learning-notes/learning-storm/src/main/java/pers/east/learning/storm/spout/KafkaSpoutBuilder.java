package pers.east.learning.storm.spout;

import org.apache.storm.kafka.*;
import org.apache.storm.spout.SchemeAsMultiScheme;

public class KafkaSpoutBuilder {

    // zookeeper
    private static String zkStr = "";

    // 订阅kafka消息的topic
    private static String topic = "nginx_topic";

    // kafka消费者定义的groupId
    private static String groupId = "test";


    public static KafkaSpout createKafkaSpout() {

        BrokerHosts host = new ZkHosts(KafkaSpoutBuilder.zkStr);
        SpoutConfig config = new SpoutConfig(host, KafkaSpoutBuilder.topic, KafkaSpoutBuilder.zkStr, KafkaSpoutBuilder.groupId);

        config.scheme = new SchemeAsMultiScheme(new StringScheme());
        config.startOffsetTime = kafka.api.OffsetRequest.LatestTime();

        return new KafkaSpout(config);
    }
}
