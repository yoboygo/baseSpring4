/**
 * MyInstantiationAwareBeanPostProcessor.java
 * 
 * Aimy
 * 上午10:48:46
 */
package tk.codecube.test.custombeanlifecycle;

import java.beans.PropertyDescriptor;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.core.Ordered;

/**
 * @author Aimy
 * 2014年9月11日 上午10:48:46
 */
public class MyInstantiationAwareBeanPostProcessor extends
	InstantiationAwareBeanPostProcessorAdapter implements Ordered{
	
	protected int order = Ordered.HIGHEST_PRECEDENCE;
	
	public Object postProcessBeforeInstantiation(Class<?> beanClass,
			String beanName) throws BeansException {
		System.out
				.println("MyInstantiationAwareBeanPostProcessor.postProcessBeforeInstantiation() -----> "+beanName);
		return null;
	}
	
	public boolean postProcessAfterInstantiation(Object bean, String beanName)
			throws BeansException {
		System.out
				.println("MyInstantiationAwareBeanPostProcessor.postProcessAfterInstantiation() ------> "+beanName);
		return true;
	}
	
	public PropertyValues postProcessPropertyValues(PropertyValues pvs,
			PropertyDescriptor[] pds, Object bean, String beanName)
			throws BeansException {
		if("car".equals(beanName))
		{
			System.out
					.println("MyInstantiationAwareBeanPostProcessor.postProcessPropertyValues() -----> "+beanName);
		}
		return pvs;
	}

	/* (non-Javadoc)
	 * @see org.springframework.core.Ordered#getOrder()
	 */
	@Override
	public int getOrder() {
		return order;
	}

}
