/**
 * MailSendEvent.java
 * 
 * Aimy
 * 下午1:22:06
 */
package tk.codecube.test.message;

import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

/**
 * @author Aimy
 * 2014年10月10日 下午1:22:07
 */
public class MailSendEvent extends ApplicationContextEvent{

	/**
	 * 
	 */
	private static final long serialVersionUID = -293818291341967648L;
	private String to;
	/**
	 * @param source
	 */
	public MailSendEvent(ApplicationContext source,String to) {
		super(source);
		this.to = to;
	}
	public String getTo() {
		return to;
	}

}
