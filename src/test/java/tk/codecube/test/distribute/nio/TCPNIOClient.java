package tk.codecube.test.distribute.nio;

import java.io.IOException;
import java.net.Socket;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Set;

/**
 * SocketChannel ServerSocketChannel
 * 
 * @author bpqqo
 *
 */
public class TCPNIOClient {

	public static void main(String[] args) throws IOException {
		
		Socket s = new Socket("127.0.0.1",1234);

		SocketChannel channel = SocketChannel.open();
		channel.configureBlocking(false);
		channel.connect(s.getRemoteSocketAddress());
		Selector selector = Selector.open();
		channel.register(selector, SelectionKey.OP_CONNECT);
		int nKeys = selector.select(100000);
		SelectionKey sKey = null;
		if(nKeys > 0) {
			Set<SelectionKey> keys = selector.selectedKeys();
			for(SelectionKey key : keys) {
				if(key.isConnectable()) {
					SocketChannel sc = (SocketChannel)key.channel();
					sc.configureBlocking(false);
					sKey = sc.register(selector,SelectionKey.OP_READ);
					sc.finishConnect();
				}else if(key.isReadable()){
					ByteBuffer buffer = ByteBuffer.allocate(1024);
					SocketChannel sc = (SocketChannel)key.channel();
					int readBytes = 0;
					try{
						int ret = 0;
						try{
							while((ret = sc.read(buffer)) > 0){
								readBytes += ret;
							}
						}finally {
							buffer.flip();
						}
					}finally {
						if(buffer != null){
							buffer.clear();
						}
					}
					//输出
					System.out.println(buffer);
				}else if(key.isWritable()){
					key.interestOps(key.interestOps() & (~SelectionKey.OP_WRITE));
					SocketChannel sc = (SocketChannel)key.channel();
					int writtenedSize = sc.write(buffer);
					if(writtenedSize == 0){
						key.interestOps(key.interestOps() | SelectionKey.OP_WRITE);
					}
				}
			}
			selector.selectedKeys().clear();
		}
		ByteBuffer bb = ByteBuffer.allocate(1024);
		int wSize = channel.write(bb);
		if(wSize == 0){
			sKey.interestOps(sKey.interestOps() | SelectionKey.OP_WRITE);
		}
	}

}
