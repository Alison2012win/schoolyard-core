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

package com.schoolyard.common.util;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**<p>
 * Title: sflink_[子系统统名]_[模块名]
 * </p>
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author Dy
 * @version $Revision$ 2014-7-24
 * @author (lastest modification by $Author$)
 * @since 20130601
 */
public class UserInfo extends User {

	private static final long serialVersionUID = 1L;
	
	private Integer uid; 		//用户id
	private String photo;		//用户头像
	private String name;		//用户名称
	private int expStatus;		//是否有导出功能：0无导出功能，1有导出功能

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}
	
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getExpStatus() {
		return expStatus;
	}

	public void setExpStatus(int expStatus) {
		this.expStatus = expStatus;
	}

	public UserInfo(String username, String password, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, authorities);
	}
}
