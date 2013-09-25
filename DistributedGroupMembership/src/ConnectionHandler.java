
import java.io.IOException;
import java.io.StringReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


public class ConnectionHandler implements Runnable {

    private boolean shouldRun = true;
    private int port = 61233;
    private int bufferSize = 2048;

    public ConnectionHandler() {
    }
    
    public ConnectionHandler(int port, int bufferSize) {
    	this.port = port;
    	this.bufferSize = bufferSize;
    }

    public void kill() {
        this.shouldRun = false;
    }

    public void run() {

    	
    	
    	
    	DatagramSocket rcvSocket = null;
		try {
			rcvSocket = new DatagramSocket(port);
		} catch (SocketException e1) {
			System.out.println("Can't listen on port "+port);
		}
    	byte[] buffer = new byte[bufferSize];
    	DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        System.out.println("Waiting for UDP packets: Started");

        while(shouldRun) {
        	try {
				rcvSocket.receive(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}

        	String msg = new String(buffer, 0, packet.getLength());
            System.out.println(packet.getAddress().getHostAddress()+ "\n: "+ msg);
            
            
            InputSource source = new InputSource(new StringReader(msg));

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db;
            Element a = null;
            
			try {
				db = dbf.newDocumentBuilder();
	            Document doc = db.parse(source);
	            a = doc.getDocumentElement();
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
            
            
			//System.out.println("Element "+a.getNodeName());

			//ArrayList<MembershipEntry> membershipList = MembershipList.getmembershipList();
			//System.out.println(membershipList.size());
			
			
            if(a.getNodeName() == "join") {
            	
            } else if(a.getNodeName() == "leave") {
            	
            } else if(a.getNodeName() == "membershipList") {
            	
            }
            
            
            

        }

        System.out.println("[" + this.getClass().toString() + "] is dying.");
    }

}