/**
 * MyBeanFactoryPostProcessor.java
 * 
 * Aimy
 * 上午10:56:27
 */
package tk.codecube.test.custombeanlifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * @author Aimy
 * 2014年9月18日 上午10:56:27
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor{

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.config.BeanFactoryPostProcessor#postProcessBeanFactory(org.springframework.beans.factory.config.ConfigurableListableBeanFactory)
	 */
	@Override
	public void postProcessBeanFactory(
			ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out
				.println("MyBeanFactoryPostProcessor.postProcessBeanFactory()");
		BeanDefinition bd = beanFactory.getBeanDefinition("car");
		if(bd != null)
			bd.getPropertyValues().add("brand", "大牌子");
	}

}
