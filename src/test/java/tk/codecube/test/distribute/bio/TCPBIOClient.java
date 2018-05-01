package tk.codecube.test.distribute.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * TCP/IP BIO 
 * Socket ServerSocket
 * @author bpqqo
 *
 */
public class TCPBIOClient {

	
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		Socket socket = new Socket("127.0.0.1",1234);
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
		out.println("Hello!");
		in.readLine();
	}
}
