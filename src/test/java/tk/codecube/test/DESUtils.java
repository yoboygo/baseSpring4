/**
 * DESUtils.java
 * 
 * Aimy
 * 上午10:47:25
 */
package tk.codecube.test;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

import org.springframework.util.Base64Utils;

/** DES加密工具类
 * @author Aimy
 * 2014年9月30日 上午10:47:25
 */
public class DESUtils {

	private static Key key;
	private static String KEY_STR = "myKey";
	
	static{
		try{
			
			KeyGenerator generator = KeyGenerator.getInstance("DES");
			generator.init(new SecureRandom(KEY_STR.getBytes()));
			key = generator.generateKey();
			generator = null;
			
		}catch(Exception e)
		{
			System.err.println(e.getStackTrace());
		}
	}
	
	/**
	 *  对字符串进行DES加密，返回Base64加密的字符串
	 * @auther Aimy
	 * @param str
	 * @return
	 * 2014年9月30日 上午10:55:57
	 */
	public static String getEncryptString(String str)
	{
		
		try {
			
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] keyEncryptBytes = cipher.doFinal(str.getBytes("UTF-8"));
			
			return Base64Utils.encodeToString(keyEncryptBytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return str;
	}
	
	/**
	 *  对DES加密的数据解密
	 * @auther Aimy
	 * @param str
	 * @return
	 * 2014年9月30日 上午11:08:13
	 */
	public static String getDecryptString(String str)
	{
		try {
			byte[] keyValueBytes = Base64Utils.decode(str.getBytes("UTF-8"));
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.DECRYPT_MODE, key);
			
			byte[] decrpyBytes = cipher.doFinal(keyValueBytes);
			
			return new String(decrpyBytes,"UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}
}
