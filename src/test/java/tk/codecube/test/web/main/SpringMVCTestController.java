/**
 * SpringMVCTestController.java
 * 
 * Aimy
 * 下午11:09:31
 */
package tk.codecube.test.web.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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