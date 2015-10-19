/**
 * TestMain.java
 * 
 * Aimy
 * 下午5:13:04
 */
package tk.codecube.test;

/**
 * @author Aimy
 * 2014年10月23日 下午5:13:04
 */
public class TestMain {

	/**
	 *  
	 * @auther Aimy
	 * @param args
	 * 2014年10月23日 下午5:13:04
	 */
	public static void main(String[] args) {
		for(String fieldValue :args)
		{
			double dbValue = 0.0;
			System.out.println(20-1.8);
			if(fieldValue.indexOf("0")==0)//以0开头，默认为数字
			{
				System.out.println("fieldValue="+fieldValue);
				dbValue = Double.valueOf(fieldValue);
				System.out.println("dbValue="+dbValue);
				System.out.println("dbValue == 0.0?"+(dbValue == 0.0));
				if(dbValue != 0.0)
					System.out.println("！=0");
			}
		}
		
	}

}
