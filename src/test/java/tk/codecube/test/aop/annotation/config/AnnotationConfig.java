/**
 * AnnotationConfig.java
 * 
 * Aimy
 * 上午10:18:15
 */
package tk.codecube.test.aop.annotation.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.Ordered;

import tk.codecube.test.aop.annotation.NeedTest;
import tk.codecube.test.aop.springcore.entry.IWaiter;
import tk.codecube.test.aop.springcore.entry.impl.NaiveWaiter;

/** AspectJ注解切面
 * @author Aimy
 * 2014年11月27日 上午10:18:15
 */
@Aspect
public class AnnotationConfig implements Ordered{

	private final int order = Integer.MAX_VALUE;
	
	/* (non-Javadoc)
	 * @see org.springframework.core.Ordered#getOrder()
	 */
	@Override
	public int getOrder() {
		return this.order;
	}
	
	@AfterReturning("@annotation(tk.codecube.test.aop.annotation.NeedTest)")
	public void needTestFun()
	{
		System.out.println("AfterReturning:AnnotationConfig.needTestFun() executed!");
	}

	@AfterReturning("this(tk.codecube.test.aop.springcore.entry.ISeller)")
	public void thisTest()
	{
		System.out.println("AfterReturning:AnnotationConfig.thisTest() ISeller!");
	}
	
	/**
	 *  调用 ISeller实现类中的所有方法时运行
	 * @author Aimy
	 * 2014年12月25日 上午11:23:02
	 */
	@Before("target(tk.codecube.test.aop.springcore.entry.ISeller)")
	public void targetTest()
	{
		System.out.println("Before:AnnotationConfig.targetTest() ISeller!");
	}
	
	/**
	 *  调用NaiveWaiter中的greetTo()方法
	 * @author Aimy
	 * @param pjp
	 * @throws Throwable
	 * 2014年12月25日 上午11:22:18
	 */
	@Around("execution(* greetTo(..)) && target(tk.codecube.test.aop.springcore.entry.impl.NaiveWaiter)")
	public void joinPointAccess(ProceedingJoinPoint pjp) throws Throwable
	{
		System.err.println("----------joinPointAccess Start-----------");
		System.out.println("args[0]:"+pjp.getArgs()[0]);
		System.out.println("signature:"+pjp.getClass().getName());
		pjp.proceed();
		System.err.println("----------joinPointAccess End-----------");
	}
	
	/**
	 * 调用NaiveWaiter中的有两个参数的方法，其中前两个为String和int的函数 
	 * @author Aimy
	 * @param num
	 * @param name
	 * 2014年12月25日 上午11:21:10
	 */
	@Before("target(tk.codecube.test.aop.springcore.entry.impl.NaiveWaiter) && args(name,num,..)")
	public void bindJoinPointAccess(int num,String name)
	{
		System.err.println("----------bindJoinPointAccess Start-----------");
		System.out.println("name:"+name);
		System.out.println("num:"+num);
		System.err.println("----------bindJoinPointAccess End-----------");
	}
	
	/**
	 *  调用IWaiter实现类的方法时运行
	 * @author Aimy
	 * @param waiter
	 * 2014年12月25日 上午11:20:35
	 */
	@Before("this(waiter)")
	public void bindProxyObj(IWaiter waiter)
	{
		System.err.println("----------bindProxyObj Start-----------");
		System.out.println("ProxyTarget:"+waiter.getClass().getName());
		System.err.println("----------bindProxyObj End-----------");
	}
	
	/**
	 *  代理对象为类
	 * @author Aimy
	 * @param waiter
	 * 2014年12月25日 上午11:20:35
	 */
	@Before("this(naiveWaiter)")
	public void bindProxyObjClass(NaiveWaiter naiveWaiter)
	{
		System.err.println("----------bindProxyObjClass Start-----------");
		System.out.println("ProxyTargetClass:"+naiveWaiter.getClass().getName());
		System.err.println("----------bindProxyObjClass End-----------");
	}
	
	/**
	 *  绑定注解对象 failure
	 * @author Aimy
	 * @param needTest
	 * 2014年12月25日 下午12:45:46
	 */
	@Before("@within(needTest)")
	public void bindTypeAnnoObjectWithin(NeedTest needTest)
	{
		System.err.println("----------bindTypeAnnoObject Start-----------");
		System.out.println("bindTypeAnnoObject--->within:"+needTest.getClass().getName());
		System.err.println("----------bindTypeAnnoObject End-----------");
	}
	
	@Before("@target(needTest)")
	public void bindTypeAnnoObjectTarget(NeedTest needTest)
	{
		System.err.println("----------bindTypeAnnoObject Start-----------");
		System.out.println("bindTypeAnnoObject--->Target:"+needTest.getClass().getName());
		System.err.println("----------bindTypeAnnoObject End-----------");
	}
	
	/**
	 *  绑定返回值
	 * @author Aimy
	 * @param retVal
	 * 2015年1月4日 下午9:08:49
	 */
	@AfterReturning(value="target(tk.codecube.test.aop.springcore.entry.impl.SmartSeller)",returning="retVal")
	public void bindReturnValue(int retVal)
	{
		System.err.println("----------bindReturnValue Start-----------");
		System.out.println("ReturnValue:"+retVal);
		System.err.println("----------bindReturnValue End-----------");
	}
}
