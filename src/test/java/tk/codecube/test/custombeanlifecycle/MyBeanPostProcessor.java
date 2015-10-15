/**
 * MyBeanPostProcessor.java
 * 
 * Aimy
 * 上午11:04:37
 */
package tk.codecube.test.custombeanlifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author Aimy
 * 2014年9月11日 上午11:04:37
 */
public class MyBeanPostProcessor implements BeanPostProcessor {

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.config.BeanPostProcessor#postProcessBeforeInitialization(java.lang.Object, java.lang.String)
	 */
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		
		if("car".equals(beanName))
		{
			Car car = (Car) bean;
			System.out
			.println("MyBeanPostProcessor.postProcessBeforeInitialization()");
			System.out.println("如果Car的颜色为空，默认设置为:黑色！");
			if(car.getColor() == null)
			{
				car.setColor("黑色");
			}
		}
		if("popoj".equals(beanName))
		{
			Popoj car = (Popoj) bean;
			System.out
			.println("MyBeanPostProcessor.postProcessBeforeInitialization()");
			System.out.println("如果Popoj的颜色为空，默认设置为:黑色！===========>"+car.getColor());
			if(car.getColor() == null)
			{
				car.setColor("黑色111");
			}
		}
		return bean;
	}

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.config.BeanPostProcessor#postProcessAfterInitialization(java.lang.Object, java.lang.String)
	 */
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		System.out
				.println("MyBeanPostProcessor.postProcessAfterInitialization()----->"+beanName);
		return bean;
	}

}
