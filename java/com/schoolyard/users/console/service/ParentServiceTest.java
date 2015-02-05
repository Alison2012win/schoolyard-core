package com.w3studio.sflink.users.console.service;

import org.jboss.logging.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.w3studio.sflink.common.AbstractTestCase;
import com.w3studio.sflink.common.page.Page;
import com.w3studio.sflink.po.parent.ParentLoginPO;
import com.w3studio.sflink.po.parent.ParentPO;

/**<p>
 * Title: sflink-console_[用户管理平台]_[家长管理]
 * </p>
 * <p>
 * Description: [家长setvice测试方法类]
 * </p>
 * 
 * @author lqw
 * @version $Revision$ 2014-6-13
 * @author (lastest modification by $Author$)
 * @since 20130601
 */
public class ParentServiceTest extends AbstractTestCase{

protected Logger log = Logger.getLogger(ParentServiceTest.class);
	
	@Autowired
	private ParentService parentService;

	@Test
	public void testAreYouReady() {
		Assert.assertNotNull(parentService);
	}
	
	@Test
	public void testParent(){
		ParentPO parent = new ParentPO();
		//ParentLoginPO login = new ParentLoginPO();
		parent.setName("家长001");
		Page<ParentPO> page = new Page<ParentPO>();
		
		String ids[] = {"1","2","3"};
		//分页查询家长
		page = parentService.findParentByPage(page);
		log.info("%%%%%%%%%%%%%%%%%%新增前家长数量："+page.getResult().size());
		
		
		//家长修改
		parent.setName("家长b1");
		parent = parentService.updateParent(parent);
		log.info("%%%%%%%%%%%%%%%%%%修改后家长名称："+parent.getName());
		Assert.assertEquals("家长b1" + "", parent.getName());
		
		//通过id获取家长信息
		ParentPO e1 = parentService.findParentById(parent.getPid());
		log.info("%%%%%%%%%%%%%%%%%%通过id获取家长名称："+e1.getName());
		Assert.assertEquals(parent.getPid(), e1.getPid());
		
		//删除
		parentService.deleteParent(ids);
		for(int i = 0;i<ids.length;i++){
			ParentLoginPO p = parentService.findParentLoginById(Integer.parseInt(ids[i]));
			log.info("%%%%%%%%%%%%%%%%%%删除后管理员状态："+p.getStatus());
			Assert.assertEquals("管理员状态" + "", p.getStatus());
		}
	}
}
