/**
 * TestAutoWried.java
 * 
 * Aimy
 * 上午8:49:01
 */
package tk.codecube.test.springautowried;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Aimy
 * 2014年11月24日 上午8:49:01
 */
@Controller
@RequestMapping("/spcore")
public class TestAutoWried {
	@Autowired
	@Qualifier("carImp1")
	private ICar car1;
	@Autowired
	@Qualifier("tk.codecube.test.springautowried.CarImp2")
	private ICar car2;
	/**
	 *  测试Spring自动注入:bytype 同一个接口有两个实现类时
	 * @auther Aimy
	 * 2014年11月24日 上午8:54:43
	 */
	@RequestMapping("/autowried")
	public void testAutoWrite()
	{
		System.out.println("TestAutoWried.testAutoWrite()");
		
		System.out.println(car1.getColor());		
		System.out.println(car2.getColor());		
	}

}
