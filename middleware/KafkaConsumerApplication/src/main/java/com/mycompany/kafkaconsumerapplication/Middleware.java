package com.mycompany.kafkaconsumerapplication;

import java.util.Arrays;
import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.json.simple.JSONObject;

public class Middleware {
    
    public static void main(String[] args) {
        
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "test");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("auto.offset.reset", "latest");
     
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("blockchainLogs"));
        
        
        int logId = 1;
        String logIdString = new String();
        String url = new String("http://localhost:3000/api/AddNewLog");
        String response = new String();
        Log send;
        
        while (true) {
           
                ConsumerRecords<String, String> records = consumer.poll(100);
                
                for (ConsumerRecord<String, String> record : records) {

                    logIdString = String.valueOf(logId);
                    JSONObject jsonLog = new JSONObject();
        
                    jsonLog.put("$class", "org1.andrew.lognetwork.AddNewLog");
                    jsonLog.put("deviceId", "PC1");
                    jsonLog.put("newLog", record.value());
                    jsonLog.put("logCount", logIdString);
                    
                    send = new Log();
                   
                    
                    response = send.sendNewLog(url, jsonLog);
                    
                    logId ++;
                    
                    System.out.println(response);
        
       
                }
                
        }
    }
    
    
    
}
