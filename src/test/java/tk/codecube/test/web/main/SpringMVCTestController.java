/**
 * SpringMVCTestController.java
 * 
 * Aimy
 * 下午11:09:31
 */
package tk.codecube.test.web.main;

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
	
	/**
	 * 跳转到fusionCharts
	 * @auther Aimy
	 * @return
	 * 2014年12月2日 上午10:07:14
	 */
	@RequestMapping("/fcharts")
	public String toFusionCharts()
	{
		return "/fusioncharts";
	}

}
