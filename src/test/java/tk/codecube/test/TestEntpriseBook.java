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
import tk.codecube.test.aop.springcore.entry.ISeller;
import tk.codecube.test.aop.springcore.entry.IWaiter;
import tk.codecube.test.aop.springcore.entry.impl.NaiveWaiter;
import tk.codecube.test.aop.springcore.entry.impl.NaughtyWaiter;
import tk.codecube.test.aop.springcore.entry.impl.Waiter;
import tk.codecube.test.aop.springcore.entry.impl.WaiterDelegate;

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
	public void testPage227_PDF242_proxy()
	{
		IWaiter naiveWaiter = new NaiveWaiter();
		
		AspectJProxyFactory aspectjFactory = new AspectJProxyFactory();
		aspectjFactory.setTarget(naiveWaiter);
		aspectjFactory.addAspect(PreGreetingAspect.class);
		
		IWaiter proxy = aspectjFactory.getProxy();
		proxy.greetTo("Tom");
		proxy.serviceTo("Tom");
	}
	
	
	/**	failuer
	 *  XML配置测试
	 * @author Aimy
	 * 2014年12月10日 上午8:45:43
	 */
	@Test
	public void testPage227_PDF242_proxy_xml()
	{
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"spring/spring-aop-aspectj-auto-def.xml");
		IWaiter naiveWaiter = (IWaiter) ac.getBean("naiveWaiter");
		naiveWaiter.greetTo("Tom");
		
	}

	/**
	 * success
	 * <aop:aspectj-autoproxy ></aop:aspectj-autoproxy>
	 * 可以自动创建切面
	 * @author Aimy
	 * 2014年11月25日 下午2:17:00
	 */
	@Test
	public void testPage227_PDF242_proxyDef()
	{
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"spring/spring-aop-aspectj-auto-def.xml");
		//autoproxy 默认是jdk代理，创建的是接口代理，所以直接声明类是不能讲AOP逻辑织入的
		//NaiveWaiter naiveWaiter = (NaiveWaiter) ac.getBean("naiveWaiter"); //failure
		
		IWaiter naiveWaiter = (IWaiter) ac.getBean("naiveWaiter"); // success
		naiveWaiter.greetTo("Tom");
		
		//page236_PDF251
		IWaiter naughtyWaiter = (IWaiter) ac.getBean("naughtyWaiter");
		naughtyWaiter.serviceTo("Join");
		
	}
	
	/**
	 * 
	 * <aop:aspectj-autoproxy ></aop:aspectj-autoproxy>
	 * 可以自动创建切面
	 * @author Aimy
	 * 2014年11月25日 下午2:17:00
	 */
	@Test
	public void testPage227_PDF242_proxyClass()
	{
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"spring/spring-aop-aspectj-auto-class.xml");
		//autoproxy 默认是jdk代理，创建的是接口代理，所以直接声明类是不能讲AOP逻辑织入的
//		NaiveWaiter naiveWaiter = (NaiveWaiter) ac.getBean("naiveWaiter"); //failure
		
		NaiveWaiter naiveWaiter = (NaiveWaiter) ac.getBean("naiveWaiter"); // success
		naiveWaiter.greetTo("Tom");
		//Page236
		NaughtyWaiter naughtyWaiter = (NaughtyWaiter) ac.getBean("naughtyWaiter");
		naughtyWaiter.joke("Tom", 10);
	}
	
	/**
	 *  AspectJ this 测试
	 *  this 之匹配目标类中的方法，不会匹配通过增强织入的方法
	 *  target 貌似不支持，目前没有找到官方的文档
	 * @author Aimy
	 * 2014年12月16日 上午10:54:32
	 */
	@Test
	public void testPage243_PDF258()
	{
		//TODO this 没有匹配通过增强织入的方法，target失效
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"spring/spring-aop-aspectj-auto-def.xml");
		IWaiter naiveWaiter = (IWaiter) ac.getBean("naiveWaiter"); // success
		naiveWaiter.greetTo("Tom");
		naiveWaiter.serviceTo("Tom");
		
		((ISeller)naiveWaiter).sell("Wood","Jhon");
	}
	
	/**
	 *  环绕增强，切面访问目标参数
	 * @author Aimy
	 * 2014年12月23日 下午4:00:05
	 */
	@Test
	public void testPage247_PDF262()
	{
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"spring/spring-aop-aspectj-auto-class.xml");
		//autoproxy 默认是jdk代理，创建的是接口代理，所以直接声明类是不能讲AOP逻辑织入的
//		NaiveWaiter naiveWaiter = (NaiveWaiter) ac.getBean("naiveWaiter"); //failure
		
		NaiveWaiter naiveWaiter = (NaiveWaiter) ac.getBean("naiveWaiter"); // success
		naiveWaiter.greetTo("Tom");
		
//		page248_pdf_263
		naiveWaiter.smileTo("Tom", 2);
	}
	
	
	/**
	 *  代理绑定注解对象
	 * @author Aimy
	 * 2014年12月25日 下午12:50:22
	 */
	@Test
	public void testPage250_PDF265()
	{
		//TODO failuer
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"spring/spring-aop-aspectj-auto-class.xml");
		
		IWaiter naughtyWaiter = (IWaiter) ac.getBean("naughtyWaiter"); // success
		
		naughtyWaiter.serviceTo("Tome");
	}
	
}
