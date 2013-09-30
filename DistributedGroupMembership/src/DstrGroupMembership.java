import java.awt.List;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import javax.xml.bind.JAXBException;
import java.io.IOException;

public class DstrGroupMembership {

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
        String myIP = conf.valueFor("bindIP");
        ownList = new MembershipList();
        //ownList.add(myIP);
		
        //And also add the contact machine to out local membership
        String contactIP = conf.valueFor("contactIP");
        int contactPort = conf.intFor("contactPort");

        ConnectionHandler connectionHandler = new ConnectionHandler(ownList, conf);
        Thread handlerThread = new Thread(connectionHandler, "Connection Handler");
        handlerThread.start();


        //MembershipController.sendJoinGroup(contactIP, contactPort);
        
        while(running) {
            ownList.incrHeartbeatCounter(myIP);
            MembershipController.trackFailing(ownList, conf.intFor("TFail")/1000);
            MembershipController.sendGossip(ownList, contactIP, contactPort, myIP);
            Thread.sleep(conf.intFor("TGossip"));
        }
		
        
        handlerThread.interrupt();
    }

}
