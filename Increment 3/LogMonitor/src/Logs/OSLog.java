
package Logs;

import Interfaces.LogMonitoring;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


public class OSLog extends Log implements LogMonitoring {

private int deviceId;
private String deviceName;

    
    public String getDeviceName() {
        
        return deviceName;
        
    }


@Override
    public void setLog( int deviceId ) {
        
        if ( deviceId == 1 ) {
            
            deviceId = 1;
            deviceName = "Server 1";
            setOriginalLog( "jdwkjd wjdijdoiw jxwijdwioj" );
            
        }   
    };
    
    public ArrayList<OSLog> getLogList( int deviceId ) {
        
        
        ArrayList<OSLog> listOfLogs = new ArrayList<OSLog>();
        
        // loop through database query
        
        OSLog log = new OSLog();
        log.setLog(1);
        
        listOfLogs.add(log);
        
        return listOfLogs;
    }
    

    
    
}
