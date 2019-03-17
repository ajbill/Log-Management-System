package com.mycompany.kafkaconsumerapplication;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.json.simple.JSONObject;

public class Middleware {
    
    public static void main(String[] args) throws IOException {
        
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "test");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("auto.offset.reset", "latest");
 
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("blockchainLogs"));

        int logId = 1;
        String logCountString = new String();
        Log send;

            while (true) {
           
                ConsumerRecords<String, String> records = consumer.poll(1);
                
                for (ConsumerRecord<String, String> record : records) {

                    logCountString = String.valueOf(logId);
                    JSONObject jsonLog = new JSONObject();

                    send = new Log();
                    send.newLog("PC1", record.value(), logCountString);

                    logId ++;

                } 
            }        
    }
    
}
