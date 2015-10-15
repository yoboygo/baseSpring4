/**
 * User.java
 * 
 * Aimy
 * 下午9:45:17
 */
package tk.codecube.test.customelement;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * @author Aimy
 * 2014年9月3日 下午9:45:17
 */
public class User {

	private String id;
	private String userName;
	private String passWord;
	private String nickName;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	public String toString()
	{
		return ReflectionToStringBuilder.toString(this);
	}
}
