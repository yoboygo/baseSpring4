/**
 * MailSenderListener.java
 * 
 * Aimy
 * 下午1:35:26
 */
package tk.codecube.test.message;

import org.springframework.context.ApplicationListener;

/**
 * @author Aimy
 * 2014年10月10日 下午1:35:26
 */
public class MailSenderListener implements ApplicationListener<MailSendEvent> {

	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationListener#onApplicationEvent(org.springframework.context.ApplicationEvent)
	 */
	@Override
	public void onApplicationEvent(MailSendEvent event) {
		System.out.println("您向  "+event.getTo()+" 发送了一封邮件！");
	}
	

}
