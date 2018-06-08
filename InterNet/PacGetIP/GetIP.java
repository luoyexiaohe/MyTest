package PacGetIP;

import java.net.InetAddress;

import org.junit.Test;

public class GetIP {
	
	@Test
	public void funcGetIP() {
		InetAddress address = null;
		try {
			address = InetAddress.getByName("JC001");
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println(address.getHostAddress());
	}
}
