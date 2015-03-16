package org.espe.sigec.exception;

/**
 * @author roberto
 *
 */
@SuppressWarnings("serial")
public class SigecException extends Exception{

	public SigecException() {
		super();
	}

	public SigecException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public SigecException(String message, Throwable cause) {
		super(message, cause);
	}

	public SigecException(String message) {
		super(message);
	}

	public SigecException(Throwable cause) {
		super(cause);
	}
}
