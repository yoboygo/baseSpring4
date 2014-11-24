/**
 * SpringMVCTestController.java
 * 
 * Aimy
 * 下午11:09:31
 */
package tk.codecube.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import tk.codecube.test.springautowried.ICar;

/**
 * @author Aimy
 * 2014年8月19日 下午11:09:31
 */
@Controller
@RequestMapping("/test")
public class SpringMVCTestController {
	
	public SpringMVCTestController()
	{
		System.out.println("SpringMVCTestController.SpringMVCTestController()");
	}
	
	@RequestMapping
	public String index()
	{
		System.out.println("SpringMVCTestController.index()");
		
		return "/index";
	}

}
