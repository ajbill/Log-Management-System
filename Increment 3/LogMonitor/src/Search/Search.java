package Search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class Search {
    
    protected String deviceId;
    
    
    public Search( String deviceId ) {
        
        this.deviceId = deviceId;
        
    } 
    
    public List<String> getDeviceLogs( String deviceId ) {
        
        
        String urlPath = new String("http://localhost:3000/api/DeviceLogHistory");
        JSONObject jsonRequest = new JSONObject();
        
        jsonRequest.put("$class", "org1.andrew.lognetwork.DeviceLogHistory");
        jsonRequest.put("deviceId", deviceId);

        List<String> logs = new ArrayList<String>();
                     
        HttpURLConnection connection = null;
        
        try {
            
            URL url = new URL(urlPath);
            
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            
            OutputStreamWriter streamWriter = new OutputStreamWriter(connection.getOutputStream());
            streamWriter.write(jsonRequest.toString());
            streamWriter.flush();
            StringBuilder stringBuilder = new StringBuilder();
            
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(streamReader);
                String response = null;
                while ((response = bufferedReader.readLine()) != null) {
                    System.out.println(response);
                    JSONParser parser = new JSONParser();
                    Object jsonObj = parser.parse(response);
                    JSONArray jsonLogs = (JSONArray) jsonObj;
                    for (int i = 0; i < jsonLogs.size(); i++) {
                        logs.add(jsonLogs.get(i).toString());
                    }
                    
                }
                bufferedReader.close();
                System.out.println(stringBuilder.toString());
                return logs;
            } else {
                System.out.println(connection.getResponseMessage());
                return null;
            }
        } catch (Exception exception){
            System.out.println(exception.toString());
            return null;
        } finally {
            if (connection != null){
                connection.disconnect();
            }
        }      
    }
}
