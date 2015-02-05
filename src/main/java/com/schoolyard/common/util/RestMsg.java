package com.schoolyard.common.util;



public class RestMsg<T> {
	public static final int RECODE_OK = 1;
	public static final int RECODE_FAIL = -1;
	public static final int NO_LOGIN = -2;

	public static final String SUCCESS = "success";
	public static final String ERROR = "error";
	public static final String LOGIN = "您还没有登录，请登录！";

	private int code;// <成功/失败/错误等状态码>
	private String msg;// <失败/错误时的额外信息>
	private T result;// 输出
	
	public RestMsg(){
		super();
	}

	public RestMsg(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public RestMsg<T> successMsg() {
		this.code = RECODE_OK;
		this.msg = SUCCESS;
		return this;
	}

	public RestMsg<T> errorMsg() {
		this.code = RECODE_FAIL;
		this.msg = ERROR;
		return this;
	}
	
	public RestMsg<T> errorMsg(String errorMsg) {
		this.code = RECODE_FAIL;
		this.msg = errorMsg;
		return this;
	}
	
	public RestMsg<T> loginMsg() {
		this.code = NO_LOGIN;
		this.msg = LOGIN;
		return this;
	}
	
	@Override
	public String toString() {
		return JsonUtils.getJsonData(this);
	}
}