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
import org.hibernate.criterion.Restrictions;

/**
 * 
 * <p>Title:查询工具类 </p>
 * <p>Description: 查询参数（简易）</p>
 * 
 * @version 1.0 
 * Created by [dy] [2014-5-14]
 * Midified by [修改人] [修改时间]
 * 
 */
public class QueryBaseParam {

	protected Map<String, Object> eq;//=查询条件 <属性名,值>
	protected Map<String, Object> gt;//>查询条件 <属性名,值>
	protected Map<String, Object> lt;//<查询条件 <属性名,值>
	protected Map<String, Object> like;//like查询条件 <属性名,值>
	protected Map<String, Object[]> between;//between查询条件 <属性名,[小值，大值]>
	protected Map<String, Object[]> in;//in查询条件  <属性名,[in值，....]>
	protected Map<String, String> order;//排序条件  <排序方式,属性名>
	protected Map<String, Object> ne;//<>查询条件 <属性名,值>
	
	/**
	 * <p>获得 eq</p>
	 * @return Map<String,Object> eq
	 *
	 */
	public Map<String, Object> getEq() {
		return eq;
	}
	/**
	 * <p>设置 eq</p>
	 * @param Map<String,Object> eq
	 */
	public void setEq(Map<String, Object> eq) {
		this.eq = eq;
	}
	/**
	 * <p>获得 gt</p>
	 * @return Map<String,Object> gt
	 *
	 */
	public Map<String, Object> getGt() {
		return gt;
	}
	/**
	 * <p>设置 gt</p>
	 * @param Map<String,Object> gt
	 */
	public void setGt(Map<String, Object> gt) {
		this.gt = gt;
	}
	
	/**
	 * <p>获得 lt</p>
	 * @return Map<String,Object> lt
	 *
	 */
	public Map<String, Object> getLt() {
		return lt;
	}
	/**
	 * <p>设置 lt</p>
	 * @param Map<String,Object> lt
	 */
	public void setLt(Map<String, Object> lt) {
		this.lt = lt;
	}
	
	/**
	 * <p>获得 like</p>
	 * @return Map<String,Object> like
	 *
	 */
	public Map<String, Object> getLike() {
		return like;
	}
	/**
	 * <p>设置 like</p>
	 * @param Map<String,Object> like
	 */
	public void setLike(Map<String, Object> like) {
		this.like = like;
	}
	/**
	 * <p>获得 between</p>
	 * @return Map<String,Object[]> between
	 *
	 */
	public Map<String, Object[]> getBetween() {
		return between;
	}
	/**
	 * <p>设置 between</p>
	 * @param Map<String,Object[]> between
	 */
	public void setBetween(Map<String, Object[]> between) {
		this.between = between;
	}
	/**
	 * <p>获得 in</p>
	 * @return Map<String,Object[]> in
	 *
	 */
	public Map<String, Object[]> getIn() {
		return in;
	}
	/**
	 * <p>设置 in</p>
	 * @param Map<String,Object[]> in
	 */
	public void setIn(Map<String, Object[]> in) {
		this.in = in;
	}
	
	/**
	 * <p>获得 order</p>
	 * @return Map<String,Object> order
	 *
	 */
	public Map<String, String> getOrder() {
		return order;
	}
	/**
	 * <p>设置 order</p>
	 * @param Map<String,Object> order
	 */
	public void setOrder(Map<String, String> order) {
		this.order = order;
	}
	/**
	 * <p>获得 ne</p>
	 * @return Map<String,Object> ne
	 *
	 */
	public Map<String, Object> getNe() {
		return ne;
	}
	/**
	 * <p>设置 ne</p>
	 * @param Map<String,Object> ne
	 */
	public void setNe(Map<String, Object> ne) {
		this.ne = ne;
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
		//>
		if(null != lt){
			for (Map.Entry<String, Object> query : lt.entrySet()) {
				String field = query.getKey().toString();
				Object val = query.getValue();
				Criterion c = Restrictions.lt(field, val);
				cList.add(c);
			}
		}
		//like
		if(null != like){
			for (Map.Entry<String, Object> query : like.entrySet()) {
				String field = query.getKey().toString();
				String val = query.getValue().toString();
				Criterion c = Restrictions.like(field, "%"+ val +"%");
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
		//in 条件
		if(null != in){
			for (Map.Entry<String, Object[]> entry : in.entrySet()) {
				String key = entry.getKey().toString();
				Object[] value = entry.getValue();
				Criterion c = Restrictions.in(key, value);
				cList.add(c);
			}
		}
		// <>
		if (null != ne) {
			for (Map.Entry<String, Object> query : ne.entrySet()) {
				String field = query.getKey().toString();
				Object val = query.getValue();
				Criterion c = Restrictions.ne(field, val);
				cList.add(c);
			}
		}
		return cList;
	}
}
