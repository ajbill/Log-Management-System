package com.mycompany.kafkaconsumerapplication;

import org.json.simple.JSONObject;

/**
 *
 * @author andrew
 */
public class TPS {
    
    public static void main(String[] args) {
        
        long startTime = System.currentTimeMillis();
        int logId = 1;
        String logIdString = new String();
        String url = new String("http://localhost:3000/api/AddNewLog");
        String response = new String();
        Log send;
        
        while(true) {
            
            long currentTime = System.currentTimeMillis();
            if(currentTime - startTime >= 1000) {
                break;
            } 
            else {
                logIdString = String.valueOf(logId);
                JSONObject jsonLog = new JSONObject();
            
                jsonLog.put("$class", "org1.andrew.lognetwork.AddNewLog");
                jsonLog.put("deviceId", "PC1");
                jsonLog.put("newLog", "Milliseconds passed = " + currentTime);
                jsonLog.put("logCount", logIdString);
            
                send = new Log();
            
                response = send.sendNewLog(url, jsonLog);
                    
                logId ++;
                    
                System.out.println(response);
                System.out.println(currentTime);
            }
        }    
    }     
}
