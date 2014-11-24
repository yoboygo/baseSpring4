/**
 * BankOrderChecketed.java
 * 
 * Aimy
 * 上午10:48:37
 */
package tk.codecube.test.online;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Aimy
 * 2014年9月26日 上午10:48:37
 */
public class BankOrderChecketed {

	static final String TARGER_STR = "?";
	static final String SPLICT_STR = ";";
	
	static boolean hasPreLine = false;
	
	/**
	 *  
	 * @auther Aimy
	 * @param args
	 * 2014年9月26日 上午10:48:37
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] afterArray = new String[25];
		int argsLength = args.length;
		for(int index = 0;index < argsLength; ++index)
		{
			if(index > 0)
				hasPreLine = true;
			
			List<Integer> listPoint = getTargetlPointList(args[index]);
			for(int point:listPoint)
			{
				switch(point)
				{
					case 1:getIncome(index,args,listPoint,afterArray);break;
					case 2:;
					case 3:;
				}
			}
		}
	}

	/**
	 *  get the incom and put the line to afterArray
	 * @auther Aimy
	 * @param index
	 * @param args
	 * @param listPoint
	 * @param afterArray
	 * 2014年9月26日 下午2:07:34
	 */
	private static void getIncome(int index, String[] args,List<Integer> listPoint,
			 String[] afterArray) {
		String line = args[index];
		// index > 0 and only has one point
		if(index > 0 && listPoint.size() == 1)
		{
			String formual = "3+2-#3";
			Double result = calculateByFormual(index,args,formual);
			line.replace("?", String.valueOf(result));
			afterArray[index] = line;
		}
	}

	/**
	 *  calculate by formual
	 *  # --> pre
	 *  $ --> next
	 * @auther Aimy
	 * @param index
	 * @param args
	 * @param formual
	 * @return
	 * 2014年9月26日 下午2:27:43
	 */
	private static Double calculateByFormual(int index, String[] args,
			String formual) {
		// TODO Auto-generated method stub
		String[] preLineArray = args[index-1].split(";");
		String[] lineArray = args[index].split(";");
		
		return null;
	}

	/**
	 *  return the flag position in array
	 * @auther Aimy
	 * @param line
	 * @return
	 * 2014年9月26日 上午10:52:14
	 */
	private static List<Integer> getTargetlPointList(String line) {
		List<Integer> listPoint = new ArrayList<Integer>();
		String[] lineArray = line.split(SPLICT_STR);
		int arrayLength = lineArray.length;
		for(int i = 0;i<arrayLength;++i)
		{
			if(TARGER_STR.equals(lineArray[i]))
				listPoint.add(i);
		}
		return listPoint;
	}

}
