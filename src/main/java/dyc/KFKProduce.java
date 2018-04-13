package dyc;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import net.sf.json.JSONObject;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
//成都二枢 10.30.6.25这台机器我搭好了kafka server的测试环境。zookeeper地址10.30.6.25:2181，broker地址10.30.6.25:9092 
public class KFKProduce {

    public static void main(String[] args) {
        System.out.println("begin produce"+Calendar.getInstance().getTime());
        testloanAuditStatusRefuse();
        System.out.println("finish produce"+Calendar.getInstance().getTime());
    }

    public static void connectionKafka() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "10.30.7.14:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);
        Map<String,String> map = new HashMap<String, String>();
        map.put("status", "1");
        map.put("statusChangeTime", "2016-11-11 11:11:11");
        for (int i = 0; i < 1; i++) {
//            try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
            producer.send(new ProducerRecord<String, String>("loanAuditStatusChange", "46010201610180036", JSONObject.fromObject(map).toString()));    
        }
        producer.close();
    }

    public static void testloanStatusChange() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "10.30.7.14:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);
        Map<String,String> map = new HashMap<String, String>();
        map.put("status", "1");
        map.put("statusChangeTime", "2016-11-11 11:11:11");
        for (int i = 0; i < 2; i++) {
            producer.send(new ProducerRecord<String, String>("loanStatusChange", "test123", JSONObject.fromObject(map).toString()));    
        }
        producer.close();
    }
    

    public static void testloanAuditStatusRefuse() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "10.30.7.14:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);
        Map<String,String> map = new HashMap<String, String>();
        map.put("status", "1");
        map.put("loanNo", "14060201701090005");
        map.put("statusChangeTime", "2016-11-11 11:11:11");
        map.put("applyDate", "2016-11-11 11:11:11");
        map.put("saleNo", "23000001");
        map.put("applyAmt", "5000");
        map.put("chanSource", "wechat");
        map.put("notifyType", "back");
        map.put("backCode", "H0401");
        map.put("backCodeDesc", "附件身份证正面（不清晰/有误）");
        map.put("notifyType", "back");
        map.put("productType", "0");
        map.put("refuseCode", "12124");
        map.put("refuseCodeDesc", "不符合我公司的进件要求");
        map.put("certNo", "411722198806201718");
        try {
            for (int i = 0; i < 1; i++) {
//              try {
//  				Thread.sleep(1000);
//  			} catch (InterruptedException e) {
//  				e.printStackTrace();
//  			}
              producer.send(new ProducerRecord<String, String>("test", "14060201701090005", JSONObject.fromObject(map).toString()));    
          }
          producer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

    }
}