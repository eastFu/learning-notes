package cn.gyyx.learning.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class MyProducer{

    private static final String KAFKA_BROKERS = "10.168.100.16:9093,10.168.100.17:9093,10.168.100.18:9093";

    protected KafkaProducer<String, String> producer;

    private final String topic;

    public MyProducer(String topicName) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_BROKERS);
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 1);
        props.put("client.id", "75a38bf8691d439c9636d9c54346bd2a");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        this.producer = new KafkaProducer<String, String>(props);
        this.topic = topicName;
    }


    public void doIt() throws InterruptedException {
        String msg = "{\"message\":\"2021-03-15T13:54:49+08:00\t/test/1.png\t200\t633\t65545\t-\t0.019\t0.019\tceph-sync-a.autohome.com.cn\t10.168.162.138\t10.28.5.78\t10.28.5.78:9000\t-\taws-sdk-java/1.11.336 Windows_10/10.0 Java_HotSpot(TM)_64-Bit_Server_VM/25.91-b15 java/1.8.0_91\t-\tnginx\t_\tmax-age=31104000\t-\t200\thttp\tPUT\t@-\ttest\"}";
        //消息实体

        ProducerRecord<String , String> record = null;
        for (int i = 0; i < 100; i++) {
            record = new ProducerRecord<String, String>(topic, msg);
            //发送消息
            producer.send(record);
            System.out.println(i);
            Thread.sleep(500);
        }
        producer.close();
    }

    public static void main(String[] args) throws InterruptedException {
        MyProducer producer = new MyProducer("testCephPutLog");
        producer.doIt();

    }
}
