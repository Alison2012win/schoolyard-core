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
import com.w3studio.sflink.po.parent.ParentPO;
import com.w3studio.sflink.po.student.StudentGroupPO;
import com.w3studio.sflink.po.student.StudentPO;

/**
 * <p>
 * Title: sflink-console_[用户管理平台]_[学生管理]
 * </p>
 * <p>
 * Description: [学生setvice测试方法类]
 * </p>
 * 
 * @author lqw
 * @version $Revision$ 2014-6-13
 * @author (lastest modification by $Author$)
 * @since 20130601
 */
public class StudentServiceTest extends AbstractTestCase {

	protected Logger log = Logger.getLogger(StudentServiceTest.class);

	@Autowired
	private StudentService studentService;

	@Test
	public void testAreYouReady() {
		Assert.assertNotNull(studentService);
	}

	@Test
	public void testManager() {
		StudentPO student = new StudentPO();
		ParentPO parent = new ParentPO();
		List<StudentGroupPO> groups = new ArrayList<StudentGroupPO>();
		StudentGroupPO studentGroup = new StudentGroupPO();
		studentGroup.setGid(1);
		groups.add(studentGroup);
		student.setName("学生001");
		student.setStatus(1);
		parent.setPhone("15828556678");
		Page<StudentPO> page = new Page<StudentPO>();
		StudentGroupPO group = new StudentGroupPO();
		group.setGid(123);
		Page<StudentGroupPO> grouppage = new Page<StudentGroupPO>();

		// 分页查询学生
		page = studentService.findStudentByPage(page);
		int sizeBeforeAdd = page.getResult().size();
		log.info("%%%%%%%%%%%%%%%%%%新增前学生数量：" + page.getResult().size());

		// 学生增加
		int hasStudent = studentService.saveStudent(student, parent, groups);
		log.info("%%%%%%%%%%%%%%保存后学生id：" + student.getSid());
		// Assert.studentService(student.getSid());
		page = studentService.findStudentByPage(page);
		int sizeAfterAdd = page.getResult().size();
		log.info("%%%%%%%%%%%%%%%%%%新增后学生数量：" + page.getResult().size());
		Assert.assertEquals(1, sizeAfterAdd - sizeBeforeAdd);

		// 学生修改studentGroup = new StudentGroupPO();
		groups = new ArrayList<StudentGroupPO>();
		studentGroup.setGid(2);
		studentGroup.setPid(student.getPid());
		studentGroup.setSid(student.getSid());
		groups.add(studentGroup);
		student.setName("学生b1");
		student = studentService.updateStudent(student, groups);
		log.info("%%%%%%%%%%%%%%%%%%修改后学生名称：" + student.getName());
		Assert.assertEquals("学生b1" + "", student.getName());

		// 通过id获取学生
		StudentPO e1 = studentService.findStudentById(student.getSid());
		log.info("%%%%%%%%%%%%%%%%%%通过id获取学生名称：" + e1.getName());
		Assert.assertEquals(student.getSid(), e1.getSid());

		// 删除
		String ids[] = { e1.getSid().toString() };
		studentService.deleteStudent(ids);
		for (int i = 0; i < ids.length; i++) {
			StudentPO p = studentService.findStudentById(Integer.parseInt(ids[i]));
			log.info("%%%%%%%%%%%%%%%%%%删除后管理员状态：" + p.getStatus());
			Assert.assertEquals(2, p.getStatus().intValue());
		}

		// 获取组织结构下的学生：列表
		Map<String, Object> map = new HashMap<String, Object>();
		// map.put("meterid", map.getMeterId());
		List<StudentGroupPO> list = studentService.getStudentGroupList(map);
		log.info("%%%%%%%%%%%%%%%%%%通过ID获取计量设备数为：" + list.size());
		Assert.assertNotNull(list.size());

		// Assert.studentService(student.getSid());
		grouppage = studentService.findStudentGroupByPage(grouppage);
		int sizeAfterAdd1 = grouppage.getResult().size();
		log.info("%%%%%%%%%%%%%%%%%%新增后学生数量：" + grouppage.getResult().size());
		Assert.assertNotEquals(0, sizeAfterAdd1);
	}
}
