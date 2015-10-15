/**
 * MailSender.java
 * 
 * Aimy
 * 下午1:42:36
 */
package tk.codecube.test.message;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author Aimy
 * 2014年10月10日 下午1:42:36
 */
public class MailSender implements ApplicationContextAware {

	private ApplicationContext ctx;
	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.ctx = applicationContext;

	}
	
	/**
	 *  send mail
	 * @auther Aimy
	 * @param to
	 * 2014年10月10日 下午1:45:58
	 */
	public void SendMail(String to)
	{
		System.out.println("<-------模拟发送邮件------->");
		MailSendEvent mse = new MailSendEvent(this.ctx, to);
		//send event to all listener
		ctx.publishEvent(mse);
	}

}
