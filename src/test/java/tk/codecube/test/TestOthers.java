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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
			dis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public <E> List<E> getInit()
	{
		return new ArrayList<E>();
	}
	
	@Test
	public void testList()
	{
		ArrayList<Integer> al  = new ArrayList<Integer>();
		for(int i=0;i<10;++i)
		{
			al.add(i);
		}
		printList(al);
	}
	//使用递归遍历List
	public void printList(ArrayList<Integer> list)
	{
		System.out.println(list.remove(0));
		if(list.size() > 0)
		{
			printList(list);
		}
	}
}
