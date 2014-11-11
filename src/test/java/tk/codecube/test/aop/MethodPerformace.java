/**
 * MethodPerformace.java
 * 
 * Aimy
 * 下午12:34:28
 */
package tk.codecube.test.aop;

/**
 * @author Aimy
 * 2014年10月11日 下午12:34:28
 */
public class MethodPerformace {

	private long begin;
	private long end;
	private String serviceMethod;
	
	/**
	 * @param method
	 */
	public MethodPerformace(String method) {
		this.serviceMethod = method;
		this.begin = System.currentTimeMillis();
	}

	/**
	 *  
	 * @auther Aimy
	 * 2014年10月11日 下午12:38:34
	 */
	public void printPerformance() {
		end = System.currentTimeMillis();
		long elapse = end - begin;
		System.err.println(serviceMethod+" 花费 "+elapse+" 毫秒");
	}

}
