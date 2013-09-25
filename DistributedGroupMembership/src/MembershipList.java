import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

public class MembershipList {
	
	private static ArrayList<MembershipEntry> membershipList = null;

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
	
}
