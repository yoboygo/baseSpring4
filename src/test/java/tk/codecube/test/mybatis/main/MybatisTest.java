package tk.codecube.test.mybatis.main;

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
		
		System.out.println(user.getUserName());
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
	}
	
}
