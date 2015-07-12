/**
 * MyJob.java
 * 
 * Aimy
 * 上午9:41:14
 */
package tk.codecube.test.aop.springcore.job;

import java.util.Calendar;
import java.util.Map;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;

/**
 * 任务调度测试
 * @author Aimy
 * 2015年2月3日 上午9:41:14
 */
public class MyJob implements Job{

	/* (non-Javadoc)
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 */
	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		
		Map<String,Object> dataMap = context.getJobDetail().getJobDataMap();
		String size = (String) dataMap.get("size");
		
		ApplicationContext ctx =  (ApplicationContext) dataMap.get("applicationContext");
		
		System.out.println(ctx.getId());
		
		System.out.println("size:"+size);
		
		dataMap.put("size",size+"0");
		
		System.out.println(Calendar.getInstance());
		
		
	}

}
