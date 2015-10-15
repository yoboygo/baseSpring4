/**
 * SpringMVCTestController.java
 * 
 * Aimy
 * 下午11:09:31
 */
package tk.codecube.main.web.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
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
	public String index(@RequestHeader(value="Accept-Encoding",required=false) String encoder)
	{
		
		System.out.println("SpringMVCTestController.index()----->Encoding:"+encoder);
		
		return "/index";
	}
}
