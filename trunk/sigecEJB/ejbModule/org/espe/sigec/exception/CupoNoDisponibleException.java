package org.espe.sigec.exception;

@SuppressWarnings("serial")
public class CupoNoDisponibleException extends Exception{

	public CupoNoDisponibleException() {
		super();
	}

	public CupoNoDisponibleException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CupoNoDisponibleException(String message, Throwable cause) {
		super(message, cause);
	}

	public CupoNoDisponibleException(String message) {
		super(message);
	}

	public CupoNoDisponibleException(Throwable cause) {
		super(cause);
	}
	
}
