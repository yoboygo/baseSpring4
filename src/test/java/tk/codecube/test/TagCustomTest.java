/**
 * TagCustomTest.java
 * 
 * Aimy
 * 下午10:21:04
 */
package tk.codecube.test;


import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import tk.codecube.test.custombeanlifecycle.Car;
import tk.codecube.test.custombeanlifecycle.MyBeanPostProcessor;
import tk.codecube.test.custombeanlifecycle.MyInstantiationAwareBeanPostProcessor;
import tk.codecube.test.custombeanlifecycle.Popoj;
import tk.codecube.test.customelement.User;
import tk.codecube.test.lookupandreplace.Boss1;
import tk.codecube.test.lookupandreplace.MagicBox;

/**
 * @author Aimy
 * 2014年9月3日 下午10:21:04
 */
public class TagCustomTest {

	/**
	 * 描述:解析自定义标签
	 * @auther Aimy
	 * @param args
	 * 2014年9月3日 下午10:21:04
	 */
	@Test
	public void springCustomTag() {
		ApplicationContext bf = new ClassPathXmlApplicationContext("spring/spring-tag-test.xml");
		
		User user = (User)bf.getBean("testbean");
		System.err.println(user.toString());
		
	}

	/**
	 * 描述:对Spring解析完成的Bean进行处理
	 * @auther Aimy
	 * 2014年9月10日 上午8:59:16
	 */
	@Test
	public void springBeanLife(){
		Resource res = new ClassPathResource("spring/spring-beanlifecycle-test.xml");
		BeanFactory bf = new XmlBeanFactory(res);
		
		((ConfigurableBeanFactory)bf).addBeanPostProcessor(new MyBeanPostProcessor());
		((ConfigurableBeanFactory)bf).addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
	
		Car car1 = (Car)bf.getBean("car");
		car1.introduce();
		car1.setColor("红色");
		
		Car car2 = (Car)bf.getBean("car");
		System.out.println("car1==car2 : "+(car1==car2));
		
		//测试接口BeanNameAware，BeanFactoryAware在BeanPostProcessor中有没有用：结论 没用~
		System.out.println();
		System.out.println("================>POPOJ START<===================");
		Popoj po = (Popoj)bf.getBean("popoj");
		System.out.println(po.toString());
		System.out.println("================> POPOJ END <===================");
		System.out.println();
		
		((XmlBeanFactory)bf).destroySingletons();
	}
	 
	/**
	 * 描述:Spring4.1.0的写法
	 * @auther Aimy
	 * 2014年9月18日 上午8:52:00
	 */
	@Test
	public void springBeanLife4(){
		Resource res = new ClassPathResource("spring/spring-beanlifecycle-test.xml");
		
		DefaultListableBeanFactory dlb = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader xbdr = new XmlBeanDefinitionReader(dlb);
		xbdr.loadBeanDefinitions(res);
		
		((ConfigurableBeanFactory)dlb).addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
		((ConfigurableBeanFactory)dlb).addBeanPostProcessor(new MyBeanPostProcessor());

		Car test = (Car) dlb.getBean("car");
//		test.setBrand("奥玛");
		test.introduce();
		
		Popoj poj = (Popoj)dlb.getBean("popoj");
		System.out.println(poj.toString());
		
		((DefaultListableBeanFactory)dlb).destroySingletons();
	}
	
	
	/**
	 * 描述:测试ApplicationContext And BeanFacoryPostProcessor
	 * @auther Aimy
	 * 2014年9月18日 上午11:04:31
	 */
	@Test
	public void springBeanLifeBeanFactory(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring/spring-beanlifecycle-test.xml");
		
		Car test = (Car) ac.getBean("car");
		
//		test.setBrand("奥玛");
		test.introduce();
		
		Popoj poj = (Popoj)ac.getBean("popoj");
		System.out.println(poj.toString());
		
	}
	
	/**
	 * 描述:springLookUpTest
	 * @auther Aimy
	 * 2014年9月19日 上午9:19:44
	 */
	@Test
	public void springLookUpTest()
	{
		Resource rse = new ClassPathResource("spring/spring-lookupandreplace-test.xml");
		DefaultListableBeanFactory dlb = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader xdr = new XmlBeanDefinitionReader(dlb);
		xdr.loadBeanDefinitions(rse);
		
		MagicBox mb = (MagicBox) dlb.getBean("magicBox");
		Car car1 = mb.getCar();
		car1.setBrand("car1");
		car1.introduce();
		
		Car car2 = mb.getCar();
		car2.introduce();
	}
	
	/**
	 * 描述:SpringReplacedTest
	 * @auther Aimy
	 * 2014年9月19日 上午9:57:39
	 */
	@Test
	public void springReplacedTest()
	{
		Resource rse = new ClassPathResource("spring/spring-lookupandreplace-test.xml");
		DefaultListableBeanFactory dlb = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader xdr = new XmlBeanDefinitionReader(dlb);
		xdr.loadBeanDefinitions(rse);
		
		Boss1 boss1 = (Boss1) dlb.getBean("boss1");
		boss1.getCar().introduce();
	}
	
	/**
	 *  base64 DES 加密解密测试
	 * @auther Aimy
	 * 2014年9月30日 下午1:48:45
	 */
	@Test
	public void Base64UtilsTest()
	{
		String key = "123123";
		System.out.println("key  ------->"+key);
		String keyEncrypt = DESUtils.getEncryptString(key);
		System.out.println("keyEncrypt-->"+keyEncrypt);
		String keyDecrypt = DESUtils.getDecryptString(keyEncrypt);
		System.out.println("keyDecrypt-->"+keyDecrypt);
	}
}
