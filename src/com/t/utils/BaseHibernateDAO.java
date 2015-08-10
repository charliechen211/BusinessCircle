package com.t.utils;


import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.NoResultException;

import org.hibernate.Criteria;
import org.hibernate.NonUniqueResultException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.SQLGrammarException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;



@SuppressWarnings("unchecked")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class BaseHibernateDAO<T, PK extends Serializable>{

	private Logger logger = null;
	
	private Logger getLogger() {
		if (null == logger) {
			logger = LoggerFactory.getLogger(this.getClass());
		}
		return logger;
	}

	protected SessionFactory sessionFactory;

	protected Class<T> entityClass;

	public BaseHibernateDAO() {
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public PK save(T entity) {
		Assert.notNull(entity);
		PK id = (PK)getSession().save(entity);
		getLogger().info("save entity: {}", entity);
		return id;
	}
	
	/**add by zeng 2011-8-19**/
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void update(T entity) {
		Assert.notNull(entity);
		getSession().update(entity);
		getLogger().info("update entity: {}", entity);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void saveOrUpdate(T entity) {
		Assert.notNull(entity);
		getSession().saveOrUpdate(entity);
		getLogger().info("save entity: {}", entity);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(T entity) {
		Assert.notNull(entity);
		getSession().delete(entity);
		getLogger().info("delete entity: {}", entity);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(PK id) {
		Assert.notNull(id);
		delete(get(id));
	}

	public List<T> findAll() {
		return findByCriteria();
	}

//	public Page<T> findAll(Page<T> page) {
//		return findByCriteria(page);
//	}

	public T get(final PK id) {
		try {
			return null != id ? (T)getSession().get(entityClass, id) : null;
		} catch (SQLGrammarException e) {
			return null;
		}
	}

	public T load(final PK id) {
		try {
			return null != id ? (T)getSession().load(entityClass, id) : null;
		} catch (SQLGrammarException e) {
			return null;
		}
	}

	@SuppressWarnings("rawtypes")
	public List find(String hql, Object... values) {
		return createQuery(hql, values).list();
	}

	@SuppressWarnings("rawtypes")
	public List findBySQL(String sql, Map<String, Object> paramMap) {
		return createSQLQuery(sql, paramMap).list();
	}

	public Object findUnique(String hql, Object... values) {
		Object res = null;
		try {
			res = createQuery(hql, values).uniqueResult();
		} catch (NoResultException ex) {
			return res;
		} catch (NonUniqueResultException ex) {
//			LogUtils.error(logger, "GenericDaoImp::findUnique() has no unique result", ex);
			return null;
		}
		return res;
	}

	public Integer findInt(String hql, Object... values) {
		return (Integer) findUnique(hql, values);
	}

	public Long findLong(String hql, Object... values) {
		return (Long) findUnique(hql, values);
	}

	public List<T> findByCriteria(Criterion... criterion) {
		return createCriteria(criterion).list();
	}

	public List<T> findByCriteria(Criteria criteria) {
		if (criteria == null) {
			return null;
		}
		return criteria.list();
	}

	public List<T> findByProperty(String propertyName, Object value) {
		Assert.hasText(propertyName);
		return createCriteria(Restrictions.eq(propertyName, value)).list();
	}

	public T findUniqueByProperty(String propertyName, Object value) {
		Assert.hasText(propertyName);
		return (T) createCriteria(Restrictions.eq(propertyName, value)).uniqueResult();
	}
	
	public Query createQuery(String queryString, Object... values) {
		Assert.hasText(queryString);
		Query queryObject = getSession().createQuery(queryString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				queryObject.setParameter(i, values[i]);
			}
		}
		return queryObject;
	}

	public Query createSQLQuery(String querySQL, Map<String, Object> parameterMap) {
		Assert.hasText(querySQL);
		Query queryObject = getSession().createSQLQuery(querySQL);
		if (parameterMap != null && parameterMap.size() > 0) {
			Iterator<Entry<String, Object>> it = parameterMap.entrySet().iterator();
			while (it.hasNext()) {
				Entry<String, Object> entry = it.next();
				String key = entry.getKey();
				Object value = entry.getValue();
				queryObject.setParameter(key, value);
			}
		}
		return queryObject;
	}

	public Criteria createCriteria(Criterion... criterions) {
		Criteria criteria = this.getCriteria();
		for (Criterion c : criterions) {
			if (c != null) {
				criteria.add(c);
			}
		}
		return criteria;
	}

	public Criteria getCriteria() {
		Criteria criteria = getSession().createCriteria(entityClass);
		return criteria;
	}

	public boolean isPropertyUnique(String propertyName, Object newValue, Object orgValue) {
		if (newValue == null || newValue.equals(orgValue))
			return true;

		Object object = findUniqueByProperty(propertyName, newValue);
		return (object == null);
	}

	public int batchUpdateOrDelete(String hql, Map<String, Object> parameterMap) {
		Assert.hasText(hql);
		Query queryObject = getSession().createQuery(hql);
		if (parameterMap != null && parameterMap.size() > 0) {
			Iterator<Entry<String, Object>> it = parameterMap.entrySet().iterator();
			while (it.hasNext()) {
				Entry<String, Object> entry = it.next();
				String key = entry.getKey();
				Object value = entry.getValue();
				queryObject.setParameter(key, value);
			}
		}
		return queryObject.executeUpdate();
	}

	public int batchUpdateOrDeleteBySql(String sql, Map<String, Object> parameterMap) {
		Assert.hasText(sql);
		Query queryObject = getSession().createSQLQuery(sql);
		if (parameterMap != null && parameterMap.size() > 0) {
			Iterator<Entry<String, Object>> it = parameterMap.entrySet().iterator();
			while (it.hasNext()) {
				Entry<String, Object> entry = it.next();
				String key = entry.getKey();
				Object value = entry.getValue();
				queryObject.setParameter(key, value);
			}
		}
		return queryObject.executeUpdate();
	}

	public void flush() {
		this.getSession().flush();

	}
	
	public void evict(Object obj) {
		this.getSession().evict(obj);
	}
	
}