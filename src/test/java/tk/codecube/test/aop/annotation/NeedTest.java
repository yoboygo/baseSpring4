/**
 * NeedTest.java
 * 
 * Aimy
 * 上午10:03:24
 */
package tk.codecube.test.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 标注此注解的方法默认进行测试
 * @author Aimy
 * 2014年11月27日 上午10:03:24
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface NeedTest {
	boolean value() default true;
}
