
package Interfaces;

import Logs.OSLog;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author abillington1
 */
public interface LogMonitor {
    
    public void setLog( int deviceId );
    
    public ArrayList<OSLog> getLogList( int deviceId );
    
    //public ArrayList<ReviewComment> getCompanyReviewsList(String companyId);
    
    //public String getUserReviewRating(String companyId);
    
}
