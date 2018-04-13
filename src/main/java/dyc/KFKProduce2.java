package dyc;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import net.sf.json.JSONObject;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
//成都二枢 10.30.6.25这台机器我搭好了kafka server的测试环境。zookeeper地址10.30.6.25:2181，broker地址10.30.6.25:9092 
public class KFKProduce2 {

    public static void main(String[] args) {
        System.out.println("begin produce");
        try {
			connectionKafka();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("finish produce");
    }

    public static void connectionKafka() throws IOException {
        Properties props = new Properties();
        props.put("bootstrap.servers", "10.40.10.222:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.load(ClassLoader.getSystemResourceAsStream("kafka-producer.properties"));
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Producer<String, String> producer = new KafkaProducer<>(props);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("loanNo", "46000201708080005");
//		map.put("certNo", currentTask.getCertNo());
//		map.put("custName", currentTask.getCustName());
//		map.put("saleNo", currentTask.getSaleNo());
//		map.put("applyAmt", currentTask.getApplyAmt());
//		map.put("mthRepayAmt", currentTask.getMthRepayAmt());
//		map.put("firRepayDate", firRepayDate);
//		map.put("installNum", currentTask.getInstallNum());
//		map.put("applyDate", applyDate);
//		map.put("chanSource", currentTask.getChanSource());
		map.put("notifyType", "pass");
//		map.put("backCode", currentTask.getBackCode());
//		map.put("backCodeDesc", currentTask.getBackCodeDesc());
//		map.put("refuseCode", currentTask.getRefuseCode());
//		map.put("refuseCodeDesc", currentTask.getRefuseCodeDesc());
		map.put("statusChangeTime", dateformat.format(new Date()));
//		map.put("remark", currentTask.getRemark());
		// 贷款产品类型。=0或不填表示POS贷，=1表示现金贷.。(类型为：整型)

        producer.send(new ProducerRecord<String, String>("loanAuditStatusChange", "46000201708080005", JSONObject.fromObject(map).toString()));    

        producer.close();
    }

}