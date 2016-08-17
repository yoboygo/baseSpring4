package tk.codecube.test.mybatis.dao;

import java.util.List;

import tk.codecube.test.mybatis.model.User;

public interface IUser {
	
	public User selectById(long id);
	
	public List<User> findAll();
	
	public void add(User user);
	
	public void deleteById(long id);
	
	public void update(User user);
	
}
