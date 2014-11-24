/**
 * TestEntpriseBook.java
 * 
 * Aimy
 * 下午4:23:25
 */
package tk.codecube.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import tk.codecube.test.aop.springcore.Waiter;
import tk.codecube.test.aop.springcore.WaiterDelegate;

/**
 * @author Aimy
 * 2014年11月11日 下午4:23:25
 */
public class TestEntpriseBook {
	
	/**
	 *  AOP TEST STATICMETHOD
	 * @auther Aimy
	 * 2014年11月11日 下午4:24:10
	 */
	@Test
	public void testPage203()
	{
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"spring/spring-aop-test.xml");
		//静态切面
/*		
  		Waiter waiter = (Waiter)ac.getBean("waiter");
		waiter.greetTo("Join");
		waiter.greetTo("Join");
		waiter.serviceTo("Join");
		
*/		
		//表达式切面
/*
		Waiter waiterRegx = (Waiter)ac.getBean("waiterRegx");
		waiterRegx.greetTo("Tom");
*/
		//动态切面
		Waiter waiterDynamic = (Waiter)ac.getBean("waiterDynamic");
		waiterDynamic.serviceTo("John");
		waiterDynamic.greetTo("John");
		waiterDynamic.serviceTo("Peeter");
		waiterDynamic.greetTo("Peeter");
	}

	/**
	 *  AOP FolwPoint TEST
	 * @auther Aimy
	 * 2014年11月13日 上午10:28:22
	 */
	@Test
	public void testPage211()
	{
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"spring/spring-aop-test.xml");
		Waiter waiterFlow = (Waiter) ac.getBean("waiterFlow");
		waiterFlow.serviceTo("Jhon");
		
		WaiterDelegate wd = new WaiterDelegate();
		wd.setWaiter(waiterFlow);
		wd.service("Peter");
	}
	
	/** 失败
	 *  waiterComposable
	 * @auther Aimy
	 * 2014年11月13日 下午2:22:06
	 */
	@Test
	public void testPage214()
	{
		// TODO Error failer
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"spring/spring-aop-test.xml");
		Waiter waiterComposable = (Waiter) ac.getBean("waiterComposable");
		waiterComposable.serviceTo("Jhon");
	}
	
	/**
	 *  自动创建代理
	 * @auther Aimy
	 * 2014年11月14日 下午5:19:55
	 */
	@Test
	public void testPage217()
	{
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"spring/spring-aop-test.xml");
		Waiter waiterComposable = (Waiter) ac.getBean("waiterTarget");
		waiterComposable.serviceTo("Jhon");
	}
	
}
