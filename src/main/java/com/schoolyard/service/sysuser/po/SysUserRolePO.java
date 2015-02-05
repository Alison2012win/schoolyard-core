package com.schoolyard.service.sysuser.po;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p>
 * Title: schoolyard_[用户管理平台]_[管理员]
 * </p>
 * <p>
 * Description: [管理员角色实体PO]
 * </p>
 * 
 * @author XXX
 * @version $Revision$ 2014-6-11
 * @author (lastest modification by $Author$)
 * @since 20130601
 */
@Entity
@Table(name = "TB_SF_SYS_USER_ROLE")
public class SysUserRolePO {

	// Fields

	private Integer id;// 组件

	private Integer sysuid;// 系统管理员id

	private Integer rid;// 角色id

	// Constructors

	/** default constructor */
	public SysUserRolePO() {
	}

	/** full constructor */
	public SysUserRolePO(Integer sysuid, Integer rid) {
		this.sysuid = sysuid;
		this.rid = rid;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "SYS_U_ID", nullable = false)
	public Integer getSysuid() {
		return sysuid;
	}

	public void setSysuid(Integer sysuid) {
		this.sysuid = sysuid;
	}

	@Column(name = "R_ID", nullable = false)
	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}
}
