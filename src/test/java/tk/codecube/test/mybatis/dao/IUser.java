package tk.codecube.test.mybatis.dao;

import tk.codecube.test.mybatis.model.User;

public interface IUser {
	
	public User selectById(long id);
	
	public void add(User user);
	
	public void deleteById(long id);
	
}
