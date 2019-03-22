package socketTest;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.junit.Test;

public class SocketTest {

	@Test
	public void testSocket() throws Exception {
		ServerSocket s = new ServerSocket(9091);
		while(true) {
			readSocket(s.accept());
		}
		
	}
	
	public void readSocket(Socket socket) throws Exception {
		InputStream in = socket.getInputStream();
		byte[] context = new byte[1024]; 
		while(in.read(context)>0) {
			String code =  new String(context);
			System.out.println(code);
		}
	}
}
