package com.schoolyard.service.sysuser.service;

import com.schoolyard.common.page.Page;
import com.schoolyard.service.sysuser.po.SysUserPO;
import com.schoolyard.service.sysuser.po.SysUserRolePO;

/**
 * <p>
 * Title: schoolyard_[用户管理平台]_[管理员管理]
 * </p>
 * <p>
 * Description: [管理员service接口]
 * </p>
 * 
 * @author XXX
 * @version $Revision$ 2014-6-12
 * @author (lastest modification by $Author$)
 * @since 20130601
 */
public interface SysUserService {
	
	/**
	 * 管理员增加/修改
	 * @param po
	 * @return
	 */
	public SysUserPO saveSysUser(SysUserPO user,SysUserRolePO userRole);
	
	/**
	 * 管理员进行角色配置
	 * @param po
	 * @return
	 */
	public SysUserRolePO saveSysUserRole(SysUserRolePO po);
	
	/**
	 * 通过管理员id查找到角色id
	 * @param po
	 * @return
	 */
	public SysUserRolePO findSysUserRoleByUid(Integer sysuid);
	
	/**
	 * 管理员根据主键id进行详情查询
	 * @param id
	 * @return
	 */
	public SysUserPO findSysUserById(Integer id);
	
	/**
	 * 分页查询管理员
	 * @param page
	 * @return
	 */
	public Page<SysUserPO> findSysUserByPage(Page<SysUserPO> page);
	
	/**
	 * 管理员锁定（即修改status状态）
	 * @param po
	 * @return
	 */
	public void deleteSysUser(String[] ids);
	/**
	 * 管理员激活（即修改status状态）
	 * @param po
	 * @return
	 */
	public void activateSysUser(String[] ids);
	
	/**
	 * 管理员重置密码
	 * @param po
	 * @return
	 */
	public SysUserPO resetSysUser(SysUserPO po);
	
	/**
	 * 根据login获取信息
	 * @param param
	 * @return UserPO
	 */
	public SysUserPO findUserByLogin(String login);
	
	/**
	 * 重名校验
	 * @param param
	 * @return 
	 */
	public boolean duplicatecheck(Object sysuid, String property, String value);
	
	/**
	 * 根据用戶名获取用户信息
	 * @param name
	 * @return SysUserPO
	 */
	public SysUserPO getUserByName(String name);
}
