package com.schoolyard.common.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.schoolyard.common.bo.UserBO;


/**
 * token转化类
 * @author GLJ 2013-12-23
 *
 */
public class TokenTransform {
	public static Map<String, UserBO> tonkenMap = new HashMap<String, UserBO>();
	/**
	 * 反向token，由用户id来确定
	 */
	public static Map<Integer, String> tonkenMapBack = new HashMap<Integer, String>();
	
	/**
	 * 通过token获取用户信息
	 * @param token
	 * @return
	 */
	public static int tokenToUid(String token){
		if(token.equals("x3q8UfddcQJeE/XMPmtAEQ==")){//演示使用
			return 1;
		}
		UserBO user = tonkenMap.get(token);
		int uid = -1;
		if(user!=null){
			uid = user.getUid();
		}
		return uid;
	}
	
	/**
	 * 通过tonken获取用户信息
	 * @param token
	 * @return
	 */
	public static String tokenToUsername(String token){
		UserBO user = tonkenMap.get(token);
		String userName = null;
		if(user!=null){
			userName = user.getUsername();
		}
		return userName;
	}
	
	/**
	 * 创建token
	 * @param userId
	 * @param userName
	 * @return
	 */
	public static String createToken(int userId,String userName){
		String token = tonkenMapBack.get(userId);
		if(token!=null&&token!=""){
			tonkenMap.remove(token);
		}
		Long time = new Date().getTime();
		try {
			token = EncryptUtil.encode(time.toString()).replace("+", "").replace("=", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		UserBO user = new UserBO();
		user.setUid(userId);
		user.setUsername(userName);
		tonkenMap.put(token, user);
		tonkenMapBack.put(userId, token);
		return token;
	}
	
	/**
	 * 登录权限的验证
	 * 
	 * @author Dy
	 * @param token token值
	 * @param uid 用户id
	 * @return
	 */
	public static boolean authorization(String token, int uid) {
		// 登录校验
		if (StringUtils.isBlank(token) || uid != TokenTransform.tokenToUid(token)) {
			return false;
		}
		return true;
	}
}
