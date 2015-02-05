package com.schoolyard.service.sysuser.po;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p>
 * Title: schoolyard_[用户管理平台]_[系统管理员登陆管理]
 * </p>
 * <p>
 * Description: [系统管理员实体PO]
 * </p>
 * 
 * @author XXX
 * @version $Revision$ 2014-6-11
 * @author (lastest modification by $Author$)
 * @since 20130601
 */
@Entity
@Table(name = "TB_SF_SYS_USER")
public class SysUserPO {

	// Fields

	private Integer sysuid;// 主键

	private String login;// 登录名

	private String password;// 密码

	private Integer status;// 状态：1：激活，2：锁定

	private String email;// 邮箱

	private String phone;// 手机

	private String name;// 系统管理员名称

	// Constructors

	/** default constructor */
	public SysUserPO() {
	}

	/** minimal constructor */
	public SysUserPO(String login, String password, Integer status) {
		this.login = login;
		this.password = password;
		this.status = status;
	}

	/** full constructor */
	public SysUserPO(String login, String password, Integer status, String email, String phone, String name) {
		this.login = login;
		this.password = password;
		this.status = status;
		this.email = email;
		this.phone = phone;
		this.name = name;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "SYS_U_ID", unique = true, nullable = false)
	public Integer getSysuid() {
		return sysuid;
	}

	public void setSysuid(Integer sysuid) {
		this.sysuid = sysuid;
	}

	@Column(name = "LOGIN", nullable = false)
	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Column(name = "PASSWORD", nullable = false)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "STATUS", nullable = false)
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "EMAIL")
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "PHONE")
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "NAME")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
