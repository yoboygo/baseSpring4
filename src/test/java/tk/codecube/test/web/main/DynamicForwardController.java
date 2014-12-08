/**
 * DynamicForwardController.java
 * 
 * Aimy
 * 下午1:11:15
 */
package tk.codecube.test.web.main;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/** 动态跳转
 * @author Aimy
 * 2014年12月2日 下午1:11:15
 */
@Controller
@RequestMapping("/forward")
public class DynamicForwardController {

	/**
	 *  动态跳转方法
	 * @auther Aimy
	 * @param request
	 * @return
	 * 2014年12月2日 下午1:12:19
	 */
	@RequestMapping("/**")
	public String dynamicForward(HttpServletRequest request)
	{
		String ctx = request.getContextPath();
		String urlTmp = request.getRequestURI();
		return urlTmp.replace(ctx, "").replace("forward", "");
	}
}
