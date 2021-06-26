package com.pbs.exception;

import java.nio.file.AccessDeniedException;

import javax.validation.ConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.log4j.Log4j2;

@Log4j2
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(
      value = {ConstraintViolationException.class, DataIntegrityViolationException.class})
  public ResponseEntity<Object> handleMissingServletRequestParameter(
      Exception ex, WebRequest request) {
    final String bodyOfResponse = ex.getMessage();
    return handleExceptionInternal(
        ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
  }

  @ExceptionHandler({AccessDeniedException.class})
  public ResponseEntity<Object> handleAccessDeniedException(
      AccessDeniedException ex, WebRequest request) {
    final String bodyOfResponse = ex.getMessage();
    return new ResponseEntity<Object>(bodyOfResponse, new HttpHeaders(), HttpStatus.FORBIDDEN);
  }

  @ExceptionHandler({
    NullPointerException.class,
    IllegalArgumentException.class,
    IllegalStateException.class
  })
  public ResponseEntity<Object> handleInternal(
      final RuntimeException ex, final WebRequest request) {
    log.error("Error {}", ex);
    final String bodyOfResponse = ex.getMessage();
    return handleExceptionInternal(
        ex, bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
  }
}
