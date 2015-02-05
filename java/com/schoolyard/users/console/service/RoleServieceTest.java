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

import org.jboss.logging.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.w3studio.sflink.common.AbstractTestCase;
import com.w3studio.sflink.common.page.Page;
import com.w3studio.sflink.po.sysuser.RolePO;
import com.w3studio.sflink.users.console.web.utils.Constant;

/**<p>
 * Title: sflink-console_[用户管理平台]_[角色管理]
 * </p>
 * <p>
 * Description: [角色管理Serveice测试方法类]
 * </p>
 * 
 * @author lqw
 * @version $Revision$ 2014-6-12
 * @author (lastest modification by $Author$)
 * @since 20130601
 */
public class RoleServieceTest extends AbstractTestCase{

protected Logger log = Logger.getLogger(RoleServieceTest.class);
	
	@Autowired
	private RoleService roleService;

	@Test
	public void testAreYouReady() {
		Assert.assertNotNull(roleService);
	}
	
	@Test
	public void testRole(){
		RolePO role = new RolePO();
		role.setName("角色001");
		role.setType(Constant.STATUS_UNLOCK);
		role.setRid(12);
		Page<RolePO> page = new Page<RolePO>();
		String ids[] = {"1","2","3"};
		
		//分页查询角色
		page = roleService.findRoleByPage(page);
		int sizeBeforeAdd = page.getResult().size();
		log.info("%%%%%%%%%%%%%%%%%%新增前角色数量："+page.getResult().size());
		
		//角色增加
//		RolePO role1 = roleService.saveRole(role);
//		log.info("%%%%%%%%%%%%%%保存后角色id："+role1.getRid());
//		Assert.assertNotNull(role1.getRid());
//		page = roleService.findRoleByPage(page);
//		int sizeAfterAdd = page.getResult().size();
//		log.info("%%%%%%%%%%%%%%%%%%新增后角色数量："+page.getResult().size());
//		Assert.assertEquals(1, sizeAfterAdd-sizeBeforeAdd);
		
		//角色修改
		role.setName("角色b1");
		role = roleService.saveRole(role);
		log.info("%%%%%%%%%%%%%%%%%%修改后角色名称："+role.getName());
		Assert.assertEquals("角色b1" + "", role.getName());
//		
//		//通过id获取角色信息
//		RolePO e1 = roleService.findRoleById(role.getRid());
//		log.info("%%%%%%%%%%%%%%%%%%通过id获取角色名称："+e1.getName());
//		Assert.assertEquals(role.getRid(), e1.getRid());
//		//删除
//		roleService.deleteRole(ids);
//		page = roleService.findRoleByPage(page);
//		int sizeAfterDelete = page.getResult().size();
//		log.info("%%%%%%%%%%%%%%%%%%删除后角色数量："+page.getResult().size());
//		Assert.assertEquals(sizeBeforeAdd,sizeAfterDelete);
	}
}
