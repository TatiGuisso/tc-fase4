package com.grupo16.tcfase4.exception;

public abstract class SystemBaseException extends RuntimeException {
	private static final long serialVersionUID = 869484695654256005L;
	
	public abstract String getCode();
	public abstract Integer getHttpStatus();
	@Override
	public abstract String getMessage();

}
