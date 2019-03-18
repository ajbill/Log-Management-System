package com.mycompany.kafkaconsumerapplication;

import org.json.simple.JSONObject;


public class Test2 {

    public static void main(String[] args) {
        
        
        long startTime = System.currentTimeMillis();
        int logCount = 1;
        String logCountString = new String();
        Log send;
          
        while(true) {
            
        logCountString = String.valueOf(logCount);        
        long currentTime = System.currentTimeMillis();
        
                    if (currentTime - startTime >= 1000) {
                        
                        break;
                        
                    } else {  
                        
                        JSONObject jsonLog = new JSONObject();

                        send = new Log();
                        send.newLog(logCountString, logCountString);    
                        
                    }
        
            logCount ++;     
        } 
    }
}
