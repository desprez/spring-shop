package fr.training.samples.spring.shop.domain.common.exception;

public class AlreadyExistingException extends BusinessException {

	private static final long serialVersionUID = 1L;

	public AlreadyExistingException() {
		super();
	}

	public AlreadyExistingException(final String message) {
		super(message);
	}

	public AlreadyExistingException(final Throwable cause) {
		super(cause);
	}

	public AlreadyExistingException(final String message, final Throwable cause) {
		super(message, cause);
	}

}