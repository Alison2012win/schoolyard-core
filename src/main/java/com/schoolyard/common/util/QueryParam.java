/**
 * 
 */
package com.schoolyard.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 * 
 * <p>Title:查询工具类 </p>
 * <p>Description: 查询参数</p>
 * 
 * @version 1.0 
 * Created by [dy] [2014-5-14]
 * Midified by [修改人] [修改时间]
 * 
 */
public class QueryParam extends QueryBaseParam{

	private Map<String, Object> ge;//>=查询条件 <属性名,值>
	private Map<String, Object> le;//<=查询条件 <属性名,值>
	private Map<String, Object> ilike;//忽略大小写like查询条件 <属性名,值>
	private List<String> isnull;//为空查询条件 [属性名,...]
	private List<String> notNull;//非空查询条件 [属性名,...]
	private Map<String, Object[]> notIn;//notIn查询条件  <属性名,notin值，...>
	private List<String> max;//获取最大值[属性名，...]
	private List<String> min;//获取最小值[属性名，...]
	private List<String> avg;//获取平均值[属性名，...]
	private List<String> sum;//获取合计[属性名，...]
	private List<String> count;//获取个数[属性名，...]
	private List<String> countD;//获取取重后的个数[属性名，...]
	private List<String> group;//分组统计[属性名，...]
	
	/**
	 * <p>获得 ge</p>
	 * @return Map<String,Object> ge
	 *
	 */
	public Map<String, Object> getGe() {
		return ge;
	}
	/**
	 * <p>设置 ge</p>
	 * @param Map<String,Object> ge
	 */
	public void setGe(Map<String, Object> ge) {
		this.ge = ge;
	}
	/**
	 * <p>获得 le</p>
	 * @return Map<String,Object> le
	 *
	 */
	public Map<String, Object> getLe() {
		return le;
	}
	/**
	 * <p>设置 le</p>
	 * @param Map<String,Object> le
	 */
	public void setLe(Map<String, Object> le) {
		this.le = le;
	}
	/**
	 * <p>获得 ilike</p>
	 * @return Map<String,Object> ilike
	 *
	 */
	public Map<String, Object> getIlike() {
		return ilike;
	}
	/**
	 * <p>设置 ilike</p>
	 * @param Map<String,Object> ilike
	 */
	public void setIlike(Map<String, Object> ilike) {
		this.ilike = ilike;
	}
	/**
	 * <p>获得 isnull</p>
	 * @return List<?> isnull
	 *
	 */
	public List<String> getIsnull() {
		return isnull;
	}
	/**
	 * <p>设置 isnull</p>
	 * @param List<String> isnull
	 */
	public void setIsnull(List<String> isnull) {
		this.isnull = isnull;
	}
	/**
	 * <p>获得 notNull</p>
	 * @return List<String> notNull
	 *
	 */
	public List<String> getNotNull() {
		return notNull;
	}
	/**
	 * <p>设置 notNull</p>
	 * @param List<?> notNull
	 */
	public void setNotNull(List<String> notNull) {
		this.notNull = notNull;
	}
	/**
	 * <p>获得 notIn</p>
	 * @return Map<String,Object[]> notIn
	 *
	 */
	public Map<String, Object[]> getNotIn() {
		return notIn;
	}
	/**
	 * <p>设置 notIn</p>
	 * @param Map<String,Object[]> notIn
	 */
	public void setNotIn(Map<String, Object[]> notIn) {
		this.notIn = notIn;
	}
	/**
	 * <p>获得 max</p>
	 * @return List<String> max
	 *
	 * @hibernate.property not-null = "true"
	 * column = ""
	 */
	public List<String> getMax() {
		return max;
	}
	/**
	 * <p>设置 max</p>
	 * @param List<String> max
	 */
	public void setMax(List<String> max) {
		this.max = max;
	}
	/**
	 * <p>获得 min</p>
	 * @return List<String> min
	 *
	 * @hibernate.property not-null = "true"
	 * column = ""
	 */
	public List<String> getMin() {
		return min;
	}
	/**
	 * <p>设置 min</p>
	 * @param List<String> min
	 */
	public void setMin(List<String> min) {
		this.min = min;
	}
	/**
	 * <p>获得 avg</p>
	 * @return List<String> avg
	 *
	 * @hibernate.property not-null = "true"
	 * column = ""
	 */
	public List<String> getAvg() {
		return avg;
	}
	/**
	 * <p>设置 avg</p>
	 * @param List<String> avg
	 */
	public void setAvg(List<String> avg) {
		this.avg = avg;
	}
	/**
	 * <p>获得 sum</p>
	 * @return List<String> sum
	 *
	 * @hibernate.property not-null = "true"
	 * column = ""
	 */
	public List<String> getSum() {
		return sum;
	}
	/**
	 * <p>设置 sum</p>
	 * @param List<String> sum
	 */
	public void setSum(List<String> sum) {
		this.sum = sum;
	}
	/**
	 * <p>获得 count</p>
	 * @return List<String> count
	 *
	 * @hibernate.property not-null = "true"
	 * column = ""
	 */
	public List<String> getCount() {
		return count;
	}
	/**
	 * <p>设置 count</p>
	 * @param List<String> count
	 */
	public void setCount(List<String> count) {
		this.count = count;
	}
	/**
	 * <p>获得 countD</p>
	 * @return List<String> countD
	 *
	 * @hibernate.property not-null = "true"
	 * column = ""
	 */
	public List<String> getCountD() {
		return countD;
	}
	/**
	 * <p>设置 countD</p>
	 * @param List<String> countD
	 */
	public void setCountD(List<String> countD) {
		this.countD = countD;
	}
	/**
	 * <p>获得 group</p>
	 * @return List<String> group
	 *
	 * @hibernate.property not-null = "true"
	 * column = ""
	 */
	public List<String> getGroup() {
		return group;
	}
	/**
	 * <p>设置 group</p>
	 * @param List<String> group
	 */
	public void setGroup(List<String> group) {
		this.group = group;
	}
	
	/**
	 * 
	 *
	 * <p>Description:构造与查询条件 </p>
	 *
	 * Created by [dy] [2014-5-14]
	 * Midified by [修改人] [修改时间]
	 *
	 * @param criteria
	 * @return
	 */
	@Override
	public Criteria andCriteria(Criteria criteria){
		List<Criterion> cList = getCriterions();
		
		//与查询条件组装
		if(null != cList && cList.size()>0){
			for (int i = 0; i < cList.size(); i++) {
				criteria.add(cList.get(i));
			}
		}
		
		//order
		if(null != order){
			for (Map.Entry<String, String> ord : order.entrySet()) {
				String sort = ord.getKey();
				String field = ord.getValue();
				if ("desc".equalsIgnoreCase(sort))
					criteria.addOrder(Order.desc(field));
				else
					criteria.addOrder(Order.asc(field));
			}
		}
		return criteria;
	}
	
	/**
	 * 
	 *
	 * <p>Description:构造或查询条件 </p>
	 *
	 * Created by [dy] [2014-5-14]
	 * Midified by [修改人] [修改时间]
	 *
	 * @param criteria
	 * @return
	 */
	@Override
	public Criteria orCriteria(Criteria criteria){
		List<Criterion> cList = getCriterions();
		
		//或查询条件组装
		if(null != cList && cList.size()>0){
			if(cList.size() <2){
				criteria.add(Restrictions.or(cList.get(0)));
			}else{
				LogicalExpression orExp = Restrictions.or(cList.get(0),cList.get(1));
				for (int i = 2; i < cList.size(); i++) {
					orExp = Restrictions.or(orExp, cList.get(i));
				}
				criteria.add(orExp);
			}
		}
		
		//order
		if(null != order){
			for (Map.Entry<String, String> ord : order.entrySet()) {
				String sort = ord.getKey();
				String field = ord.getValue();
				if ("desc".equalsIgnoreCase(sort))
					criteria.addOrder(Order.desc(field));
				else
					criteria.addOrder(Order.asc(field));
			}
		}
		return criteria;
	}
	
	/**
	 * 
	 *
	 * <p>Description:构造投影、统计条件 </p>
	 *
	 * Created by [dy] [2014-5-15]
	 * Midified by [修改人] [修改时间]
	 *
	 * @return
	 */
	public ProjectionList projectionList() {
		ProjectionList proList = Projections.projectionList();
		// max
		if (null != max && max.size() >0) {
			for (int i = 0; i < max.size(); i++) {
				proList.add(Projections.max(max.get(i)));
			}
		}
		// min
		if (null != min && min.size() > 0) {
			for (int i = 0; i < min.size(); i++) {
				proList.add(Projections.min(min.get(i)));
			}
		}
		// avg
		if (null != avg && avg.size() > 0) {
			for (int i = 0; i < avg.size(); i++) {
				proList.add(Projections.avg(avg.get(i)));
			}
		}
		// sum
		if (null != sum && sum.size() > 0) {
			for (int i = 0; i < sum.size(); i++) {
				proList.add(Projections.sum(sum.get(i)));
			}
		}
		// count
		if (null != count && count.size() > 0) {
			for (int i = 0; i < count.size(); i++) {
				proList.add(Projections.count(count.get(i)));
			}
		}
		// countDistinct
		if (null != countD && countD.size() > 0) {
			for (int i = 0; i < countD.size(); i++) {
				proList.add(Projections.countDistinct(countD.get(i)));
			}
		}
		// group by
		if (null != group && group.size() > 0) {
			for (int i = 0; i < group.size(); i++) {
				proList.add(Projections.groupProperty(group.get(i)));
			}
		}
		return proList;
	}
	/**
	 * 
	 *
	 * <p>Description:查询条件 </p>
	 *
	 * Created by [dy] [2014-5-15]
	 * Midified by [修改人] [修改时间]
	 *
	 * @return List<Criterion>
	 */
	private List<Criterion> getCriterions() {
		List<Criterion> cList = new ArrayList<Criterion>(); 
		//=
		if(null != eq){
			for (Map.Entry<String, Object> query : eq.entrySet()) {
				String field = query.getKey().toString();
				Object val = query.getValue();
				Criterion c = Restrictions.eq(field, val);
				cList.add(c);
			}
		}
		//>
		if(null != gt){
			for (Map.Entry<String, Object> query : gt.entrySet()) {
				String field = query.getKey().toString();
				Object val = query.getValue();
				Criterion c = Restrictions.gt(field, val);
				cList.add(c);
			}
		}
		//>=
		if(null != ge){
			for (Map.Entry<String, Object> query : ge.entrySet()) {
				String field = query.getKey().toString();
				Object val = query.getValue();
				Criterion c = Restrictions.ge(field, val);
				cList.add(c);
			}
		}
		//>
		if(null != lt){
			for (Map.Entry<String, Object> query : lt.entrySet()) {
				String field = query.getKey().toString();
				Object val = query.getValue();
				Criterion c = Restrictions.lt(field, val);
				cList.add(c);
			}
		}
		//>=
		if(null != le){
			for (Map.Entry<String, Object> query : le.entrySet()) {
				String field = query.getKey().toString();
				Object val = query.getValue();
				Criterion c = Restrictions.le(field, val);
				cList.add(c);
			}
		}
		//like
		if(null != like){
			for (Map.Entry<String, Object> query : like.entrySet()) {
				String field = query.getKey().toString();
				String val = query.getValue().toString();
				Criterion c = Restrictions.like(field, "%" + val + "%");
				cList.add(c);
			}
		}
		//忽略大小写的like
		if(null != ilike){
			for (Map.Entry<String, Object> query : ilike.entrySet()) {
				String field = query.getKey().toString();
				Object val = query.getValue();
				Criterion c = Restrictions.ilike(field,"%"+ val +"%");
				cList.add(c);
			}
		}
		//<>
		if(null != ne){
			for (Map.Entry<String, Object> query : ne.entrySet()) {
				String field = query.getKey().toString();
				Object val = query.getValue();
				Criterion c = Restrictions.ne(field, val);
				cList.add(c);
			}
		}
		// 组装区间查询条件
		if (null != between) {
			for (Map.Entry<String, Object[]> entry : between.entrySet()) {
				String key = entry.getKey().toString();
				Object[] value = entry.getValue();
				Criterion c = Restrictions.between(key, value[0], value[1]);
				cList.add(c);
			}
		}
		
		//is null
		if(null != isnull && isnull.size() >0){
			for (int i = 0; i < isnull.size(); i++) {
				Criterion c = Restrictions.isNull(isnull.get(i));
				cList.add(c);
			}
		}
		//is not null
		if(null != notNull && notNull.size() >0){
			for (int i = 0; i < notNull.size(); i++) {
				Criterion c = Restrictions.isNotNull(notNull.get(i));
				cList.add(c);
			}
		} 
		
		//in 条件
		if(null != in){
			for (Map.Entry<String, Object[]> entry : in.entrySet()) {
				String key = entry.getKey().toString();
				Object[] value = entry.getValue();
				Criterion c = Restrictions.in(key, value);
				cList.add(c);
			}
		}
		
		//not in 条件
		if(null != notIn){
			for (Map.Entry<String, Object[]> entry : notIn.entrySet()) {
				String key = entry.getKey().toString();
				Object[] value = entry.getValue();
				Criterion c = Restrictions.not(Restrictions.in(key, value));
				cList.add(c);
			}
		}
		return cList;
	}
}
