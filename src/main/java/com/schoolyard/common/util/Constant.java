package com.schoolyard.common.util;


/** 
 * 常量字典表
 * @author GLJ
 */
public class Constant {
	
	/**
	 * 状态——激活
	 */
	public static final int STATUS_UNLOCK = 1;
	/**
	 * 状态——锁定
	 */
	public static final int STATUS_LOCK = 2;
	
	/**
	 * 家长登录默认密码
	 */
	public static final String password = "123456";
	/**
	 * 教师登录默认密码
	 */
	public static final String pwd = "123456";
	
	/**
	 * 组织结构属性类型定义——boolean型
	 */
	public static final int ATTR_TYPE_BOOL = 0;
	/**
	 * 组织结构属性类型定义——int型
	 */
	public static final int ATTR_TYPE_INT = 1;
	/**
	 * 组织结构属性类型定义——float型
	 */
	public static final int ATTR_TYPE_FLOAT = 2;
	/**
	 * 组织结构属性类型定义——double型
	 */
	public static final int ATTR_TYPE_DOUBLE = 3;
	/**
	 * 组织结构属性类型定义——char型
	 */
	public static final int ATTR_TYPE_CHAR = 4;
	/**
	 * 组织结构属性类型定义——string型
	 */
	public static final int ATTR_TYPE_STR = 5;
	/**
	 * 组织结构属性类型定义——EnumList型
	 */
	public static final int ATTR_TYPE_ENUM = 6;
	
	
	/**
	 * 组织结构类型定义——教委
	 */
	public static final String TYPE_CODE_EDUCATION = "1000";
	/**
	 * 组织结构类型定义——学校
	 */
	public static final String TYPE_CODE_SCHOOL = "1001";
	/**
	 * 组织结构类型定义——年级
	 */
	public static final String TYPE_CODE_GRADE = "1002";
	/**
	 * 组织结构类型定义——班级
	 */
	public static final String TYPE_CODE_CLASS = "1003";
	/**
	 * 组织结构类型定义——老师组
	 */
	public static final String TYPE_CODE_TEACHER = "1004";
	/**
	 * 组织结构类型定义——其他
	 */
	public static final String TYPE_CODE_OTHERS = "1005";
	
	/**
	 * 家长账户类型:包年
	 */
	public static final int ACCOUNT_TYPE_YEAR = 1;
	/**
	 * 家长账户类型:包学期
	 */
	public static final int ACCOUNT_TYPE_SEMESTER = 2;
	/**
	 * 家长账户类型:包月
	 */
	public static final int ACCOUNT_TYPE_MONTH = 3;
	/**
	 * 家长账户类型:其他
	 */
	public static final int ACCOUNT_TYPE_OTHERS = 4;
	
	/**
	 * 产品类型：特价
	 */
	public static final int GOODS_TYPE_SPECIAL = 1;
	/**
	 * 产品类型：包年
	 */
	public static final int GOODS_TYPE_YEAR = 2;
	/**
	 * 产品类型：包月
	 */
	public static final int GOODS_TYPE_MONTH = 3;
	/**
	 * 产品类型：学期
	 */
	public static final int GOODS_TYPE_SEMESTER = 4;
	/**
	 * 产品类型：其他
	 */
	public static final int GOODS_TYPE_OTHERS = 5;
	
	/**
	 * 角色类型：超级管理员
	 */
	public static final int ROLE_TYPE_SUPER_ADMIN = 1;
	/**
	 * 角色类型：普通管理员
	 */
	public static final int ROLE_TYPE_ADMIN = 0;
	
	/**
	 * 消息来源类型：教师
	 */
	public static final int SOURCE_TYPE_TEACHER = 1;
	/**
	 * 消息来源类型：家长
	 */
	public static final int SOURCE_TYPE_PARENT = 2;
	/**
	 * 消息来源类型：学校
	 */
	public static final int SOURCE_TYPE_SCHOOL = 3;
	/**
	 * 消息来源类型：系统
	 */
	public static final int SOURCE_TYPE_SYS = 4;
	/**
	 * 消息来源类型：教委
	 */
	public static final int SOURCE_TYPE_EDUCATOR = 5;
	
	/**
	 * 接受者类型-教师
	 */
	public static final int ACCEPT_TYPE_TEACHER = 1;
	/**
	 * 接受者类型-家长
	 */
	public static final int ACCEPT_TYPE_PARENT = 2;
	/**
	 * 接受者类型-学校
	 */
	public static final int ACCEPT_TYPE_SCHOOL = 3;
	/**
	 * 接受者类型-系统
	 */
	public static final int ACCEPT_TYPE_SYS = 4;
	
	/**
	 * 消息类型-作业
	 */
	public static final int MSG_TYPE_OPERATION = 1;
	/**
	 * 消息类型-评语
	 */
	public static final int MSG_TYPE_REMARK = 2;
	/**
	 * 消息类型-成绩
	 */
	public static final int MSG_TYPE_SCORE = 3;
	/**
	 * 消息类型-通知
	 */
	public static final int MSG_TYPE_INFORM = 4;
	/**
	 * 消息类型-公告
	 */
	public static final int MSG_TYPE_NOTICE = 5;
	/**
	 * 消息类型-其他
	 */
	public static final int MSG_TYPE_OTHERS = 6;
	/**
	 * 消息类型-教学动态
	 */
	public static final int MSG_TYPE_NEWS = 7;
	/**
	 * 消息类型-系统消息
	 */
	public static final int MSG_TYPE_SYS = 8;
	/**
	 * 消息类型-重要通知
	 */
	public static final int MSG_TYPE_IMPORTANT_INFORM = 9;
	
	/**
	 * 性别：男
	 */
	public static final int SEX_MAN = 1;
	/**
	 * 性别：女
	 */
	public static final int SEX_WOMAN = 2;
	
	/**
	 * 发件箱消息状态：发送失败
	 */
	public static final int SEND_STATUS_FAIL = 0;
	/**
	 * 发件箱消息状态：待发
	 */
	public static final int SEND_STATUS_COMMITTED = 1;
	/**
	 * 发件箱消息状态：已发
	 */
	public static final int SEND_STATUS_PUBLISHED = 2;
	/**
	 * 发件箱消息状态：发送成功
	 */
	public static final int SEND_STATUS_SUCCESS = 3;
	
	/**
	 * 收件箱消息状态：未读
	 */
	public static final int RECEIVED_STATUS_UNREAD = 0;
	/**
	 * 收件箱消息状态：已读
	 */
	public static final int RECEIVED_STATUS_READ = 1;
	/**
	 * 收件箱消息状态：收藏
	 */
	public static final int RECEIVED_STATUS_COLLECT = 2;
	/**
	 * 收件箱消息状态：删除
	 */
	public static final int RECEIVED_STATUS_DELETE = 3;
	
	/**
	 * 家长账户状态：正常
	 */
	public static final int ACCOUNT_STATUS_NORMAL = 1;
	/**
	 * 家长账户状态：过期
	 */
	public static final int ACCOUNT_STATUS_EXPIRE = 2;
	
	/**
	 * 产品状态：正常
	 */
	public static final int GOODS_STATUS_NORMAL = 1;
	/**
	 * 产品状态：下架
	 */
	public static final int GOODS_STATUS_DROPS = 2;
	/**
	 * 购买状态：已购买
	 */
	public static final int SERVICE_STATUS_PURCHASED = 1;
	/**
	 * 购买状态：可购买
	 */
	public static final int SERVICE_STATUS_PURCHASE = 2;
	
	/**
	 * 系统日志事件类型：账户缴费
	 */
	public static final int EVENT_CODE_LOG = 1;
	
	/**
	 * 公告/评语/成绩/通知/作业详情，发送状态：发送失败
	 */
	public static final int SEND_FAILED = 0;
	/**
	 * 公告/评语/成绩/通知/作业详情，发送状态：待发
	 */
	public static final int WAITING = 1;
	/**
	 * 公告/评语/成绩/通知/作业详情，发送状态：已发
	 */
	public static final int SEND_PUBLISHED = 2;
	/**
	 * 公告/评语/成绩/通知/作业详情，发送状态：发送成功
	 */
	public static final int SEND_SUCCESS = 3;
	
	/**
	 * 班级动态评论，评论者类型：教师
	 */
	public static final int REVIEWER_TYPE_TEACHER = 1;
	
	/**
	 * 班级动态评论，评论者类型：家长
	 */
	public static final int REVIEWER_TYPE_PARENT = 2;
	
	/**
	 * 班级动态状态：正常
	 */
	public static final int NEWS_NORMAL = 1;
	/**
	 * 班级动态状态：删除
	 */
	public static final int NEWS_DELETE = 2;
	
	/**
	 * 班级动态状态：正常
	 */
	public static final int NEWS_REVIEW_NORMAL = 1;
	/**
	 * 班级动态评论状态：删除
	 */
	public static final int NEWS_REVIEW_DELETE = 2;
	
	/**
	 * 成长经历状态：正常
	 */
	public static final int GROWTH_NORMAL = 1;
	/**
	 * 成长经历状态：删除
	 */
	public static final int GROWTH_DELETE = 2;
	
	/**
	 * 云推送注册设备类型：android
	 */
	public static final int DEVICETYPE_ANDROID = 3;
	/**
	 * 云推送注册设备类型：ios
	 */
	public static final int DEVICETYPE_IOS = 4;
	
	/**
	 * 云推送状态：打开
	 */
	public static final int CHANNEL_ON = 1;
	/**
	 * 云推送状态：关闭
	 */
	public static final int CHANNEL_OFF = 0;
	/**
	 * 配置推送表是否使用短信发送：不使用
	 */
	public static final int MSG_SEND_OFF = 0;
	/**
	 * 配置推送表是否使用短信发送：使用
	 */
	public static final int MSG_SEND_ON = 1;
	
	/**
	 * 导出权限：可导出
	 */
	public static final int EXPORT_YES = 1;
	/**
	 * 导出权限：不可进行导出
	 */
	public static final int EXPORT_NO= 0;
	
	/**
	 * 重要通知状态：草稿
	 */
	public static final int IMPORTANT_NOTICE_DRAFT = 0;
	/**
	 * 重要通知状态：待发
	 */
	public static final int IMPORTANT_NOTICE_WAITTING = 1;
	/**
	 * 重要通知状态：已发
	 */
	public static final int IMPORTANT_NOTICE_SENDED = 2;
	
	/**
	 * 新建重要通知是否使用尊称：使用
	 */
	public static final int USETITLE_YES = 1;
	/**
	 * 新建重要通知是否使用尊称：不使用
	 */
	public static final int USETITLE_NO = 0;
	
	/**
	 * 新建重要通知是否使用署名：使用
	 */
	public static final int USESIGN_YES = 1;
	/**
	 * 新建重要通知是否使用署名：不使用
	 */
	public static final int USESIGN_NO = 0;
	
	/**
	 * 新建重要通知是否使用短信发送：使用
	 */
	public static final int SENDSMS_YES = 1;
	/**
	 * 新建重要通知是否使用短信发送：不使用
	 */
	public static final int SENDSMS_NO = 0;
	
}