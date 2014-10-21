package Command;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Get_Current_IpAddress {

	
	public static String getIp(){
		
		InetAddress IP = null;
		
		try {
			IP = InetAddress.getLocalHost();
			
		
			return IP.getHostAddress();
		
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return IP.getHostAddress();
		
		
	
		
	}
	
}
