package com.mycompany.kafkaconsumerapplication;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.simple.JSONObject;

public class Log { 

    public void newLog(String deviceId, String log, String logCount) {
    
        HttpURLConnection connection = null;
    
        JSONObject jsonLog = new JSONObject();
    
        jsonLog.put("$class", "org1.andrew.lognetwork.AddNewLog");
        jsonLog.put("deviceId", deviceId);
        jsonLog.put("newLog", log);
        jsonLog.put("logCount", logCount);
    
        String response = new String();

        try {
                URL url=new URL("http://localhost:3000/api/AddNewLog");
                connection = (HttpURLConnection) url.openConnection();
                connection.setDoOutput(true);
                connection.setDoInput(true);
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestProperty("Accept", "application/json"); 
                connection.setRequestProperty("Connection", "Keep-Alive");

                OutputStreamWriter streamWriter = new OutputStreamWriter(connection.getOutputStream());
                streamWriter.write(jsonLog.toString());
                streamWriter.flush();
                streamWriter.close();
                StringBuilder stringBuilder = new StringBuilder();
                    if (connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                        InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());
                        BufferedReader bufferedReader = new BufferedReader(streamReader);

                        while ((response = bufferedReader.readLine()) != null) {
                            stringBuilder.append(response + "\n");
                        }
                            bufferedReader.close();
                            System.out.println(stringBuilder.toString());
                            streamReader.close();
                        } else {
                            System.out.println(connection.getResponseMessage());
                        }

        } catch (Exception exception){
                System.out.println(exception.toString());
        } finally {
                if (connection != null){
                    connection.disconnect();
                }
        }
    }  
}
