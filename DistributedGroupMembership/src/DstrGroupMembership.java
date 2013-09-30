import java.awt.List;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import javax.xml.bind.JAXBException;
import java.io.IOException;

public class DstrGroupMembership {

    //Define the address of the contact-machine for the initialization
    //private static String contactIP = "192.168.56.101";	
    //private static int contactPort = 61233;
    //private static int TGossip = 1000;
    private static boolean running = true;
    private static Config conf;
    private static MembershipList ownList;
    private static final String configFileName = "gossip.conf";
	
    public static void main(String[] args) throws InterruptedException, JAXBException, SocketException, UnknownHostException {
        
        try {
            conf = new Config(configFileName);
        }
        catch (IOException e) {
            System.out.println("Failed to load config file: " + configFileName);
            return;
        }

        //Add our own machine to our local membershipList
        String myIP = conf.valueFor("bindIP"); //OwnIP.find().get(0);
        ownList = new MembershipList();
        ownList.add(myIP);
		
        //And also add the contact machine to out local membership
        //ownList.add(conf.valueFor("contactIP"));
        String contactIP = conf.valueFor("contactIP");
        int contactPort = Integer.parseInt(conf.valueFor("contactPort"));

        MembershipController.sendJoinGroup(contactIP, contactPort);


	
        ConnectionHandler connectionHandler = new ConnectionHandler(ownList, conf);
        Thread handlerThread = new Thread(connectionHandler, "Connection Handler");
        handlerThread.start();
                
        // Well, maybe it's unnecessary that a new machine sends a join-statement - it may just start to gossip. 
        // Since the own membershipentry is in the list which is sent out, other machines hear about the new machine anyway.
        //MembershipController.sendJoinGroup(contactIP, contactPort);
        
        while(running) {
            ownList.incrHeartbeatCounter(myIP);
            MembershipController.sendGossip(ownList, contactPort, myIP);
            Thread.sleep(Integer.parseInt(conf.valueFor("TGossip")));
        	
            //TODO implement a counter to resend sendJoinGroup after t seconds, if no membership-list is received
        }
		
        
        handlerThread.interrupt();
    }

}
