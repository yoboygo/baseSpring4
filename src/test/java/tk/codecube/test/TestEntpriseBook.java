/**
 * TestEntpriseBook.java
 * 
 * Aimy
 * 下午4:23:25
 */
package tk.codecube.test;

import org.junit.Test;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import tk.codecube.test.aop.springcore.PreGreetingAspect;
import tk.codecube.test.aop.springcore.entry.IWaiter;
import tk.codecube.test.aop.springcore.entry.NaiveWaiter;
import tk.codecube.test.aop.springcore.entry.NaughtyWaiter;
import tk.codecube.test.aop.springcore.entry.Waiter;
import tk.codecube.test.aop.springcore.entry.WaiterDelegate;

/**
 * @author Aimy
 * 2014年11月25日 下午2:16:58
 */
@SuppressWarnings("resource")
public class TestEntpriseBook {
	
	/**
	 *  AOP TEST STATICMETHOD
	 * @author Aimy
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
	 * @author Aimy
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
	 * @author Aimy
	 * 2014年11月13日 下午2:22:06
	 */
	@Test
	public void testPage214()
	{
		// TODO Error failure
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"spring/spring-aop-test.xml");
		Waiter waiterComposable = (Waiter) ac.getBean("waiterComposable");
		waiterComposable.serviceTo("Jhon");
	}
	
	/**
	 *  自动创建代理
	 * @author Aimy
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
	
	/**success
	 *  硬编码实现测试 
	 * @author Aimy
	 * 2014年12月9日 下午4:57:57
	 */
	@Test
	public void testPage227_242_proxy()
	{
		IWaiter naiveWaiter = new NaiveWaiter();
		
		AspectJProxyFactory aspectjFactory = new AspectJProxyFactory();
		aspectjFactory.setTarget(naiveWaiter);
		aspectjFactory.addAspect(PreGreetingAspect.class);
		
		IWaiter proxy = aspectjFactory.getProxy();
		proxy.greetTo("Tom");
		proxy.serviceTo("Tom");
	}
	
	
	/**	success
	 *  XML配置测试
	 * @author Aimy
	 * 2014年12月10日 上午8:45:43
	 */
	@Test
	public void testPage227_242_proxy_xml()
	{
		IWaiter naiveWaiter = new NaiveWaiter();
		
		AspectJProxyFactory aspectjFactory = new AspectJProxyFactory();
		aspectjFactory.setTarget(naiveWaiter);
		aspectjFactory.addAspect(PreGreetingAspect.class);
		
		IWaiter proxy = aspectjFactory.getProxy();
		proxy.greetTo("Tom");
		proxy.serviceTo("Tom");
	}

	/**failure
	 * 注解切面 :接口代理失败，去掉接口成功
	 * <aop:aspectj-autoproxy ></aop:aspectj-autoproxy>
	 * 可以自动创建切面
	 * @author Aimy
	 * 2014年11月25日 下午2:17:00
	 */
	@Test
	public void testPage227_242()
	{
		// TODO Error failure
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"spring/spring-aop-aspectj-auto.xml");
		NaiveWaiter naiveWaiter = (NaiveWaiter) ac.getBean("naiveWaiter");
		naiveWaiter.greetTo("Tom");
		
		Waiter waiterTarget = (Waiter) ac.getBean("waiterTarget");
		waiterTarget.greetTo("Jhon");
	}
	
	/**
	 *  
	 * @author Aimy
	 * 2014年11月27日 上午10:39:34
	 */
	@Test
	public void testPage236()
	{
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"spring/spring-aop-aspectj-test.xml");
		NaughtyWaiter naughtyWaiter = (NaughtyWaiter) ac.getBean("naughtyWaiter");
		naughtyWaiter.joke("Tom", 10);
		
	}
	
	
	
}
