package dyc;

import java.util.Arrays;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class KFKConsumer {

    public static void main(String[] args) {
        System.out.println("begin consumer");
        connectionKafka();
        System.out.println("finish consumer");
    }

    @SuppressWarnings("resource")
    public static void connectionKafka() {

        Properties props = new Properties();
        props.put("bootstrap.servers", "10.30.7.14:9092");
        Random r = new Random();
        props.put("group.id", "testConsumer2");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("auto.offset.reset", "earliest");
        
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, Map<String,String>> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("loanAuditStatusChange"));
        while (true) {
            ConsumerRecords<String, Map<String,String>> records = consumer.poll(100);
            for (ConsumerRecord<String, Map<String,String>> record : records) {
                System.out.printf("offset = %d, key = %s, value = %s \n", record.offset(), record.key(), record.value());
            }
        }
    }
}