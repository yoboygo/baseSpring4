/**
 * PerformanceMonitor.java
 * 
 * Aimy
 * 上午11:08:21
 */
package tk.codecube.test.aop;

/**
 * @author Aimy
 * 2014年10月11日 上午11:08:21
 */
public class PerformanceMonitor {

	private static ThreadLocal<MethodPerformace> performanceRecord = new ThreadLocal<MethodPerformace>();
	
	/**
	 *  
	 * @auther Aimy
	 * @param string
	 * 2014年10月11日 上午11:08:34
	 */
	public static void begin(String method) {
		System.err.println("Begin monitor...");
		MethodPerformace mp = new MethodPerformace(method);
		performanceRecord.set(mp);
	}

	/**
	 *  
	 * @auther Aimy
	 * 2014年10月11日 上午11:15:16
	 */
	public static void end() {
		System.err.println("End monitor...");
		MethodPerformace mp = performanceRecord.get();
		mp.printPerformance();
	}

}
