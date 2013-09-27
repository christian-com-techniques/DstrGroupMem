import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "membershipentry")
public class MembershipEntry {
    private int heartbeatCounter;
    private long joinedtstamp;
    private long lastupdtstamp;
    private String ipAddress;
    boolean failedFlag;

    public MembershipEntry() {
    	
    }
    
    public MembershipEntry(String ipAddress) {
        this.heartbeatCounter = 0;
        this.joinedtstamp = new Date().getTime()/1000;
        this.lastupdtstamp = this.joinedtstamp;
        this.ipAddress = ipAddress;
        this.failedFlag = false;
    }
    
    public int getHeartbeatCounter() {
    	return heartbeatCounter;
    }
    
    public long getJoinedtstamp() {
    	return joinedtstamp;
    }
    
    public long getLastupdtstamp() {
    	return lastupdtstamp;
    }
    
    public String getIPAddress() {
    	return ipAddress;
    }
    
    public boolean getFailedFlag() {
    	return failedFlag;
    }
    
    public void incrHeartbeatCount() {
    	heartbeatCounter++;
    }
    
    public void setFailedFlag(boolean failed) {
    	failedFlag = failed;
    }
    
    public void setHeartbeat(int heartbeatCounter) {
    	this.heartbeatCounter = heartbeatCounter;
    }
    
    public void setLastUpdTstamp(long lastupdtstamp) {
    	this.lastupdtstamp = lastupdtstamp;
    }

}
