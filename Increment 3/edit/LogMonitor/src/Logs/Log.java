
package Logs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import org.json.simple.JSONObject;


public abstract class Log {
    
    protected String logs[];
    

    public void connectToAPI(String deviceId) {
   
    }
    
    public void getDeviceLogs( String deviceId ) {
        
        
        String url = new String("http://localhost:3000/api/DeviceLogHistory");
        JSONObject jsonLog = new JSONObject();
        
        jsonLog.put("$class", "org1.andrew.lognetwork.LoggingDevice");
        jsonLog.put("deviceId", "PC1");

        
                     send = new Log();
                     
        HttpURLConnection connection = null;
        try {
            URL url=new URL(urlpath);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            OutputStreamWriter streamWriter = new OutputStreamWriter(connection.getOutputStream());
            streamWriter.write(json.toString());
            streamWriter.flush();
            StringBuilder stringBuilder = new StringBuilder();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(streamReader);
                String response = null;
                while ((response = bufferedReader.readLine()) != null) {
                    stringBuilder.append(response + "\n");
                }
                bufferedReader.close();

                System.out.println(stringBuilder.toString());
                return stringBuilder.toString();
            } else {
                //Log.e("test", connection.getResponseMessage());
                System.out.println(connection.getResponseMessage());
                return null;
            }
        } catch (Exception exception){
            System.out.println(exception.toString());
            //Log.e("test", exception.toString());
            return null;
        } finally {
            if (connection != null){
                connection.disconnect();
            }
        }             
                     
                     
        
                    
    }

    
    public ArrayList<OSLog> getLogList( int deviceId ) {
        
        
        ArrayList<OSLog> listOfLogs = new ArrayList<OSLog>();
        
        // loop through database query
        
        OSLog log = new OSLog();
        log.setLog(1);
        
        listOfLogs.add(log);
        
        return listOfLogs;
    }

    //public ArrayList<Log> getCurrentLogs() {
    //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    //}
    
}
