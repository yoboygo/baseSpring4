/**
 * UserBeanDefinitionParser.java
 * 
 * Aimy
 * 下午9:55:04
 */
package tk.codecube.test.customelement;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * @author Aimy
 * 2014年9月3日 下午9:55:04
 */
@SuppressWarnings("unchecked")
public class UserBeanDefinitionParser extends
		AbstractSingleBeanDefinitionParser {

	//element 对应的类
	@SuppressWarnings("rawtypes")
	protected Class getBeanClass(Element element)
	{
		return User.class;
	}
	
	//从 element 中解析并提取对应的元素
	protected void doParse(Element element,BeanDefinitionBuilder bean)
	{
		String id = element.getAttribute("id");
		String userName = element.getAttribute("userName");
		String passWord = element.getAttribute("passWord");
		String nickName = element.getAttribute("nickName");
		
		//将提取的数据放入到BeanDefinitionBuilder中，待到完成所有bean的解析后统一注册到beanFactory中
		if(StringUtils.hasText(id))
			bean.addPropertyValue("id", id);
		if(StringUtils.hasText(userName))
			bean.addPropertyValue("userName", userName);
		if(StringUtils.hasText(userName))
			bean.addPropertyValue("passWord", passWord);
		if(StringUtils.hasText(nickName))
			bean.addPropertyValue("nickName", nickName);
	}
}
