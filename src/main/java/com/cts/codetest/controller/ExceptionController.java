package com.cts.codetest.controller;

import org.apache.maven.shared.utils.logging.MessageBuilder;
import org.codehaus.plexus.util.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cts.codetest.exception.BadRequestException;
import com.cts.codetest.exception.InternalServerException;
import com.cts.codetest.exception.ResourceNotFoundException;
import com.cts.codetest.model.Response;
import com.cts.codetest.service.DBLoggerService;
import com.cts.codetest.util.Constants;

@RestController
public class ExceptionController implements MessageSourceAware{

	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionController.class);
	
	 @Autowired
	 private MessageSource messageSource;
	 
	@Autowired
	@Qualifier(value = "dbLoggerService")
	private DBLoggerService dbLoggerService;

	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseEntity<Response> handleBadRequestException(HttpMessageNotReadableException ex) {
		
		String code = messageSource.getMessage(Constants.ERROR_CODE_INVALID_XML, null , LocaleContextHolder.getLocale());
		String message = messageSource.getMessage(Constants.ERROR_MSG_INVALID_XML, null , LocaleContextHolder.getLocale());
		
		Response response = new Response();
		response.setResponseCode(code);
		response.setResponseMessage(message);

		dbLoggerService.log(Constants.LOG_TYPE_ERROR, "Request validation", Constants.ERROR_CODE_INVALID_XML + ":" + Constants.ERROR_MSG_INVALID_XML, ExceptionUtils.getFullStackTrace(ex));
	
		ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(response,HttpStatus.OK);
		
		LOGGER.debug("responseEntity:{}",responseEntity);
		return responseEntity;
	}
	
	@ExceptionHandler(BadRequestException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseEntity<Response> handleBadRequestException(BadRequestException ex) {
		
		String code = messageSource.getMessage(ex.getErrorCodeKey(), null , LocaleContextHolder.getLocale());
		String message = messageSource.getMessage(ex.getErrorMessageKey(), null , LocaleContextHolder.getLocale());
		
		Response response = new Response();
		response.setResponseCode(code);
		response.setResponseMessage(message);

		dbLoggerService.log(Constants.LOG_TYPE_ERROR, "Request validation", Constants.ERROR_CODE_INVALID_XML + ":" + Constants.ERROR_MSG_INVALID_XML, ExceptionUtils.getFullStackTrace(ex));
	
		ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(response,HttpStatus.OK);
		
		LOGGER.debug("responseEntity:{}",responseEntity);
		return responseEntity;
	}
	
	@ExceptionHandler(InternalServerException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<Response> handleInternalServerException(InternalServerException ex) {
		
		String code = messageSource.getMessage(ex.getErrorCodeKey(), null , LocaleContextHolder.getLocale());
		String message = messageSource.getMessage(ex.getErrorMessageKey(), null , LocaleContextHolder.getLocale());
		
		Response response = new Response();
		response.setResponseCode(code);
		response.setResponseMessage(message);

		dbLoggerService.log(Constants.LOG_TYPE_ERROR, "Request validation", Constants.ERROR_CODE_INVALID_XML + ":" + Constants.ERROR_MSG_INVALID_XML, ExceptionUtils.getFullStackTrace(ex));
	
		ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(response,HttpStatus.OK);
		
		LOGGER.debug("responseEntity:{}",responseEntity);
		return responseEntity;
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ResponseEntity<Response> handleInternalServerException(ResourceNotFoundException ex) {
		
		String code = messageSource.getMessage(ex.getErrorCodeKey(), null , LocaleContextHolder.getLocale());
		String message = messageSource.getMessage(ex.getErrorMessageKey(), null , LocaleContextHolder.getLocale());
		
		Response response = new Response();
		response.setResponseCode(code);
		response.setResponseMessage(message);

		dbLoggerService.log(Constants.LOG_TYPE_ERROR, "Request validation", Constants.ERROR_CODE_INVALID_XML + ":" + Constants.ERROR_MSG_INVALID_XML, ExceptionUtils.getFullStackTrace(ex));
	
		ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(response,HttpStatus.OK);
		
		LOGGER.debug("responseEntity:{}",responseEntity);
		return responseEntity;
	}
	
	@Override
	public void setMessageSource(MessageSource messageSource) {
		  this.messageSource = messageSource;		
	}
}
