/**
 * SimpleTimerTask.java
 * 
 * Aimy
 * 下午1:57:56
 */
package tk.codecube.test.aop.springcore.job.jdk;

import java.util.Date;
import java.util.TimerTask;

/**JDK Timer测试
 * @author Aimy
 * 2015年2月3日 下午1:57:56
 */
public class SimpleTimerTask extends TimerTask{

	private int count = 0;
	
	/* (non-Javadoc)
	 * @see java.util.TimerTask#run()
	 */
	@Override
	public void run() {
		System.out.println("Execute task.");
		Date exeTime = new Date(scheduledExecutionTime());
		System.out.println("本次任务安排执行时间为:"+exeTime);
		
		if(++count > 10){
			cancel();
		}
		
	}

}
