package org.forten.si.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

@Repository("hibernateDao")
public class HibernateDao {
	@Resource
	private SessionFactory sf;

	/**
	 * 从当前线程上获取Hibernate的Session
	 * 
	 * @return Session
	 */
	public Session getSession() {
		return sf.getCurrentSession();
	}

	/**
	 * 持久化对象到数据库中
	 * 
	 * @param entity
	 *            要被持久化的对象
	 */
	public <T> void save(T entity) {
		getSession().save(entity);
	}

	/**
	 * 更新对象到数据库中
	 * 
	 * @param entity
	 *            要被更新的对象
	 */
	public <T> void update(T entity) {
		getSession().update(entity);
	}

	/**
	 * 保存或更新对象到数据库中
	 * 
	 * @param entity
	 *            要被保存或更新的对象
	 */
	public <T> void saveOrUpdate(T entity) {
		getSession().saveOrUpdate(entity);
	}

	/**
	 * 从数据库中删除对象
	 * 
	 * @param entity
	 *            要被删除的对象
	 */
	public <T> void delete(T entity) {
		getSession().delete(entity);
	}

	/**
	 * 从数据库中删除对象
	 * 
	 * @param id
	 *            要被删除对象的id
	 * @param clazz
	 *            要被删除对象的类型
	 * @return 删除数据的数量（0或1）
	 */
	public <T> int delete(Serializable id, Class<T> clazz) {
		String hql = "DELETE FROM " + clazz.getName() + "WHERE id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, id);
		return query.executeUpdate();
	}

	/**
	 * 按照id延迟查询对象
	 * 
	 * @param id
	 *            对象的id
	 * @param clazz
	 *            对象的类型
	 * @return 实体对象的代理
	 */
	public <T> T loadById(Serializable id, Class<T> clazz) {
		return getSession().load(clazz, id);
	}

	/**
	 * 按照id即时查询对象
	 * 
	 * @param id
	 *            对象的id
	 * @param clazz
	 *            对象的类型
	 * @return 实体对象
	 */
	public <T> T getById(Serializable id, Class<T> clazz) {
		return getSession().get(clazz, id);
	}

	/**
	 * 通过hql和参数Map来进行条件查询
	 * 
	 * @param hql
	 *            HQL语句
	 * @param params
	 *            命名参数Map，key为命名参数的名称，value为全名参数的值
	 * @return 对象List
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> findBy(String hql, Map<String, Object> params) {
		Query query = getSession().createQuery(hql);
		for (Entry<String, Object> kv : params.entrySet()) {
			query.setParameter(kv.getKey(), kv.getValue());
		}
		return query.getResultList();
	}

	/**
	 * 通过hql和参数Map来进行分页条件查询
	 * 
	 * @param hql
	 *            HQL语句
	 * @param params
	 *            命名参数Map，key为命名参数的名称，value为全名参数的值
	 * @param first
	 *            从第n条数据开始查询
	 * @param max
	 *            每页的记录数量
	 * @return 对象List
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> findBy(String hql, Map<String, Object> params, int first, int max) {
		Query query = getSession().createQuery(hql);
		for (Entry<String, Object> kv : params.entrySet()) {
			query.setParameter(kv.getKey(), kv.getValue());
		}
		query.setFirstResult(first);
		query.setMaxResults(max);
		return query.getResultList();
	}

	public <T> T findOneBy(String hql, Map<String, Object> params) {
		Query query = getSession().createQuery(hql);
		for (Entry<String, Object> kv : params.entrySet()) {
			query.setParameter(kv.getKey(), kv.getValue());
		}
		try {
			return (T) query.getSingleResult();
		} catch (NoResultException e) {
			// e.printStackTrace();
			return null;
		}
	}
}
