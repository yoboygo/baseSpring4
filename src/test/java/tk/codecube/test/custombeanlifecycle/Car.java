/**
 * ICar.java
 * 
 * Aimy
 * 上午9:04:12
 */
package tk.codecube.test.custombeanlifecycle;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/** Spring Bean Life cycle
 * @author Aimy
 * 2014年9月10日 上午9:04:12
 */
public class Car implements BeanFactoryAware, BeanNameAware, InitializingBean,
		DisposableBean {

	private String brand;
	private String color;
	private int maxSpeed;
	
	private BeanFactory beanFactory;
	private String beanName;
	
	public Car()
	{
		System.out.println("ICar.Car():------------调用Car的构造函数-----------");
	}
	
	public String getBrand() {
		System.out.println("------------获取Brand: "+brand+"-----------");
		return brand;
	}

	public void setBrand(String brand) {
		System.out.println("------------设置Brand: "+brand+"-----------");
		this.brand = brand;
	}

	public String getColor() {
		System.out.println("------------获取Color: "+color+"-----------");
		return color;
	}

	public void setColor(String color) {
		System.out.println("------------设置Color: "+color+"-----------");
		this.color = color;
	}

	public int getMaxSpeed() {
		System.out.println("------------获取MaxSpeed: "+maxSpeed+"-----------");
		return maxSpeed;
	}

	public void setMaxSpeed(int maxSpeed) {
		System.out.println("------------设置MaxSpeed: "+maxSpeed+"-----------");
		this.maxSpeed = maxSpeed;
	}

	public String getBeanName() {
		System.out.println("------------获取BeanName: "+beanName+"-----------");
		return beanName;
	}

	public BeanFactory getBeanFactory() {
		return beanFactory;
	}

	public void introduce()
	{
		System.out.println(ReflectionToStringBuilder.toString(this));
	}

	public void myInit()
	{
		System.out.println("调用 XML中Init-Method定义的myInit()方法，将MaxSpeed设置成240!");
		this.maxSpeed = 240;
	}
	
	public void myDestroy()
	{
		System.out.println("调用 XML中Destroy-Method定义的myDestroy()方法，将MaxSpeed设置成240!");
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.DisposableBean#destroy()
	 */
	@Override
	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("DisposableBean.destroy()");
	}

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("InitializingBean.afterPropertiesSet()");
	}

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.BeanNameAware#setBeanName(java.lang.String)
	 */
	@Override
	public void setBeanName(String name) {
		System.out.println("ICar.setBeanName()");
		this.beanName = name;

	}

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.BeanFactoryAware#setBeanFactory(org.springframework.beans.factory.BeanFactory)
	 */
	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		System.out.println("ICar.setBeanFactory()");
		this.beanFactory = beanFactory;
	}

}
