package com.izycrush.rest;


public class IzycrushException extends Exception
{
	private final String errorCode;

	public IzycrushException(String errorCode) {
		super();
		this.errorCode = errorCode;
	}

	public IzycrushException(Throwable cause, String errorCode) {
		super(cause);
		this.errorCode = errorCode;
	}

	public IzycrushException(String message, String errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return this.errorCode;
	}

}
