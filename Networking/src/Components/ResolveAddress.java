package Components;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ResolveAddress {
	
	private ResolveAddress() {
		
	}
	
	public static boolean printAddress(String ipStr) {
		
		int j = 0;
		StringBuffer strArr[] = new StringBuffer[4];
		strArr[0] = new StringBuffer();
		for(int i=0;i<ipStr.length();i++) {
			
			if(ipStr.charAt(i) >= 48 && ipStr.charAt(i) <= 57) {
				strArr[j].append(ipStr.charAt(i));						
			} else if(ipStr.charAt(i) == '.') {
				strArr[++j] = new StringBuffer();
			} else {
				System.out.println("wrong ip");
				return false;
			}
		
		}
		j = 0;
		byte[] ip = new byte[4];
		for(StringBuffer temp : strArr) {
			ip[j++] = Byte.parseByte(temp.toString());
		}
		InetAddress address = null;
		try {
			address = InetAddress.getByAddress(ip);
		} catch (UnknownHostException e) {
			System.out.println("Invalid IP\nIt should be in 0.0.0.0 to 255.255.255.255");
			return false;
		}
		System.out.println(address.getHostName());
		return true;
		
	}
	
	public static boolean printIP(String address) {
		
		InetAddress ip = null;
		try {
			ip = InetAddress.getByName(address);
		} catch (UnknownHostException e) {
			System.out.println("Unknown Host");
			return false;
		}
		System.out.println(ip.getHostAddress());
		return true;
		
	}

}
