package Logs;


public class OSLog extends Log{

    
private String deviceId;

    public void setDeviceId( String deviceId ) {    
        this.deviceId = deviceId;
    }
    

    public String getDeviceId() {
        return deviceId;
    }  
}
