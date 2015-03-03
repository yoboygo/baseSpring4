/**
 * BaseDao.java
 * 
 * Aimy
 * 下午1:26:02
 */
package tk.codecube.test.web.base;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

/** 实体Dao基类
 * @author Aimy
 * 2015年2月9日 下午1:26:02
 */
public class BaseDao<T extends BaseModel> extends HibernateDaoSupport {

	/**
	 * 子类的泛型类型
	 */
	private Class<T> clazz;
	
	
	/**
	 * 利用反射获取子类的泛型类型
	 */
	@SuppressWarnings("unchecked")
	public BaseDao()
	{
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		this.clazz = (Class<T>) pt.getActualTypeArguments()[0];
	}
	
	/**
	 *  注入本DAO使用的SessionFactory
	 * @author Aimy
	 * @param sessionFactory
	 * 2015年2月9日 下午2:26:34
	 */
	@Autowired
	public void setSuperSessionFactory(SessionFactory sessionFactory)
	{
		super.setSessionFactory(sessionFactory);
	}
	
	/**
	 *  保存,返回主键
	 * @author Aimy
	 * @param entity
	 * 2015年2月9日 下午2:29:37
	 */
	public String save(T entity)
	{
		super.getHibernateTemplate().save(entity);
		return entity.getId();
	}
	
	/**
	 *  删除实体
	 * @author Aimy
	 * @param entity
	 * 2015年2月9日 下午2:33:53
	 */
	public void del(T entity)
	{
		super.getHibernateTemplate().delete(entity);
	}
	
	/**
	 *  更新实体
	 * @author Aimy
	 * @param entity
	 * 2015年2月9日 下午2:34:42
	 */
	public void update(T entity)
	{
		super.getHibernateTemplate().update(entity);
	}
	
	/**
	 *  根据Id，获取对象
	 * @author Aimy
	 * @param id
	 * @return
	 * 2015年2月9日 下午2:41:53
	 */
	public T get(String id)
	{
		return super.getHibernateTemplate().get(this.clazz, id);
	}
	
	/**
	 *  根据HQL查询结果集
	 * @author Aimy
	 * @param hql
	 * @return
	 * 2015年2月9日 下午2:46:44
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByHql(String hql)
	{
		return (List<T>) super.getHibernateTemplate().find(hql);
	}
	
	/**
	 *  根据SQL返回查询结果
	 * @author Aimy
	 * @param sql
	 * @return
	 * 2015年2月9日 下午2:56:07
	 */
	public List<?> findBySql(String sql)
	{
		SQLQuery query = super.getSessionFactory().getCurrentSession().createSQLQuery(sql);
		return query.list();
	}
}
