package tk.codecube.test.distribute.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPBIOService {

	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(1234);
		System.out.println("--->SocketService start!");
		Socket socket = ss.accept();
		InputStream is = socket.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		while(br.ready()) {
			System.out.println(br.readLine());
		}
		System.out.println("--->SocketService end!");
		
	}
}
