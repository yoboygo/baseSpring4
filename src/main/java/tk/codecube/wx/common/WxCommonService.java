package tk.codecube.wx.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.stereotype.Service;

/**
 * 微信综合处理Service
 * @author aimy
 *
 */
@Service
public class WxCommonService {

	/**
	 * Des:判断当前的请求是否来自微信服务器
	 * 
	 * @param keys
	 * @return
	 * 11:00:46 PM aimy
	 */
	public boolean isFromWx(List<String> keys,String signature) {
		String keystr = "";
		for(String key : keys)
		{
			keystr += key;
		}
		try {
			MessageDigest md = MessageDigest.getInstance("SHA1");
			md.update(keystr.getBytes());
			String code = new String(md.digest());
			if(code.equals(signature))
			{
				return true;
			}else
			{
				return false;
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return false;
		}
	}
}
