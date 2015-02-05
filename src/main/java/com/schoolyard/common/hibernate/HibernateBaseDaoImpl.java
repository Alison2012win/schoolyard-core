package com.schoolyard.common.hibernate;



import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.LockOptions;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.Assert;

import com.schoolyard.common.page.Page;
import com.schoolyard.common.util.QueryBaseParam;
import com.schoolyard.common.util.QueryParam;

/**
 * hibernate DAO基类
 * 
 * 提供hql分页查询，拷贝更新等一些常用功能。
 * 
 * @author zhoubin
 * 
 * @param <T>
 * @param <ID>
 */
public class HibernateBaseDaoImpl<T, ID extends Serializable> extends
		HibernateSimpleDaoImpl implements HibernateBaseDao<T, ID> {
	private Class<T> clazz;

	@SuppressWarnings("unchecked")
	public HibernateBaseDaoImpl() {
		clazz = (Class<T>) ((ParameterizedType) super.getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	/**
	 * @see Session.get(Class,Serializable)
	 * @param id
	 * @return 持久化对象
	 */
	@Override
	public T get(ID id) {
		return get(id, false);
	}

	/**
	 * @see Session.get(Class,Serializable,LockMode)
	 * @param id
	 *            对象ID
	 * @param lock
	 *            是否锁定，使用LockOptions.UPGRADE
	 * @return 持久化对象
	 */
	@Override
	@SuppressWarnings("unchecked")
	public T get(ID id, boolean lock) {
		T entity;
		if (lock) {
			entity = (T) getSession().get(getEntityClass(), id,
					LockOptions.UPGRADE);
		} else {
			entity = (T) getSession().get(getEntityClass(), id);
		}
		return entity;
	}

	/**
	 * 按属性查找对象列表
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<T> findByProperty(String property, Object value) {
		Assert.hasText(property);
		List<T> result = createCriteria(Restrictions.eq(property, value)).list();
		getSession().clear();
		return result;
	}

	/**
	 * 按属性查找唯一对象
	 */
	@Override
	@SuppressWarnings("unchecked")
	public T findUniqueByProperty(String property, Object value) {
		Assert.hasText(property);
		Assert.notNull(value);
		return (T) createCriteria(Restrictions.eq(property, value))
				.uniqueResult();
	}

	/**
	 * 按属性统计记录数
	 * 
	 * @param property
	 * @param value
	 * @return
	 */
	public int countByProperty(String property, Object value) {
		Assert.hasText(property);
		Assert.notNull(value);
		return ((Number) (createCriteria(Restrictions.eq(property, value))
				.setProjection(Projections.rowCount()).uniqueResult()))
				.intValue();
	}

	/**
	 * 按Criterion查询列表数据.
	 * 
	 * @param criterion
	 *            数量可变的Criterion.
	 */
	@SuppressWarnings("unchecked")
	public List findByCriteria(Criterion... criterion) {
		return createCriteria(criterion).list();
	}

	/**
	 * 根据Criterion条件创建Criteria,后续可进行更多处理,辅助函数.
	 */
	public Criteria createCriteria(Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(getEntityClass());
		for (Criterion c : criterions) {
			criteria.add(c);
		}
		return criteria;
	}

	/**
	 * 获得Dao对于的实体类
	 * 
	 * @return
	 */
	public Class<T> getEntityClass() {
		return clazz;
	}

	@Override
	public T save(T po) {
		getSession().saveOrUpdate(po);
		return po;
	}

	@Override
	public void delete(T obj) {
		getSession().delete(obj);
	}

	@Override
	public void deleteById(ID id) {
		delete(get(id));
	}

	@Override
	public List<T> findAll() {
		Criteria criteria = createCriteria();
		List<T> result = criteria.list();
		getSession().clear();
		return result;
	}

	@Override
	public List<T> findBy(Map<String, Object> paramMap) {
		Criteria criteria = createCriteria();
		if (paramMap != null) {
			for (Iterator<String> i = paramMap.keySet().iterator(); i.hasNext();) {
				String name = i.next();
				if (filterProperty(name)) {
					criteria.add(Restrictions.eq(name, paramMap.get(name)));
				} else {
					super.log.error("Could not resolve property:" + name);
				}
			}
		}
		List<T> result = criteria.list();
		getSession().clear();
		return result;
	}

	private boolean filterProperty(String name) {
		try {
			BeanWrapper bw = new BeanWrapperImpl(getEntityClass().newInstance());
			bw.getPropertyValue(name);
		} catch (Exception e) {
			super.log.error("Could not resolve property:" + name, e);
			return false;
		}
		return true;
	}

	@Override
	public List<T> findByLike(Map<String, String> paramMap) {
		Criteria criteria = createCriteria();
		for (Iterator<String> iter = paramMap.keySet().iterator(); iter.hasNext();) {
			String name = iter.next();
			if (filterProperty(name)) {
				criteria.add(Restrictions.like(name,paramMap.get(name), MatchMode.ANYWHERE));
			} else {
				super.log.error("Could not resolve property:" + name);
			}
		}
		List<T> result = criteria.list();
		getSession().clear();
		return result;
	}

	@Override
	public Page<T> findByPage(Page<T> page) {
		Criteria c = createCriteria();
		if(page.getParam() != null){
			c = page.getParam().andCriteria(c);
		}
		
		c.setProjection(null);

		// 获取总条数
		int totalCount = ((Long) c.setProjection(Projections.rowCount()).uniqueResult()).intValue();

		c.setProjection(null);
		c.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);

		// 设置page
		if (page.getStart() > -1) {
			c.setFirstResult(page.getStart());
		}
		if (page.getPagesize() > -1) {
			c.setMaxResults(page.getPagesize());
		}
		List<T> result = c.list();
		getSession().clear();
		page.setResult(result);
		page.setTotal(totalCount);
		return page;
	}
	
	@Override
	public List<T> findByAnd(QueryBaseParam param) {
		Criteria criteria = createCriteria();
		if(null != param){
			criteria = param.andCriteria(criteria);
		}
		List<T> result = criteria.list();
		getSession().clear();
		return result;
	}
	
	@Override
	public List<T> findByAnd(QueryParam param) {
		Criteria criteria = createCriteria();
		if(null != param){
			criteria = param.andCriteria(criteria);
		}
		List<T> result = criteria.list();
		getSession().clear();
		return result;
	}
	
	@Override
	public List<T> findByOr(QueryBaseParam param) {
		Criteria criteria = createCriteria();
		if(null != param){
			criteria = param.orCriteria(criteria);
		}
		List<T> result = criteria.list();
		getSession().clear();
		return result;
	}
	
	@Override
	public List<T> findByOr(QueryParam param) {
		Criteria criteria = createCriteria();
		if(null != param){
			criteria = param.orCriteria(criteria);
		}
		List<T> result = criteria.list();
		getSession().clear();
		return result;
	}

	@Override
	public boolean duplicatecheck(String idProperty, Object id, String property, Object value) {
		if(null == id){
			List<?> list = createCriteria(Restrictions.eq(property, value)).list();
			if(list != null && list.size()>0) {
				return true;
			}
		}else{
			List<?> list = createCriteria(Restrictions.eq(property, value)).add(Restrictions.ne(idProperty, id)).list();
			if(list != null && list.size()>0) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean duplicatecheck(String idProperty, Object id, Map<String, Object> map) {
		Criteria criteria = createCriteria();
		if (map != null) {
			for (Iterator<String> i = map.keySet().iterator(); i.hasNext();) {
				String name = i.next();
				if (filterProperty(name)) {
					criteria.add(Restrictions.eq(name, map.get(name)));
				} else {
					super.log.error("Could not resolve property:" + name);
				}
			}
		}
		if(null == id){
			List<?> list = criteria.list();
			if(list != null && list.size()>0) {
				return true;
			}
		}else{
			List<?> list = criteria.add(Restrictions.ne(idProperty, id)).list();
			if(list != null && list.size()>0) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Object findMax(String max, Map<String, Object> Map) {
		Criteria criteria = createCriteria();
		for (Iterator<String> i = Map.keySet().iterator(); i.hasNext();) {
			String name = i.next();
			if (filterProperty(name)) {
				criteria.add(Restrictions.eq(name, Map.get(name)));
			} else {
				super.log.error("Could not resolve property:" + name);
			}
		}
		return criteria.setProjection(Projections.max(max)).uniqueResult();
	}
	
	@Override
	public Object findMin(String min, Map<String, Object> Map) {
		Criteria criteria = createCriteria();
		for (Iterator<String> i = Map.keySet().iterator(); i.hasNext();) {
			String name = i.next();
			if (filterProperty(name)) {
				criteria.add(Restrictions.eq(name, Map.get(name)));
			} else {
				super.log.error("Could not resolve property:" + name);
			}
		}
		return criteria.setProjection(Projections.min(min)).uniqueResult();
	}

	@Override
	public Object findAvg(String avg, Map<String, Object> Map) {
		Criteria criteria = createCriteria();
		for (Iterator<String> i = Map.keySet().iterator(); i.hasNext();) {
			String name = i.next();
			if (filterProperty(name)) {
				criteria.add(Restrictions.eq(name, Map.get(name)));
			} else {
				super.log.error("Could not resolve property:" + name);
			}
		}
		return criteria.setProjection(Projections.avg(avg)).uniqueResult();
	}

	@Override
	public Object findSum(String sum, Map<String, Object> Map) {
		Criteria criteria = createCriteria();
		for (Iterator<String> i = Map.keySet().iterator(); i.hasNext();) {
			String name = i.next();
			if (filterProperty(name)) {
				criteria.add(Restrictions.eq(name, Map.get(name)));
			} else {
				super.log.error("Could not resolve property:" + name);
			}
		}
		return criteria.setProjection(Projections.sum(sum)).uniqueResult();
	}

	@Override
	public Object[] findStatisticByAnd(QueryParam param) {
		Criteria criteria = param.andCriteria(createCriteria());
		criteria.setProjection(null);
		criteria.setProjection(param.projectionList());
		Object[] result = (Object[]) criteria.uniqueResult();
		return result;
	}
	
	@Override
	public Object[] findStatisticByOr(QueryParam param) {
		Criteria criteria = param.orCriteria(createCriteria());
		criteria.setProjection(null);
		criteria.setProjection(param.projectionList());
		Object[] result = (Object[]) criteria.uniqueResult();
		return result;
	}
	
}
