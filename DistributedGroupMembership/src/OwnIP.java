import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;


public class OwnIP {

	private static ArrayList<String> IpsOnThisComputer = new ArrayList<String>();
	
	//Returns a list of all IP address of all interface of this computer
	public static ArrayList<String> find() throws SocketException, UnknownHostException {
		
		IpsOnThisComputer = new ArrayList<String>();
		
        Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
        for (NetworkInterface netint : Collections.list(nets)) {
            Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
            for (InetAddress inetAddress : Collections.list(inetAddresses)) {
            	IpsOnThisComputer.add((inetAddress).toString().substring(1));
            }
        }

        //Remove IPv6 addresses to make things easier for us
        for(int i=0;i<IpsOnThisComputer.size();i++) {	
        	if(IpsOnThisComputer.get(i).length() > 15) {
        		IpsOnThisComputer.remove(i);
        	}
        }

		return IpsOnThisComputer;
	}
	
}
