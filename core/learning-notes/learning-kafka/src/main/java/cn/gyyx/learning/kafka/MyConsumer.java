package cn.gyyx.learning.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class MyConsumer{

    private static final String KAFKA_BROKERS = "storm-cluster1.gyyx.cn:9092,storm-cluster2.gyyx.cn:9092,storm-cluster3.gyyx.cn:9092,storm-cluster4.gyyx.cn:9092,storm-cluster5.gyyx.cn:9092";

    private final KafkaConsumer<String, String> consumer;

    private ConsumerRecords<String, String> msgList;

    private final String topic;

    private static final String GROUP_ID = "group111";

    public MyConsumer(String topicName) {
        Properties props = new Properties();
        props.put("bootstrap.servers", KAFKA_BROKERS);
        props.put("group.id", GROUP_ID);
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("auto.offset.reset", "earliest");
        props.put("key.deserializer", StringDeserializer.class.getName());
        props.put("value.deserializer", StringDeserializer.class.getName());
        this.consumer = new KafkaConsumer<String, String>(props);
        this.topic = topicName;
        this.consumer.subscribe(Collections.singletonList(topic));
    }

    public void doIt() throws InterruptedException {
        System.out.println("---------开始消费---------");
        while (true) {
            msgList = consumer.poll(100);
            for (ConsumerRecord<String, String> record : msgList) {
                System.out.println(String.format("Consumer receive a msg --- topic:%s,offset:%d,消息:%s", //
                        record.topic(), record.offset(), record.value()));
                TimeUnit.SECONDS.sleep(2);
            }
        }
        //consumer.close();
    }
}
