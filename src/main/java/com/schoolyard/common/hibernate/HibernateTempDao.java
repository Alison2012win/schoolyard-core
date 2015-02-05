package com.schoolyard.common.hibernate;




import java.util.List;
import java.util.Map;

import com.schoolyard.common.page.Page;
import com.schoolyard.common.util.QueryBaseParam;
import com.schoolyard.common.util.QueryParam;

/**
 * hibernate DAO基类
 * 
 * 提供hql分页查询，拷贝更新等一些常用功能。
 * @author dy
 *
 * @param <T>
 * @param <ID>
 */
public interface HibernateTempDao extends HibernateSimpleDao {
	
	/**
	 * 保存对象
	 * @param obj
	 * @return
	 */
	public <T> T save(T po);

	/**
	 * @see Session.get(Class,Serializable)
	 * @param id
	 * @return 持久化对象
	 */
	public <T> T get(Object id ,Class<?> clazz);

	/**
	 * @see Session.get(Class,Serializable,LockMode)
	 * @param id
	 *            对象ID
	 * @param lock
	 *            是否锁定，使用LockMode.UPGRADE
	 * @return 持久化对象
	 */
	public <T> T get(Object id, Class<?> clazz, boolean lock);
	
	/**
	 * 删除对象
	 * @param paramT
	 */
	public abstract <T> void delete(T paramT);
	
	/**
	 * 通过主键删除对象
	 * @param paramSerializable
	 */
	public abstract <T> void deleteById(Object id, Class<?> clazz);
	
	/**
	 * 查询所有对象
	 * @return
	 */
	public abstract <T> List<T> findAll(Class<?> clazz);
	
	/**
	 * 根据查询条件过滤查询对象列表
	 * @param paramMap
	 * @return
	 */
	 public abstract <T> List<T> findBy(Map<String, Object> paramMap, Class<?> clazz);
	 
	 /**
	  * 根据查询条件like对象列表
	  * @param paramMap
	  * @return
	  */
	 public abstract <T> List<T> findByLike(Map<String, String> paramMap, Class<?> clazz);
	

	/**
	 * 按属性查找对象列表
	 */
	public <T> List<T> findByProperty(String property, Object value, Class<?> clazz);

	/**
	 * 按属性查找唯一对象
	 */
	public <T> T findUniqueByProperty(String property, Object value, Class<?> clazz);
	
	/**
	 * 分页查询
	 * @param page 
	 * @return
	 */
	public <T> Page<T> findByPage(Page<T> page, Class<?> clazz);
	
	/**
	 * 
	 * 根据条件进行与查询
	 * @param param 基本查询条件
	 * @return
	 */
	public <T> List<T> findByAnd(QueryBaseParam param, Class<?> clazz);
	
	/**
	 * 
	 * 根据条件进行与查询
	 * @param param 查询条件
	 * @return
	 */
	public <T> List<T> findByAnd(QueryParam param, Class<?> clazz);
	
	/**
	 * 
	 * 根据条件进行或查询
	 * @param param 基本查询条件
	 * @return
	 */
	public <T> List<T> findByOr(QueryBaseParam param, Class<?> clazz);
	
	/**
	 * 
	 * 根据map条件进行或查询
	 * @param param 查询条件
	 * @return
	 */
	public <T> List<T> findByOr(QueryParam param, Class<?> clazz);
	
	/**
	 * 
	 * 重名校验
	 * @param idProperty 主键字段名
	 * @param id 现有的id值
	 * @param property 校验的属性名称
	 * @param value 校验的值
	 * @return 有重名 ture，没有重名 false
	 */
	public boolean duplicatecheck(String idProperty, Object id, String property, Object value, Class<?> clazz);
	
	/**
	 * 
	 * 获取max字段的最大值
	 * @param max 字段名
	 * @param Map 查询条件map
	 * @return
	 */
	public Object findMax(String max,Map<String, Object> Map, Class<?> clazz);
	
	/**
	 * 
	 * 获取min字段的最小值
	 * @param min 字段名
	 * @param Map 查询条件map
	 * @return
	 */
	public Object findMin(String min,Map<String, Object> Map, Class<?> clazz);
	
	/**
	 * 
	 * 获取avg字段的平均值
	 * @param avg 字段名
	 * @param Map 查询条件map
	 * @return
	 */
	public Object findAvg(String avg,Map<String, Object> Map, Class<?> clazz);
	
	/**
	 * 
	 * 获取sum字段的和
	 * @param sum 字段名
	 * @param Map 查询条件map
	 * @return
	 */
	public Object findSum(String sum,Map<String, Object> Map, Class<?> clazz);
	

	/**
	 * 
	 * 最值、平均、等统计获取
	 * @param param 与统计条件
	 * @return
	 */
	public Object[] findStatisticByAnd(QueryParam param, Class<?> clazz);
	
	/**
	 * 
	 * 最值、平均、等统计获取
	 * @param param 或统计条件
	 * @return
	 */
	public Object[] findStatisticByOr(QueryParam param, Class<?> clazz);
	
}
