import java.awt.List;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;

import javax.xml.bind.JAXBException;


public class DstrGroupMembership {

	//Define the address of the contact-machine for the initialization
	private static String contactIP = "192.168.56.101";	
	private static int contactPort = 61233;
	private static int TGossip = 1000;
	private static boolean running = true;
	
	public static void main(String[] args) throws InterruptedException, JAXBException, SocketException, UnknownHostException {

		//Add our own machine to our local membershipList
		String myIP = OwnIP.find().get(0);
		MembershipList.add(myIP);
		
		//And also add the contact machine to out local membership
		MembershipList.add(contactIP);
		
        ConnectionHandler connectionHandler = new ConnectionHandler();
        Thread handlerThread = new Thread(connectionHandler, "Connection Handler");
        handlerThread.start();
                
        // Well, maybe it's unnecessary that a new machine sends a join-statement - it may just start to gossip. 
        // Since the own membershipentry is in the list which is sent out, other machines hear about the new machine anyway.
        //MembershipController.sendJoinGroup(contactIP, contactPort);
        
        while(running) {
        	MembershipList.incrHeartbeatCounter();
        	MembershipController.sendGossip();
        	Thread.sleep(TGossip);
        	
        	//TODO implement a counter to resend sendJoinGroup after t seconds, if no membership-list is received
        }
		
        
        handlerThread.interrupt();
	}

}
