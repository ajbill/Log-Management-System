
package Logs;


public abstract class Log {
    
    protected String originalLog;
    
    public void setOriginalLog( String originalLog ) {
        
        this.originalLog = originalLog;
        
    }
    
    public String getOriginalLog() {
        
        return this.originalLog;
        
    }

    //public ArrayList<Log> getCurrentLogs() {
    //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    //}
    
}
