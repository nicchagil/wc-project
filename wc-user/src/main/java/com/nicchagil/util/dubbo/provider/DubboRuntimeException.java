package com.nicchagil.util.dubbo.provider;

public class DubboRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DubboRuntimeException() {
	}

	public DubboRuntimeException(String message) {
		super(message);
	}

	public DubboRuntimeException(Throwable cause) {
		super(cause);
	}

	public DubboRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public DubboRuntimeException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
