import java.io.IOException;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;




public class MembershipController {

	
	
	public static void sendJoinGroup(String contactIP, int contactPort) throws JAXBException {
		
		/*
		JAXBContext jc = JAXBContext.newInstance(MembershipList.class);
        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        StringWriter sw = new StringWriter();
        marshaller.marshal(memList, sw);
        */
        
        try {
			Supplier.send(contactIP, contactPort, sw.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
        
	}
	
	public static void sendLeaveGroup() {
		
	}
	
	public static void sendGossip() {
		
	}
	
	public static void incrementHeartbeat() {
		
	}
	
}
