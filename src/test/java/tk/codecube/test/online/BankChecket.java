/**
 * BankChecket.java
 * 
 * Aimy
 * 下午3:57:16
 */
package tk.codecube.test.online;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Aimy
 * 2014年9月25日 下午3:57:16
 */
public class BankChecket {
	
	static String LOATION_FLAG = "?";
	static String SPLIT_FLAG = ";";
	
	static String[] allInArray = new String[25];
	static String line = new String();
	static String preLine = new String();
	
	public static void main(String args[])
	{
		if(args.length == 0)
		{
			System.err.println("Pleace input the number line!");
		}
		
		int length = args.length;
		for(int i=0; i<length;i++)
		{
			line = args[i];
			boolean isAmbig = line.indexOf(LOATION_FLAG) != -1;
			if(isAmbig)
			{
				if(i>0)
				{
					runChecked(i);
				}else{
					System.out.println("Input illegal!");
					return;
				}
			}else
			{
				allInArray[i] = line;
			}
		}
		
		for(String line:allInArray)
		{
			if(line != null)
				System.out.println(line);
		}
	}
	
	
	
	/**
	 *  细化模糊数字所在位置
	 * @auther Aimy
	 * @param index
	 * 2014年9月25日 下午4:54:07
	 */
	public static void runChecked(int index)
	{
		preLine = allInArray[index-1];
		String[] preLineArray = preLine.split(SPLIT_FLAG);
		String[] lineArray = line.split(SPLIT_FLAG);
		
		//add for delete alert
		System.out.println(preLineArray);
		
		List<Integer> flagIndexInArray = getFlagIndexInArray(lineArray);
		
		switch(flagIndexInArray.size())
		{
			case 1:repairOnePoint(index,flagIndexInArray);break;
//			case 2:repairTwoPoint(index,flagIndexInArray);break;
	//		case 3:repairThreePoint(index,flagIndexInArray);break;
		}
		
		/*if(line.endsWith(LOATION_FLAG))
		{
			if(line.indexOf(LOATION_FLAG) == line.lastIndexOf(LOATION_FLAG))
			{
				//仅余额模糊则获取余额
				getRemain(index,lineArray,preLineArray);
			}else{
				
			}
				
			
			
		}else
		{
			int location = 0;
			for(String num:lineArray)
			{
				if(LOATION_FLAG.equals(num))
				{
					++location;
					break;
				}else{
					continue;
				}
					
			}
		}*/
		
	}
	

	/**
	 *  记录中只有一个模糊数字
	 * @auther Aimy
	 * @param index
	 * @param flagIndexInArray
	 * 2014年9月26日 上午9:19:45
	 */
	private static void repairOnePoint(int index, List<Integer> flagIndexInArray) {
		int location = flagIndexInArray.get(0);
		switch(location)
		{
			case 1:getIncome(index);break;
			case 2:getExpend(index);break;
			case 3:getRemain(index);break;
		}
	}


	/**
	 *  
	 * @auther Aimy
	 * @param index
	 * 2014年9月26日 上午9:37:09
	 */
	private static void getIncome(int index) {
		
		String preLineArray[] = allInArray[index-1].split(";");
		String lineArray[] = line.split(";");
		
		double preRemain = Double.valueOf(preLineArray[3]);
		double expend = Double.valueOf(lineArray[2]);
		double remain = Double.valueOf(lineArray[3]);
		
		line = line.replace(LOATION_FLAG, remain+expend-preRemain+"");
		allInArray[index] = line;
		
	}



	/**
	 *  获取支出
	 * @auther Aimy
	 * @param index
	 * 2014年9月26日 上午9:35:11
	 */
	private static void getExpend(int index) {
		String lineArray[] = line.split(";");
		String preLineArray[] = allInArray[index-1].split(";");
		
		double preRemain = Double.valueOf(preLineArray[3]);
		double income = Double.valueOf(lineArray[1]);
		double remain = Double.valueOf(lineArray[3]);
		
		line = line.replace(LOATION_FLAG,preRemain-remain-income+"");
		allInArray[index] = line;
	}



	/**
	 *  获取余额
	 * @auther Aimy
	 * @param index
	 * 2014年9月26日 上午9:23:29
	 */
	private static void getRemain(int index) {
		
		String lineArray[] = line.split(";");
		String preLineArray[] = allInArray[index-1].split(";");
		
		double preRemain = Double.valueOf(preLineArray[3]);
		double income = Double.valueOf(lineArray[1]);
		double expend = Double.valueOf(lineArray[2]);
		
		if(!String.valueOf(0d).equals(String.valueOf(income)) && !String.valueOf(0d).equals(String.valueOf(expend)))
		{
			System.out.println("收入和支出不能同时为非空！");
			return;
		}
		
		line = line.replace(LOATION_FLAG, preRemain+income-expend+"");
		allInArray[index] = line;
	}



	/**
	 *  根据传如的数组记录“？”所在的位置
	 * @auther Aimy
	 * @param lineArray
	 * @return
	 * 2014年9月25日 下午5:22:11
	 */
	private static List<Integer> getFlagIndexInArray(String[] lineArray) {
		int arrayLength = lineArray.length;
		List<Integer> listIndex = new ArrayList<Integer>();
		for(int i=0;i<arrayLength;++i)
		{
			if(LOATION_FLAG.equals(lineArray[i]))
				listIndex.add(i);
		}
		return listIndex;
	}

	/**
	 *  计算余额
	 * @auther Aimy
	 * @param index
	 * @param lineArray
	 * @param preLineArray
	 * 2014年9月25日 下午4:50:02
	 */
	public static void getRemain(int index,String[] lineArray,String[] preLineArray)
	{
		double preRemain = Double.valueOf(preLineArray[3]);
		double income = Double.valueOf(lineArray[1]);
		double expend = Double.valueOf(lineArray[2]);
		
		if(!String.valueOf(0d).equals(String.valueOf(income)) && !String.valueOf(0d).equals(String.valueOf(expend)))
		{
			System.out.println("收入和支出不能同时为非空！");
			return;
		}
		
		line = line.replace(LOATION_FLAG, preRemain+income-expend+"");
		allInArray[index] = line;
	}
	
	/**
	 *  验证记录的合法性
	 * @auther Aimy
	 * @param line
	 * 2014年9月26日 上午8:59:35
	 */
	public static boolean checkLine(String line)
	{
		String lineArray[] = line.split(";");
 		double income = Double.valueOf(lineArray[1]);
		double expend = Double.valueOf(lineArray[2]);
		if(!String.valueOf(0d).equals(String.valueOf(income)) && !String.valueOf(0d).equals(String.valueOf(expend)))
		{
			System.out.println("收入和支出不能同时为非空！");
			return false;
		}
		return true;
	}
	
	/**
	 *  验证记录的合法性（重载）
	 * @auther Aimy
	 * @param lineArray
	 * @return
	 * 2014年9月26日 上午9:16:17
	 */
	public static boolean checkLine(String[] lineArray)
	{
 		double income = Double.valueOf(lineArray[1]);
		double expend = Double.valueOf(lineArray[2]);
		if(!String.valueOf(0d).equals(String.valueOf(income)) && !String.valueOf(0d).equals(String.valueOf(expend)))
		{
			System.out.println("收入和支出不能同时为非空！");
			return false;
		}
		return true;
	}
}
