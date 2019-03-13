package com.mycompany.kafkaconsumerapplication;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
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
        String urlPath = new String("http://localhost:3000/api/AddNewLog");
        String response = new String();
        
        HttpURLConnection connection = null;
        
        try {
            
            URL url = new URL(urlPath);
                      
            
            
            while(true) {
                
                connection = (HttpURLConnection) url.openConnection();
                connection.setDoOutput(true);
                connection.setDoInput(true);
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestProperty("Accept", "application/json");
            
                long currentTime = System.currentTimeMillis();
                    if(currentTime - startTime >= 2000) {
                        
                        break;
                    } else {
                        
                        logIdString = String.valueOf(logId);
                        JSONObject jsonLog = new JSONObject();
            
                        jsonLog.put("$class", "org1.andrew.lognetwork.AddNewLog");
                        jsonLog.put("deviceId", "PC1");
                        jsonLog.put("newLog", "Milliseconds passed = " + currentTime);
                        jsonLog.put("logCount", logIdString);
            
                        OutputStreamWriter streamWriter = new OutputStreamWriter(connection.getOutputStream());
                        StringBuilder stringBuilder = new StringBuilder();
                        streamWriter.write(jsonLog.toString());
                        streamWriter.flush();
                        //streamWriter.close();
                    
                        logId ++;
                        
                        //if (connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                        //    InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());
                        //    BufferedReader bufferedReader = new BufferedReader(streamReader);
                            ///String response = null;
                        //    while ((response = bufferedReader.readLine()) != null) {
                        //        stringBuilder.append(response + "\n");
                        //    }
                        //    bufferedReader.close();

                        //       System.out.println(stringBuilder.toString());
                                ///return stringBuilder.toString();
                        //    } else {
                                ///Log.e("test", connection.getResponseMessage());
                        //    System.out.println(connection.getResponseMessage());
                                ///return null;
                        //    }
                    
                                //System.out.println(response);
                                //System.out.println(currentTime);
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
