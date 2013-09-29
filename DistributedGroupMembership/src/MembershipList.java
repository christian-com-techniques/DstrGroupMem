import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "membershipList")
public class MembershipList {
	
    @XmlElement(name = "membershipentry", type = MembershipEntry.class)
    private static ArrayList<MembershipEntry> membershipList = null;

    public MembershipList() { }
	
    public MembershipList(ArrayList<MembershipEntry> me) {
        this.membershipList = me;
    }
	
    public static void add(String ip) {
		
        if(membershipList == null) {
            membershipList = new ArrayList<MembershipEntry>();
        }
		
        MembershipEntry mE = new MembershipEntry(ip);
        membershipList.add(mE);
    }

    public static ArrayList<MembershipEntry> get() {
        return membershipList;
    }
	
    public static void incrHeartbeatCounter() throws SocketException, UnknownHostException {
        ArrayList<String> ownIPs = OwnIP.find();
		
        for(int i=0;i<membershipList.size();i++) {
            if(membershipList.get(i).getIPAddress().equals(ownIPs.get(0))) {
                membershipList.get(i).incrHeartbeatCount();
                break;
            }
        }
    }
	
}
