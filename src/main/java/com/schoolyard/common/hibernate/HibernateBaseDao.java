package com.schoolyard.common.hibernate;




import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.schoolyard.common.page.Page;
import com.schoolyard.common.util.QueryBaseParam;
import com.schoolyard.common.util.QueryParam;

/**
 * hibernate DAO基类
 * 
 * 提供hql分页查询，拷贝更新等一些常用功能。
 * @author zhoubin
 *
 * @param <T>
 * @param <ID>
 */
public interface HibernateBaseDao<T, ID extends Serializable> extends
		HibernateSimpleDao {
	
	/**
	 * 保存对象
	 * @param obj
	 * @return
	 */
	public T save(T po);

	/**
	 * @see Session.get(Class,Serializable)
	 * @param id
	 * @return 持久化对象
	 */
	public T get(ID id);

	/**
	 * @see Session.get(Class,Serializable,LockMode)
	 * @param id
	 *            对象ID
	 * @param lock
	 *            是否锁定，使用LockMode.UPGRADE
	 * @return 持久化对象
	 */
	public T get(ID id, boolean lock);
	
	/**
	 * 删除对象
	 * @param paramT
	 */
	public abstract void delete(T paramT);
	
	/**
	 * 通过主键删除对象
	 * @param paramSerializable
	 */
	public abstract void deleteById(ID id);
	
	/**
	 * 查询所有对象
	 * @return
	 */
	public abstract List<T> findAll();
	
	/**
	 * 根据查询条件过滤查询对象列表
	 * @param paramMap
	 * @return
	 */
	 public abstract List<T> findBy(Map<String, Object> paramMap);
	 
	 /**
	  * 根据查询条件like对象列表
	  * @param paramMap
	  * @return
	  */
	 public abstract List<T> findByLike(Map<String, String> paramMap);
	

	/**
	 * 按属性查找对象列表
	 */
	public List<T> findByProperty(String property, Object value);

	/**
	 * 按属性查找唯一对象
	 */
	public T findUniqueByProperty(String property, Object value);
	
	/**
	 * 分页查询
	 * @param page
	 * @return
	 */
	public Page<T> findByPage(Page<T> page);
	
	/**
	 * 
	 * 根据条件进行与查询
	 * @param param 基本查询条件
	 * @return
	 */
	public List<T> findByAnd(QueryBaseParam param);
	
	/**
	 * 
	 * 根据条件进行与查询
	 * @param param 查询条件
	 * @return
	 */
	public List<T> findByAnd(QueryParam param);
	
	/**
	 * 
	 * 根据条件进行或查询
	 * @param param 基本查询条件
	 * @return
	 */
	public List<T> findByOr(QueryBaseParam param);
	
	/**
	 * 
	 * 根据map条件进行或查询
	 * @param param 查询条件
	 * @return
	 */
	public List<T> findByOr(QueryParam param);
	
	/**
	 * 
	 * 重名校验
	 * @param idProperty 主键字段名
	 * @param id 现有的id值
	 * @param property 校验的属性名称
	 * @param value 校验的值
	 * @return 有重名 ture，没有重名 false
	 */
	public boolean duplicatecheck(String idProperty, Object id, String property, Object value);
	
	/**
	 * 
	 * 重名校验
	 * @param idProperty 主键字段名
	 * @param id 现有的id值
	 * @param map 验证条件
	 * @return 有重名 ture，没有重名 false
	 */
	public boolean duplicatecheck(String idProperty, Object id, Map<String, Object> map);
	
	/**
	 * 
	 * 获取max字段的最大值
	 * @param max 字段名
	 * @param Map 查询条件map
	 * @return
	 */
	public Object findMax(String max,Map<String, Object> Map);
	
	/**
	 * 
	 * 获取min字段的最小值
	 * @param min 字段名
	 * @param Map 查询条件map
	 * @return
	 */
	public Object findMin(String min,Map<String, Object> Map);
	
	/**
	 * 
	 * 获取avg字段的平均值
	 * @param avg 字段名
	 * @param Map 查询条件map
	 * @return
	 */
	public Object findAvg(String avg,Map<String, Object> Map);
	
	/**
	 * 
	 * 获取sum字段的和
	 * @param sum 字段名
	 * @param Map 查询条件map
	 * @return
	 */
	public Object findSum(String sum,Map<String, Object> Map);
	

	/**
	 * 
	 * 最值、平均、等统计获取
	 * @param param 与统计条件
	 * @return
	 */
	public Object[] findStatisticByAnd(QueryParam param);
	
	/**
	 * 
	 * 最值、平均、等统计获取
	 * @param param 或统计条件
	 * @return
	 */
	public Object[] findStatisticByOr(QueryParam param);
}
