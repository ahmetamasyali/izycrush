package com.izycrush.rest;

import com.izycrush.enums.Status;



public class BaseResponse<T> {

	private T data;
	
	private Status status;
	
	private String message;


	public BaseResponse(Status status, String message, T data) {
		this.data = data;
		this.message = message;
		this.status = status;
	}

	public static BaseResponse success(Object data) {
		BaseResponse response = new BaseResponse(Status.SUCCESS, "Success", data);
		return response;
	}

	public static BaseResponse error(String message) {
		return new BaseResponse(Status.ERROR,message,null);
	}


	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
