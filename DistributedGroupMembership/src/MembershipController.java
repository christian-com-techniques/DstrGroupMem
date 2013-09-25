import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.xml.bind.JAXBException;




public class MembershipController {

	private static int contactPort = 61233;
	
	public static void sendJoinGroup(String contactIP, int contactPort) throws JAXBException {
		
		String msg = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<join></join>\n";
        
        try {
			Supplier.send(contactIP, contactPort, msg);
		} catch (IOException e) {
			e.printStackTrace();
		}    
	}
	
	public static void sendLeaveGroup() {	
	}
	
	public static void sendGossip() throws SocketException, UnknownHostException {
		
		ArrayList<MembershipEntry> memList = MembershipList.get();
		
		//There can be multiple IPs on the computer. We assume that the first IP OwnIP gets is the interface which is connected to the LAN
		ArrayList<String> ownIPs = OwnIP.find();
		
		//Randomly pick nodes to send the gossip to but avoid our own IP in ownIPs.get(0)
		for(int i = 0;i < memList.size()/2;i++) {
			int randNum = (int)(Math.random() * ((memList.size()-1) + 1));
			
			MembershipEntry mE = memList.get(randNum);
			String contactIP = mE.getIPAddress();
			
			if(contactIP == ownIPs.get(0)) {
				i = i-1;
				continue;
			}
			
			String marshalledMessage = DstrMarshaller.toXML(memList);
			
	        try {
				Supplier.send(contactIP, contactPort, marshalledMessage);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void incrementHeartbeat() {
	}
	
}
