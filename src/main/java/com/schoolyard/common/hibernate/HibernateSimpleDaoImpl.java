package com.schoolyard.common.hibernate;




import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.internal.CriteriaImpl;
import org.hibernate.transform.ResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.schoolyard.common.page.Pagination;
import com.schoolyard.common.util.MyBeanUtils;

/**
 * hibernate DAO基类
 * 
 * 提供hql分页查询，不带泛型，与具体实体类无关�?
 * 
 * @author liufang
 * 
 */
public abstract class HibernateSimpleDaoImpl implements HibernateSimpleDao{
	/**
	 * 日志，可用于子类
	 */
	protected Logger log = Logger.getLogger(HibernateSimpleDaoImpl.class);
	/**
	 * HIBERNATE order
	 */
	protected static final String ORDER_ENTRIES = "orderEntries";

	/**
	 * 通过HQL查询对象列表
	 * 
	 * @param hql
	 *            hql语句
	 * @param values
	 *            数量可变的参数
	 */
	@Override
	public List<?> find(String hql, Object... values) {
		return createQuery(hql, values).list();
	}

	/**
	 * 通过HQL查询唯一对象
	 */
	@Override
	public Object findUnique(String hql, Object... values) {
		return createQuery(hql, values).uniqueResult();
	}

	/**
	 * 通过Finder获得分页数据
	 * 
	 * @param finder
	 *            Finder对象
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            每页条数
	 * @return
	 */
	@Override
	public Pagination find(Finder finder, int pageNo, int pageSize) {
		int totalCount = countQueryResult(finder);
		Pagination p = new Pagination(pageNo, pageSize, totalCount);
		if (totalCount < 1) {
			p.setList(new ArrayList());
			return p;
		}
		Query query = getSession().createQuery(finder.getOrigHql());
		finder.setParamsToQuery(query);
		query.setFirstResult(p.getFirstResult());
		query.setMaxResults(p.getPageSize());
		if (finder.isCacheable()) {
			query.setCacheable(true);
		}
		List<?> list = query.list();
		p.setList(list);
		return p;
	}

	/**
	 * 通过Finder获得列表数据
	 * 
	 * @param finder
	 * @return
	 */
	@Override
	public List<?> find(Finder finder) {
		Query query = finder.createQuery(getSession());
		List<?> list = query.list();
		return list;
	}

	/**
	 * 根据查询函数与参数列表创建Query对象,后续可进行更多处�?辅助函数.
	 */
	@Override
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

	/**
	 * 通过Criteria获得分页数据
	 * 
	 * @param crit
	 * @param pageNo
	 * @param pageSize
	 * @param projection
	 * @param orders
	 * @return
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Pagination findByCriteria(Criteria crit, int pageNo, int pageSize) {
		CriteriaImpl impl = (CriteriaImpl) crit;
		// 先把Projection、ResultTransformer、OrderBy取出�?清空三�?后再执行Count操作
		Projection projection = impl.getProjection();
		ResultTransformer transformer = impl.getResultTransformer();
		List<CriteriaImpl.OrderEntry> orderEntries;
		try {
			orderEntries = (List) MyBeanUtils
					.getFieldValue(impl, ORDER_ENTRIES);
			MyBeanUtils.setFieldValue(impl, ORDER_ENTRIES, new ArrayList());
		} catch (Exception e) {
			throw new RuntimeException(
					"cannot read/write 'orderEntries' from CriteriaImpl", e);
		}

		int totalCount = ((Number) crit.setProjection(Projections.rowCount())
				.uniqueResult()).intValue();
		Pagination p = new Pagination(pageNo, pageSize, totalCount);
		if (totalCount < 1) {
			p.setList(new ArrayList());
			return p;
		}

		// 将之前的Projection,ResultTransformer和OrderBy条件重新设回�?
		crit.setProjection(projection);
		if (projection == null) {
			crit.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		}
		if (transformer != null) {
			crit.setResultTransformer(transformer);
		}
		try {
			MyBeanUtils.setFieldValue(impl, ORDER_ENTRIES, orderEntries);
		} catch (Exception e) {
			throw new RuntimeException(
					"set 'orderEntries' to CriteriaImpl faild", e);
		}
		crit.setFirstResult(p.getFirstResult());
		crit.setMaxResults(p.getPageSize());
		p.setList(crit.list());
		return p;
	}

	/**
	 * 获得Finder的记录�?�?
	 * 
	 * @param finder
	 * @return
	 */
	@Override
	public int countQueryResult(Finder finder) {
		Query query = getSession().createQuery(finder.getRowCountHql());
		finder.setParamsToQuery(query);
		if (finder.isCacheable()) {
			query.setCacheable(true);
		}
		return ((Number) query.iterate().next()).intValue();
	}

	protected SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getSession() {
		Session session = sessionFactory.getCurrentSession();
        if(null==session || false==session.isOpen()){  
            session = sessionFactory.openSession();
        } 
        return session;  
	}
}
