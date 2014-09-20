/**
 * MyNamespaceHandler.java
 * 
 * Aimy
 * 下午10:03:21
 */
package tk.codecube.test.customelement;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @author Aimy
 * 2014年9月3日 下午10:03:21
 */
public class MyNamespaceHandler extends NamespaceHandlerSupport {

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.xml.NamespaceHandler#init()
	 */
	@Override
	public void init() {
		registerBeanDefinitionParser("user", new UserBeanDefinitionParser());

	}

}
