package cn.gyyx.learning.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Properties;

public class MyConsumer{

    private static final String KAFKA_BROKERS = "storm.demo.cn:9092";

    private final KafkaConsumer<String, String> consumer;

    private ConsumerRecords<String, String> msgList;

    private final String topic;

    private static final String GROUP_ID = "test111";

    public MyConsumer(String topicName) {
        Properties props = new Properties();
        props.put("bootstrap.servers", KAFKA_BROKERS);
        props.put("group.id", GROUP_ID);
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("auto.offset.reset", "latest");
        props.put("key.deserializer", StringDeserializer.class.getName());
        props.put("value.deserializer", StringDeserializer.class.getName());
        this.consumer = new KafkaConsumer<String, String>(props);
        this.topic = topicName;
        this.consumer.subscribe(Collections.singletonList(topic));
    }


    public void doIt() throws InterruptedException {
        System.out.println("---------开始消费---------");
        while (true) {
            msgList = consumer.poll(5);
            for (ConsumerRecord<String, String> record : msgList) {
//                record.headers().toString();
                System.out.println(getNowTime()+"  "+ record.timestamp()+"    "+getBeforeDateTime(record.timestamp())+String.format("    Consumer receive a msg --- topic:%s,offset:%d,消息:%s", //
                        record.topic(), record.offset(), record.value()));
//                TimeUnit.SECONDS.sleep(2);
            }
        }
        //consumer.close();
    }

    // 获取几分钟前的时间字符串
    public static String getNowTime(){
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return df.format(LocalDateTime.now(ZoneOffset.ofHours(8)));
    }

    // 获取给定时间字符串的时间点的几分钟前
    public static String getBeforeDateTime(long timeStr){
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime before = LocalDateTime.ofEpochSecond(timeStr/1000, 0, ZoneOffset.ofHours(8));
        return df.format(before);
    }
}
