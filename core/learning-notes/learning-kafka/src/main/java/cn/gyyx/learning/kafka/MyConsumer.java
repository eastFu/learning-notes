package cn.gyyx.learning.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Collections;
import java.util.Properties;

public class MyConsumer{

    private static final String KAFKA_BROKERS = "10.12.53.5:9092,10.12.53.6:9092,10.12.53.7:9092";

    private final KafkaConsumer<String, String> consumer;

    private ConsumerRecords<String, String> msgList;

    private final String topic;

    private static final String GROUP_ID = "groupB";

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

    public void doIt() {
        System.out.println("---------开始消费---------");
        while (true) {
            msgList = consumer.poll(100);
            for (ConsumerRecord<String, String> record : msgList) {
                System.out.println(String.format("topic:%s,offset:%d,消息:%s", //
                        record.topic(), record.offset(), record.value()));
            }
        }
        //consumer.close();
    }
}
