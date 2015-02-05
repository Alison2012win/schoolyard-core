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

package com.schoolyard.service.sysuser;

import org.jboss.logging.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.schoolyard.common.AbstractTestCase;
import com.schoolyard.common.page.Page;
import com.schoolyard.service.sysuser.po.SysUserPO;
import com.schoolyard.service.sysuser.po.SysUserRolePO;
import com.schoolyard.service.sysuser.service.SysUserService;

/**<p>
 * Title: sflink-console_[用户管理平台]_[管路员管理]
 * </p>
 * <p>
 * Description: [管理员管理setvice测试方法类]
 * </p>
 * 
 * @author lqw
 * @version $Revision$ 2014-6-12
 * @author (lastest modification by $Author$)
 * @since 20130601
 */
public class SysUserServiceTest extends AbstractTestCase{
	
	protected Logger log = Logger.getLogger(SysUserServiceTest.class);
	
	@Autowired
	private SysUserService managerService;

	@Test
	public void testAreYouReady() {
		Assert.assertNotNull(managerService);
	}
	
	@Test
	public void testSysUser(){
		SysUserPO user = new SysUserPO();
		SysUserRolePO userRole = new SysUserRolePO();
		user.setName("管理员001");
		Page<SysUserPO> page = new Page<SysUserPO>();
		
		String ids[] = {"1","2","3"};
		
		//分页查询管理员
		page = managerService.findSysUserByPage(page);
		int sizeBeforeAdd = page.getResult().size();
		log.info("%%%%%%%%%%%%%%%%%%新增前管理员数量："+page.getResult().size());
		
		//管理员增加
		user = managerService.saveSysUser(user, userRole);
		log.info("%%%%%%%%%%%%%%保存后管理员id："+user.getSysuid());
		log.info("%%%%%%%%%%%%%%保存后管理员-角色 角色id："+userRole.getRid());
		Assert.assertNotNull(user.getSysuid());
		page = managerService.findSysUserByPage(page);
		int sizeAfterAdd = page.getResult().size();
		log.info("%%%%%%%%%%%%%%%%%%新增后管理员数量："+page.getResult().size());
		Assert.assertEquals(1, sizeAfterAdd-sizeBeforeAdd);
		
		//管理员修改
		user.setName("管理员b1");
		user = managerService.saveSysUser(user, userRole);
		log.info("%%%%%%%%%%%%%%%%%%修改后管理员名称："+user.getName());
		Assert.assertEquals("管理员b1" + "", user.getName());
		
		//重置密码
		user.setPassword("111");
		user = managerService.resetSysUser(user);
		log.info("%%%%%%%%%%%%%%%%%%重置后管理员密码："+user.getPassword());
		Assert.assertEquals("管理员密码" + "", user.getPassword());
		
		//通过id获取管理员
		SysUserPO e1 = managerService.findSysUserById(user.getSysuid());
		log.info("%%%%%%%%%%%%%%%%%%通过id获取管理员名称："+e1.getName());
		Assert.assertEquals(user.getSysuid(), e1.getSysuid());
		
		//删除
		managerService.deleteSysUser(ids);
		for(int i = 0;i<ids.length;i++){
			SysUserPO p = managerService.findSysUserById(Integer.parseInt(ids[i]));
			log.info("%%%%%%%%%%%%%%%%%%删除后管理员状态："+p.getStatus());
			Assert.assertEquals("管理员状态" + "", p.getStatus());
		}
		
		
	}

}
