import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

//@XmlRootElement(name="entry")
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
    
    //@XmlElement(name="heartbeat")
    public int getHeartbeatCounter() {
    	return heartbeatCounter;
    }
    
    //@XmlElement(name="joinedtstamp")
    public long getJoinedtstamp() {
    	return joinedtstamp;
    }
    
    //@XmlElement(name="lastupdtstamp")
    public long getLastupdtstamp() {
    	return lastupdtstamp;
    }
    
    @XmlElement(name="ipAddress")
    public String getiPpAddress() {
    	return ipAddress;
    }
    
    @XmlElement(name="failedFlag")
    public boolean getFailedFlag() {
    	return failedFlag;
    }

}
