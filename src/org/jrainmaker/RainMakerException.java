package org.jrainmaker;

@SuppressWarnings("serial")
public class RainMakerException extends Exception {
	/**
	 * Constructs a new exception with the specified message.
	 * 
	 * @param message
	 *            the reason for the exception
	 */
	public RainMakerException(String message) {
		super(message);
	}

	/**
	 * Constructs a new exception with the specified message and wrapped
	 * exception.
	 * 
	 * @param message
	 *            the reason for the exception
	 * @param cause
	 *            the internal exception that caused this exception
	 */
	public RainMakerException(String message, Throwable cause) {
		super(message, cause);
	}

}
