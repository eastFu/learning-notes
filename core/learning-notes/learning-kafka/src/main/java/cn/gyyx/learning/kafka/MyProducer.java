package cn.gyyx.learning.kafka;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class MyProducer{

    private static final String KAFKA_BROKERS = "10.12.53.5:9092,10.12.53.6:9092,10.12.53.7:9092";

    protected KafkaProducer<String, String> producer;

    private final String topic;

    public MyProducer(String topicName) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_BROKERS);
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        this.producer = new KafkaProducer<String, String>(props);
        this.topic = topicName;
    }

    
    public void doIt() throws InterruptedException {
        //消息实体
        ProducerRecord<String , String> record = null;
        for (int i = 0; i < 100; i++) {
            record = new ProducerRecord<String, String>(topic, "value"+i);
            //发送消息
            producer.send(record);
            System.out.println(i);
            Thread.sleep(500);
        }
        producer.close();
    }
}
