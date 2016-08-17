package tk.codecube.test.mybatis.main;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import tk.codecube.test.mybatis.dao.IUser;
import tk.codecube.test.mybatis.model.User;

/**
 * @Description MyBatis测试
 * @author jianlong.song bpqqop@163.com
 * @date 2016年7月26日 下午1:32:51
 */
public class MybatisTest {

	/**
	 * @Description Select User 测试
	 * @author jianlong.song bpqqop@163.com 
	 * @date 2016年7月26日 下午1:33:40
	 */
	@Test
	public void selectUserTest()
	{
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("/spring/spring-mybatis.xml");
		SqlSessionFactory sessionFactory = (SqlSessionFactory) ac.getBean("sqlSessionFactory");
		
		SqlSession session = sessionFactory.openSession();
		IUser iUser = session.getMapper(IUser.class);
		User user = iUser.selectById(1);
		session.close();
		ac.close();
		System.out.println(user.getUserName());
	}
	
	/**
	 * @Description 查找所有
	 * @author jianlong.song bpqqop@163.com 
	 * @date 2016年7月27日 上午11:01:02
	 */
	@Test
	public void findAllUserTest()
	{
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("/spring/spring-mybatis.xml");
		SqlSessionFactory sessionFactory = (SqlSessionFactory) ac.getBean("sqlSessionFactory");
		
		SqlSession session = sessionFactory.openSession();
		IUser iUser = session.getMapper(IUser.class);
		
		List<User> userList = iUser.findAll();
		for(User u:userList)
		{
			System.out.println(u.getUserName());
		}
		ac.close();
		session.close();
	}
	
	/**
	 * @Description 添加用户
	 * @author jianlong.song bpqqop@163.com 
	 * @date 2016年7月26日 下午3:41:59
	 */
	@Test
	public void addUserTest()
	{
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("/spring/spring-mybatis.xml");
		SqlSessionFactory sessionFactory = (SqlSessionFactory) ac.getBean("sqlSessionFactory");
		
		SqlSession session = sessionFactory.openSession();
		IUser iUser = session.getMapper(IUser.class);
		
		User user = new User();
		user.setUserName("蒙牛");
		user.setPassWord("222222");
		iUser.add(user);
		session.close();
		ac.close();
		System.out.println(user.getUserName());
	}
	
	/**
	 * @Description 删除用户
	 * @author jianlong.song bpqqop@163.com 
	 * @date 2016年7月26日 下午4:10:21
	 */
	@Test
	public void deleteUserTest()
	{
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("/spring/spring-mybatis.xml");
		SqlSessionFactory sessionFactory = (SqlSessionFactory) ac.getBean("sqlSessionFactory");
		
		SqlSession session = sessionFactory.openSession();
		IUser iUser = session.getMapper(IUser.class);
		iUser.deleteById(2);
		session.close();
		ac.close();
	}
	
	/**
	 * @Description 更新测试
	 * @author jianlong.song bpqqop@163.com 
	 * @date 2016年7月27日 上午11:12:22
	 */
	@Test
	public void updateUserTest()
	{
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("/spring/spring-mybatis.xml");
		SqlSessionFactory sessionFactory = (SqlSessionFactory) ac.getBean("sqlSessionFactory");
		
		SqlSession session = sessionFactory.openSession();
		IUser iUser = session.getMapper(IUser.class);
		
		User u = new User();
		u.setId(1);
		u.setUserName("th");
		u.setPassWord("111111");
		iUser.update(u);
		
		session.close();
		ac.close();
	}
	
}
