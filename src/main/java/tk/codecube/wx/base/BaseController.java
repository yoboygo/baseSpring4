package tk.codecube.wx.base;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;

/**
 * 基础控制器，用于实现通用的方法
 * @author aimy
 *
 */
@Controller
public class BaseController {
	
	/**
	 * Des:返回请求里的Map参数
	 * 
	 * @param request
	 * @return
	 * 10:47:39 PM aimy
	 */
	protected Map<String,Object> getQuiresMap(HttpServletRequest request)
	{
		Map<String,Object> params = new HashMap<String, Object>();
		
		return params;
	}
	
	
	/**
	 * Des:输出字符串
	 * 
	 * @param resonse
	 * @param msg 
	 * 10:26:00 PM aimy
	 */
	protected void write(HttpServletResponse response,String msg)
	{
		PrintWriter outer = null;
		try {
			response.setCharacterEncoding("UTF-8");
			outer = response.getWriter();
			outer.print(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(outer != null)
			{
				outer.flush();
				outer.close();
			}
		}
		
	}
}
