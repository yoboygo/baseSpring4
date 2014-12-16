/**
 * AnnotationConfig.java
 * 
 * Aimy
 * 上午10:18:15
 */
package tk.codecube.test.aop.annotation.config;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/** AspectJ注解切面
 * @author Aimy
 * 2014年11月27日 上午10:18:15
 */
@Aspect
public class AnnotationConfig {

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
	
	@Before("target(tk.codecube.test.aop.springcore.entry.ISeller)")
	public void targetTest()
	{
		System.out.println("Before:AnnotationConfig.targetTest() ISeller!");
	}
}
