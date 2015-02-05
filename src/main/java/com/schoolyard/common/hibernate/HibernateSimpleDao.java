package com.schoolyard.common.hibernate;




import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;

import com.schoolyard.common.page.Pagination;

/**
 * hibernate DAO基类
 * 
 * 提供hql分页查询，不带泛型，与具体实体类无关
 * 
 * @author liufang
 * 
 */
public interface HibernateSimpleDao {

	/**
	 * 通过HQL查询对象列表
	 * 
	 * @param hql
	 *            hql语句
	 * @param values
	 *            数量可变的参�?
	 */
	public List<?> find(String hql, Object... values);

	/**
	 * 通过HQL查询唯一对象
	 */
	public Object findUnique(String hql, Object... values);

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
	public Pagination find(Finder finder, int pageNo, int pageSize);

	/**
	 * 通过Finder获得列表数据
	 * 
	 * @param finder
	 * @return
	 */
	public List<?> find(Finder finder);

	/**
	 * 根据查询函数与参数列表创建Query对象,后续可进行更多处�?辅助函数.
	 */
	public Query createQuery(String queryString, Object... values) ;

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
	public Pagination findByCriteria(Criteria crit, int pageNo, int pageSize);

	/**
	 * 获得Finder的记录�?�?
	 * 
	 * @param finder
	 * @return
	 */
	public int countQueryResult(Finder finder) ;
}
