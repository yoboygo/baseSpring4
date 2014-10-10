/**
 * TagCustomTest.java
 * 
 * Aimy
 * 下午10:21:04
 */
package tk.codecube.test;


import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

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
import tk.codecube.test.message.MailSender;

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
		String keys = "红旗C721,银色,180";
		String[] keyArray = keys.split(",");
		for(String key : keyArray)
		{
			System.out.println("key  ------->"+key);
			String keyEncrypt = DESUtils.getEncryptString(key);
			System.out.println("keyEncrypt-->"+keyEncrypt);
			String keyDecrypt = DESUtils.getDecryptString(keyEncrypt);
			System.out.println("keyDecrypt-->"+keyDecrypt);
		}
	}
	
	/**
	 *  测试解密本地的配置参数
	 * @auther Aimy
	 * 2014年9月30日 下午3:28:24
	 */
	@Test
	public void EncryptPlaceHolderConfigerTest()
	{
		/*
		Resource res = new ClassPathResource("spring/spring-encryptplace-test.xml");
		DefaultListableBeanFactory dlbf = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader xbdr = new XmlBeanDefinitionReader(dlbf);
		xbdr.loadBeanDefinitions(res);
		*/
		
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring/spring-encryptplace-test.xml");
		
		Car car = (Car)ac.getBean("car");
		car.introduce();
	}
	
	/**
	 *  
	 * @auther Aimy
	 * 2014年10月10日 上午10:45:21
	 */
	@Test
	public void LocaleUnilsTest()
	{
//		Locale local = Locale.getDefault();
		//NumberFormat
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		double dn = 1234567890f;
		System.out.println("NumberFormat: "+nf.format(dn));
		//DateFormate
		DateFormat df = DateFormat.getDateInstance();
		System.out.println("DateFormate: "+df.format(new Date()));
		
		//MessageFormat
		String pattenCN = "{0} 你好，你于 {1} 在工商银行存入 {2} 元！";
		String pattenUS = "At {1,time,short} ON {1,date,long} , {0} paid {2,number,currency} !";
		
		//Dynamic params
		Object[] params = {"Jhon",new GregorianCalendar().getTime(),1E3};
		String msg1 = MessageFormat.format(pattenCN, params);
		System.out.println("msg1: "+msg1);
		
		MessageFormat mf = new MessageFormat(pattenUS, Locale.US);
		String msg2 = mf.format(params);
		System.out.println("msg2: "+msg2);
	}
	
	/**
	 *  Spring Message Test
	 * @auther Aimy
	 * 2014年10月10日 下午1:48:02
	 */
	@Test
	@SuppressWarnings("resource")
	public void MessageTest()
	{
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/spring-message-test.xml");
		MailSender ms = (MailSender)ctx.getBean("mailSender");
		ms.SendMail("Jhon");
	}
}
