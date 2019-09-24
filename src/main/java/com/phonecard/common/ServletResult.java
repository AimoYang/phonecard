package com.phonecard.common;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
//保证序列化json的时候，如果是null的对象，key也会消失
public class ServletResult<T> implements Serializable {

	private int result;
	private String msg;
	private T data;

	private ServletResult(int result) {
		this.result = result;
	}

	private ServletResult(int result, T data) {
		this.result = result;
		this.data = data;
	}

	private ServletResult(int result, String msg, T data) {
		this.result = result;
		this.msg = msg;
		this.data = data;
	}

	private ServletResult(int result, String msg) {
		this.result = result;
		this.msg = msg;
	}

	public ServletResult() {

	}

	@JsonIgnore
	//使之不再json序列化结果当中
	public boolean isSuccess() {
		return this.result == StatusCode.SUCCESS.getCode();
	}

	public int getResult() {
		return result;
	}

	public T getData() {
		return data;
	}

	public String getMsg() {
		return msg;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setData(T data) {
		this.data = data;
	}

	public static <T> ServletResult<T> createBySuccess() {
		return new ServletResult<T>(StatusCode.SUCCESS.getCode());
	}

	public static <T> ServletResult<T> createBySuccessMessage(String msg) {
		return new ServletResult<T>(StatusCode.SUCCESS.getCode(), msg);
	}

	public static <T> ServletResult<T> createBySuccess(T data) {
		return new ServletResult<T>(StatusCode.SUCCESS.getCode(), data);
	}

	public static <T> ServletResult<T> createBySuccess(String msg, T data) {
		return new ServletResult<T>(StatusCode.SUCCESS.getCode(), msg, data);
	}

	public static <T> ServletResult<T> createByError() {
		return new ServletResult<T>(StatusCode.ERROR.getCode(), StatusCode.ERROR.getDesc());
	}

	public static <T> ServletResult<T> createByErrorMessage(String errorMessage) {
		return new ServletResult<T>(StatusCode.ERROR.getCode(), errorMessage);
	}

	public static <T> ServletResult<T> createByErrorCodeMessage(int errorCode, String errorMessage) {
		return new ServletResult<T>(StatusCode.ERROR.getCode(), errorMessage);
	}
}
