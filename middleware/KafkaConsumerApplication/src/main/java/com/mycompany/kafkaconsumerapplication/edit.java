package com.mycompany.kafkaconsumerapplication;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
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
        props.put("auto.commit.interval.ms", "1");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("auto.offset.reset", "latest");
     
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("blockchainLogs"));
        
        
        int logId = 1;
        String logIdString = new String();
        //String url = new String("http://localhost:3000/api/AddNewLog");
        String response = new String();
        Log send;
        
        HttpURLConnection connection = null;
        try {
            URL url=new URL("http://localhost:3000/api/AddNewLog");
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json"); 
        
            while (true) {
           
                ConsumerRecords<String, String> records = consumer.poll(1);
                
                for (ConsumerRecord<String, String> record : records) {

                    OutputStreamWriter streamWriter = new OutputStreamWriter(connection.getOutputStream());
                    
                    logIdString = String.valueOf(logId);
                    JSONObject jsonLog = new JSONObject();
        
                    jsonLog.put("$class", "org1.andrew.lognetwork.AddNewLog");
                    jsonLog.put("deviceId", "PC1");
                    jsonLog.put("newLog", record.value());
                    jsonLog.put("logCount", logIdString);
                                        
                    streamWriter.write(jsonLog.toString());
                    streamWriter.flush();
                    
                    StringBuilder stringBuilder = new StringBuilder();
                    if (connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                        InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());
                        BufferedReader bufferedReader = new BufferedReader(streamReader);

                        while ((response = bufferedReader.readLine()) != null) {
                        stringBuilder.append(response + "\n");
                        }
                        bufferedReader.close();

                        System.out.println(stringBuilder.toString());

                    } else {

                        System.out.println(connection.getResponseMessage());

                    }
                    
                    logId ++;

       
                }
                
            }
            } catch (Exception exception){
            System.out.println(exception.toString());
            //Log.e("test", exception.toString());
            //return null;
        } finally {
            if (connection != null){
                connection.disconnect();
            }
        }
    }
    
    
    
}
