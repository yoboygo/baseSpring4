/**
 * EnableSellerAspect.java
 * 
 * Aimy
 * 上午10:39:39
 */
package tk.codecube.test.aop.annotation.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;

import tk.codecube.test.aop.springcore.entry.ISeller;
import tk.codecube.test.aop.springcore.entry.impl.SmartSeller;

/** 引介增强
 * @author Aimy
 * 2014年12月16日 上午10:39:39
 */
@Aspect
public class EnableSellerAspect {

	@DeclareParents(value="tk.codecube.test.aop.springcore.entry.impl.NaiveWaiter",defaultImpl=SmartSeller.class)
	public ISeller iseller;
}
