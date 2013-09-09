package org.espe.sigec.exception;

/**
 * @author roberto
 *
 */
@SuppressWarnings("serial")
public class UserValidateException extends Exception{

	public UserValidateException() {
		super();
		// TODO Auto-generated constructor stub
	}

//	public UserValidateException(String message, Throwable cause,
//			boolean enableSuppression, boolean writableStackTrace) {
//		super(message, cause, enableSuppression, writableStackTrace);
//		// TODO Auto-generated constructor stub
//	}

	public UserValidateException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public UserValidateException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public UserValidateException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}
