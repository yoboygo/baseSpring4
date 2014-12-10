/**
 * TestBeginChapterSix.java
 * 
 * Aimy
 * 下午10:45:20
 */
package tk.codecube.test;

import java.beans.PropertyEditor;

import org.junit.Test;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import tk.codecube.test.propertyediter.TdateEntry;

/**Cod Spring form six
 * @author Aimy
 * 2014年10月13日 下午10:45:21
 */
@SuppressWarnings("resource")
public class TestBeginChapterSix {
	
	/**
	 * Note, that you shouldn't register {@link PropertyEditor} bean instances via
	 * the {@code customEditors} property as {@link PropertyEditor}s are stateful
	 * and the instances will then have to be synchronized for every editing
	 * attempt. In case you need control over the instantiation process of
	 * {@link PropertyEditor}s, use a {@link PropertyEditorRegistrar} to register
	 * them. 
	 * @author Aimy
	 * 2014年10月14日 下午8:48:55
	 */
	@Test
	public void DateCustomerInXmlTest()
	{
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/spring-beandatecustomer-test.xml");
		TdateEntry date = (TdateEntry) ctx.getBean("tdate");
		System.out.println(date.toString());
	}
	
	/**
	 *  Text set date as string in xml
	 * @author Aimy
	 * 2014年10月13日 下午11:03:23
	 */
	@Test
	public void DateRegisterInXmlTest()
	{
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/spring-beandateregister-test.xml");
		TdateEntry date = (TdateEntry) ctx.getBean("tdate");
		System.out.println(date.toString());
	}
	
	
}
