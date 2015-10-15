/**
 * TimerRunner.java
 * 
 * Aimy
 * 下午2:04:09
 */
package tk.codecube.test.aop.springcore.job.jdk;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 
 * @author Aimy
 * 2015年2月3日 下午2:04:09
 */
public class TimerRunner {

	public static void main(String[] args)
	{
		Timer timer = new Timer();
		TimerTask task = new SimpleTimerTask();
		
		//延迟1S，每5S执行一次
		timer.schedule(task, 1000L,5000L);
	}
	
}
