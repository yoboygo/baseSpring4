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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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
	
	/**
	 * 生成数据大屏需要的JSON对象
	 * @param itemCount
	 * @param s
	 * @param cf
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String createJsonData(int itemCount, int s, ChronoField cf) throws Exception{
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm:ss");
		LocalDateTime ldt = LocalDateTime.now();
		Random rd = new Random();
		
		JSONArray ret = new JSONArray();
		
		for(int i = 1; i <= itemCount; ++i){
			String x = ldt.format(dtf);
			for(int j = 1; j <= s; ++j){
				JSONObject itemData = new JSONObject();
				itemData.put("x", String.valueOf(x));
				itemData.put("y", String.valueOf(rd.nextInt(999)));
				itemData.put("s", String.valueOf(j));
				ret.add(itemData);
			}
			switch(cf){
				case YEAR:
					//增加1年
					ldt = ldt.plusYears(1);break;
				case MONTH_OF_YEAR:
					//增加1月
					ldt = ldt.plusMonths(1);break;
				case DAY_OF_YEAR:
					//增加1天
					ldt = ldt.plusDays(1);break;
				default:
					System.err.println("不持的参数~");
					throw new Exception("仅支持年月日~");
					
			}
		}
		
		return ret.toJSONString();
	}
	
	/**
	 * 获取每年的数据
	 * @param itemCount
	 * @param s
	 * @return
	 * @throws Exception
	 */
	public String getYearData(int itemCount,int s) throws Exception{
		return createJsonData(itemCount,s,ChronoField.YEAR);
	}
	/**
	 * 获取每月的数据
	 * @param itemCount
	 * @param s
	 * @return
	 * @throws Exception
	 */
	public String getMonthData(int itemCount,int s) throws Exception{
		return createJsonData(itemCount,s,ChronoField.MONTH_OF_YEAR);
	}
	/**
	 * 获取每天的数据
	 * @param itemCount
	 * @param s
	 * @return
	 * @throws Exception
	 */
	public String getDayData(int itemCount,int s) throws Exception{
		return createJsonData(itemCount,s,ChronoField.DAY_OF_YEAR);
	}
	
	/**
	 * 测试生成的JSON
	 * @throws Exception
	 */
	@Test
	public void testCreateJsonData() throws Exception{
		
		String yearData = getYearData(5, 5);
		String monthData = getMonthData(6, 5);
		String dayData =  getDayData(15, 5);
		
		System.out.println(yearData);
		System.out.println(monthData);
		System.out.println(dayData);
	}
}
