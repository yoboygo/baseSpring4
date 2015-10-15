/**
 * EncryptPropertyPlaceHolederConfiger.java
 * 
 * Aimy
 * 下午2:41:49
 */
package tk.codecube.test.encrypt;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import tk.codecube.test.DESUtils;

/** 对配置文件进行加密
 * @author Aimy
 * 2014年9月30日 下午2:41:49
 */
public class EncryptPropertyPlaceHolederConfiger extends
		PropertyPlaceholderConfigurer {

	private List<String> encryptPropNames = new ArrayList<String>();
	
	public EncryptPropertyPlaceHolederConfiger()
	{
		encryptPropNames.add("userName");
		encryptPropNames.add("password");
	}
	/*
	public EncryptPropertyPlaceHolederConfiger(String... args)
	{
		for(String arg : args)
		{
			encryptPropNames.add(arg);
		}
	}
	*/
	public EncryptPropertyPlaceHolederConfiger(String args)
	{
		System.out
				.println("EncryptPropertyPlaceHolederConfiger.EncryptPropertyPlaceHolederConfiger()===>"+args);
		String[]  argsArray = args.split(",");
		for(String arg : argsArray)
		{
			encryptPropNames.add(arg);
		}
	}
	
	/**
	 * 解密 需要解密的属性
	 * @auther Aimy
	 * @param propertyName
	 * @param propertyValue
	 * @return
	 * 2014年9月30日 下午2:57:50
	 */
	protected String convertProperty(String propertyName, String propertyValue)
	{
		System.out.println(propertyName);
		if(isNeedDecrypt(propertyName))
			return DESUtils.getDecryptString(propertyValue);
		return convertPropertyValue(propertyValue);
	}
	
	
	/**
	 *  判断是否需要解密
	 * @auther Aimy
	 * @param propertyName
	 * @return
	 * 2014年9月30日 下午2:56:08
	 */
	private boolean isNeedDecrypt(String propertyName)
	{
		return encryptPropNames.contains(propertyName);
	}
}
