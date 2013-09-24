import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;


public class Supplier {

	
	public static void send(String ip, int port, String message) throws IOException {
		
	     byte[] msgtosend = message.getBytes();
	     InetAddress sendip = InetAddress.getByName(ip);
	      DatagramPacket packet = new DatagramPacket(msgtosend, msgtosend.length, sendip, port);
	      DatagramSocket dsocket = new DatagramSocket();
	      dsocket.send(packet);
	      dsocket.close();

	}
	
}
