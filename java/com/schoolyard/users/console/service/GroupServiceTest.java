/** 
* 
 * Copyright (c) 1995-2012 Wonders Information Co.,Ltd. 
 * 1518 Lianhang Rd,Shanghai 201112.P.R.C.
 * All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wonders Group.
 * (Social Security Department). You shall not disclose such
 * Confidential Information and shall use it only in accordance with 
 * the terms of the license agreement you entered into with Wonders Group. 
 *
 * Distributable under GNU LGPL license by gnu.org
 */

package com.w3studio.sflink.users.console.service;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jboss.logging.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.w3studio.sflink.common.AbstractTestCase;
import com.w3studio.sflink.po.group.GroupAttrDicPO;
import com.w3studio.sflink.po.group.GroupAttrValuePO;
import com.w3studio.sflink.po.group.GroupPO;

/**<p>
 * Title: sflink-console_[子系统统名]_[模块名]
 * </p>
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author dy
 * @version $Revision$ 2014-6-19
 * @author (lastest modification by $Author$)
 * @since 20130601
 */
public class GroupServiceTest extends AbstractTestCase {
	protected Logger log = Logger.getLogger(GroupServiceTest.class);
	
	@Autowired
	private GroupService groupService;
	
	@Test
	public void testAreYouReady() {
		Assert.assertNotNull(groupService);
	}
	
	@Test
	public void testGroup (){
		GroupPO group = new GroupPO();
		group.setName("上海市第一中学");
		group.setParentGid(1);
		group.setTypeCode("1002");
		group.setTypeName("学校");
		group.setCode("60011");
		List<GroupAttrValuePO> attrs = new ArrayList<GroupAttrValuePO>();
		GroupAttrValuePO attr = new GroupAttrValuePO();
		attr.setAttrId(3);
		attr.setValue("上海市浦东区");
		attrs.add(attr);
		attr = new GroupAttrValuePO();
		attr.setAttrId(4);
		attr.setValue("020-89796754");
		attrs.add(attr);
		attr = new GroupAttrValuePO();
		attr.setAttrId(5);
		attr.setValue("张主任");
		attrs.add(attr);
		
		//新增组织结构
		groupService.saveGroup(group, attrs);
		Assert.assertNotNull(group.getGid());
		
		//根据节点类型获取节点属性字典表
		List<GroupAttrDicPO> attrDics = groupService.getGroupAttrDic("1002");
		Assert.assertEquals("学校属性字典有2条", 3, attrDics.size());
		
		//根据节点id及属性id获取属性值
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("gid", group.getGid());
		map.put("attrId", 3);
		GroupAttrValuePO attrVealue =  groupService.getGroupAttrValue(map);
		Assert.assertEquals("根据节点id及属性id获取属性值","上海市浦东区", attrVealue.getValue());
		
		//查询组织结构
		List<GroupPO> list =  groupService.getGroupByMap(null);
		Assert.assertEquals("查询所有组织结构",2, list.size());
		
		//根据组织结构id获取组织信息
		GroupPO po = groupService.getGroupById(group.getGid());
		Assert.assertEquals("根据组织结构id获取组织信息",po.getName(), group.getName());
		
		//修改组织结构信息
		GroupPO group1 = new GroupPO();
		group1.setName("上海市第二中学");
		group1.setParentGid(1);
		group1.setTypeCode("1002");
		group1.setTypeName("学校");
		group1.setCode("60011");
		group1.setGid(group.getGid());
		List<GroupAttrValuePO> attrs1 = new ArrayList<GroupAttrValuePO>();
		GroupAttrValuePO attr1 = new GroupAttrValuePO();
		attr1.setAttrId(3);
		attr1.setGid(group.getGid());
		attr1.setValue("上海市浦东区长江路12号");
		attrs1.add(attr1);
		attr1 = new GroupAttrValuePO();
		attr1.setAttrId(4);
		attr1.setGid(group.getGid());
		attr1.setValue("020-8008820");
		attrs1.add(attr1);;
		groupService.updateGroup(group1, attrs1);
		GroupPO po1 = groupService.getGroupById(group.getGid());
		Assert.assertEquals("根据组织结构id获取组织信息",po1.getName(), group1.getName());
		
		//组织结构删除
		groupService.deleteGroup(group.getGid());
		po = groupService.getGroupById(group.getGid());
		Assert.assertNull(po);
		
		//根据组织结构id删除学生组织关系
		groupService.deleteStudentGroup(group.getGid());
		Assert.assertNull(po);
		
		//根据组织结果id删除老师组织关系
		groupService.deleteTeacherGroup(group.getGid());
		Assert.assertNull(po);
	}
}
