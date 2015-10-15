package tk.codecube.wx.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import tk.codecube.wx.base.BaseController;
import tk.codecube.wx.base.PropertiesValues;

/**
 * wx 综合Controller
 * 负责所有的微信请求
 * @author aimy
 *	
 */
@Controller
@RequestMapping("/aimy")
public class WxCommonController extends BaseController{
	
	@Autowired
	private WxCommonService wxCommonService;
	
	/**
	 * Des:检查消息是否来自微信如果是则通过验证
	 * 
	 * @param request
	 * @param response
	 * 10:36:57 PM aimy
	 */
	@RequestMapping("/check")
	public void check(HttpServletRequest request,HttpServletResponse response)
	{
		String timeStamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		String signature = request.getParameter("signature");
		
		if(timeStamp==null || nonce==null || echostr==null || signature==null)
		{
			write(response, "验证失败～！");
			return;
		}
		
		List<String> keys = new ArrayList<String>();
		keys.add(timeStamp);
		keys.add(nonce);
		keys.add(PropertiesValues.TOKEN);
		
		Collections.sort(keys);
		
		if(wxCommonService.isFromWx(keys,signature))
		{
			write(response, echostr);
		}

	}
}
