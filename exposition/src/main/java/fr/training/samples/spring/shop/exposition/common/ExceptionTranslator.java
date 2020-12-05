package fr.training.samples.spring.shop.exposition.common;

import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import fr.training.samples.spring.shop.domain.common.exception.AlreadyExistingException;
import fr.training.samples.spring.shop.domain.common.exception.NotFoundException;

@ControllerAdvice
public class ExceptionTranslator extends ResponseEntityExceptionHandler {

	private static final Logger LOG = LoggerFactory.getLogger(ExceptionTranslator.class);

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {

		final String errors = ex.getBindingResult() //
				.getFieldErrors().stream() //
				.map(x -> x.getField() + ": " + x.getDefaultMessage()) //
				.collect(Collectors.joining(", "));

		final ErrorModel apiError = ErrorModel.builder() //
				.code("") //
				.message(errors) //
				.description("Please check your parameters")//
				.build();

		LOG.info(ex.getMessage());
		return handleExceptionInternal(ex, apiError, headers, HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Object> handleConstraint(final ConstraintViolationException ex) {

		final ErrorModel apiError = ErrorModel.builder() //
				.code("") //
				.message(ex.getLocalizedMessage()) //
				.description("error occurred")//
				.build();

		LOG.info(ex.getMessage());
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = { NotFoundException.class })
	@ResponseBody
	public ResponseEntity<Object> handleNotFoundException(final NotFoundException ex ) {

		final ErrorModel apiError = ErrorModel.builder() //
				.message(ex.getLocalizedMessage()) //
				.description("The given identifier is unknown by the system.")//
				.build();

		LOG.info(ex.getMessage());
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), HttpStatus.NOT_FOUND);
	}


	@ExceptionHandler(value = { AlreadyExistingException.class })
	@ResponseBody
	public ResponseEntity<Object> handleAlreadyExistingException(final AlreadyExistingException ex ) {

		final ErrorModel apiError = ErrorModel.builder() //
				.message(ex.getLocalizedMessage()) //
				.build();

		LOG.info(ex.getMessage());
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), HttpStatus.CONFLICT);
	}

	@ExceptionHandler({ Exception.class })
	@ResponseBody
	public ResponseEntity<Object> handleOthers(final Exception ex, final WebRequest request) {

		final ErrorModel apiError = ErrorModel.builder() //
				.code("err.internal") //
				.message(ex.getLocalizedMessage()) //
				.description("internal error occurred, please contact your administrator")//
				.build();

		LOG.error(ex.getMessage(), ex);

		return new ResponseEntity<Object>(apiError, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}