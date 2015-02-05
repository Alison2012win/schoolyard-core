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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jboss.logging.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.w3studio.sflink.common.AbstractTestCase;
import com.w3studio.sflink.common.page.Page;
import com.w3studio.sflink.po.teacher.TeacherGroupPO;
import com.w3studio.sflink.po.teacher.TeacherLoginPO;
import com.w3studio.sflink.po.teacher.TeacherPO;
import com.w3studio.sflink.users.console.web.utils.Constant;

/**<p>
 * Title: sflink-console_[户管理平台]_[教师管理]
 * </p>
 * <p>
 * Description: [教师setvice测试方法类]
 * </p>
 * 
 * @author HYH
 * @version $Revision$ 2014-6-18
 * @author (lastest modification by $Author$)
 * @since 20130601
 */
public class TeacherServiceTest extends AbstractTestCase{
	protected Logger log = Logger.getLogger(TeacherServiceTest.class);
	
	@Autowired
	private TeacherService teacherService;
	
	@Test
	public void testAreYouReady() {
		Assert.assertNotNull(teacherService);
	}
	
	@Test
	public void testTeacher(){
		TeacherPO teacher = new TeacherPO();
		teacher.setName("老师001");
		teacher.setPhone("12345678900");
		teacher.setEmpId("11");
		Page<TeacherPO> page = new Page<TeacherPO>();
		List<TeacherGroupPO> group = new ArrayList<TeacherGroupPO>();
		Page<TeacherGroupPO> grouppage = new Page<TeacherGroupPO>();
		String ids[] = {"1","2","3"};
		//分页查询教师
		page = teacherService.findTeacherByPage(page);
		int sizeBeforeAdd = page.getResult().size();
		log.info("%%%%%%%%%%%%%%%%%%新增前教师数量："+page.getResult().size());
		
		//教师增加
		teacher = teacherService.saveTeacher(teacher,group);
		log.info("%%%%%%%%%%%%%%保存后教师id："+teacher.getTid());
		page = teacherService.findTeacherByPage(page);
		int sizeAfterAdd = page.getResult().size();
		log.info("%%%%%%%%%%%%%%%%%%新增后教师数量："+page.getResult().size());
		Assert.assertEquals(1, sizeAfterAdd-sizeBeforeAdd);
		
		//教师修改
		teacher.setName("教师222");
		
		teacher = teacherService.updateTeacher(teacher,group,Constant.STATUS_UNLOCK);
		log.info("%%%%%%%%%%%%%%%%%%修改后教师名称："+teacher.getName());
		Assert.assertEquals("教师222" + "", teacher.getName());
		
		//通过id获取教师
		TeacherPO e1 = teacherService.findTeacherById(teacher.getTid());
		log.info("%%%%%%%%%%%%%%%%%%通过id获取教师名称："+e1.getName());
		Assert.assertEquals(teacher.getTid(), e1.getTid());
		
		//删除
		teacherService.deleteTeacher(ids);
		for(int i = 0;i<ids.length;i++){
			TeacherLoginPO p = teacherService.findTeacherLoginById(Integer.parseInt(ids[i]));
			log.info("%%%%%%%%%%%%%%%%%%删除后管理员状态："+p.getStatus());
			Assert.assertEquals("管理员状态" + "", p.getStatus());
		}
	
		//获取组织结构下的教师：列表
		Map<String,Object> map = new HashMap<String,Object>();
		List<TeacherGroupPO> list = teacherService.getTeacherGroupList(map);
		log.info("%%%%%%%%%%%%%%%%%%通过ID获取计量设备数为："+list.size());
		Assert.assertEquals("num", list.size());
		
		//分页组织结构下的教师
		grouppage = teacherService.findTeacherGroupByPage(grouppage);
		int sizeBeforeAdd1 = page.getResult().size();
		log.info("%%%%%%%%%%%%%%%%%%新增前教师数量："+grouppage.getResult().size());
		
		//Assert.teacherService(teacher.getTid());
		grouppage = teacherService.findTeacherGroupByPage(grouppage);
		int sizeAfterAdd1 = grouppage.getResult().size();
		log.info("%%%%%%%%%%%%%%%%%%新增后教师数量："+grouppage.getResult().size());
		Assert.assertEquals(1, sizeAfterAdd1-sizeBeforeAdd1);
		
	}

}
