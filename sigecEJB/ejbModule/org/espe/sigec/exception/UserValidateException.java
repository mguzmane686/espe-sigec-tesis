package org.espe.sigec.exception;

/**
 * @author roberto
 *
 */
@SuppressWarnings("serial")
public class UserValidateException extends RuntimeException{

	public UserValidateException() {
		super();
	}

	

	public UserValidateException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserValidateException(String message) {
		super(message);
	}

	public UserValidateException(Throwable cause) {
		super(cause);
	}
}
