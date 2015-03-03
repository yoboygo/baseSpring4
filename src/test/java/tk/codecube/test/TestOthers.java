/**
 * TestOthers.java
 * 
 * Aimy
 * 下午2:14:24
 */
package tk.codecube.test;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.stream.FileImageInputStream;

import org.junit.Test;

/**
 * 除Spring之外的各种小的Case
 * @author Aimy
 * 2015年2月27日 下午2:14:24
 */
public class TestOthers {

	
	/**
	 *  测试读取Data文件
	 * @author Aimy
	 * 2015年2月27日 下午2:15:14
	 */
	@Test
	public void testReadData()
	{
		try {
			DataInputStream dis = new DataInputStream(new FileInputStream(new File("./src/test/resources/testData.data")));
			System.out.println(dis.read());
			
//			File file = new File("");
//			System.out.println(file.getCanonicalPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}