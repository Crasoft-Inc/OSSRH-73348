package com.crasoftinc.exceptionsjma.exceptions;

import java.util.List;
import java.util.stream.Collectors;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class ApplicationExceptionsHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = {CustomGeneralException.class})
  public ResponseEntity<Object> generalExceptionHandler(CustomGeneralException ex,
      WebRequest request) {
    CustomErrorModel response = new CustomErrorModel(ex.getMessage(), ex.getCode(),
        HttpStatus.INTERNAL_SERVER_ERROR.value());
    return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(value = {InvalidRequestException.class})
  public ResponseEntity<Object> handleInvalidRequests(InvalidRequestException ex,
      WebRequest request) {
    CustomErrorModel response = new CustomErrorModel(ex.getMessage(), ex.getCode(),
        HttpStatus.BAD_REQUEST.value());
    return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(value = {NotFoundRequestException.class})
  public ResponseEntity<Object> handleNotFoundRequests(NotFoundRequestException ex,
      WebRequest request) {
    CustomErrorModel response = new CustomErrorModel(ex.getMessage(), ex.getCode(),
        HttpStatus.NOT_FOUND.value());
    return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(value = {UnauthorizedRequestException.class})
  public ResponseEntity<Object> handleUnauthenticatedExceptionHandler(CustomGeneralException ex,
      WebRequest request) {
    CustomErrorModel response = new CustomErrorModel(ex.getMessage(), ex.getCode(),
        HttpStatus.UNAUTHORIZED.value());
    return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(value = {ForbiddenRequestException.class})
  public ResponseEntity<Object> handleForbiddenExceptionHandler(CustomGeneralException ex,
      WebRequest request) {
    CustomErrorModel response = new CustomErrorModel(ex.getMessage(), ex.getCode(),
        HttpStatus.FORBIDDEN.value());
    return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.FORBIDDEN);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {
    CustomErrorModel response = new CustomErrorModel(ex.getMessage(), "0000",
        HttpStatus.BAD_REQUEST.value());
    return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(value = {ConstraintViolationException.class})
  public ResponseEntity<Object> handleConstraintViolationException(
      ConstraintViolationException ex) {
    CustomErrorModel response = new CustomErrorModel("Invalid input parameters: " + ex.getMessage(),
        "1003", HttpStatus.BAD_REQUEST.value());
    return ResponseEntity.badRequest().body(response);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {
    List<String> validationList = ex.getBindingResult().getFieldErrors().stream()
        .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage()).collect(
            Collectors.toList());
    CustomErrorModel response = new CustomErrorModel("Invalid input parameters: " + validationList,
        "0000", HttpStatus.BAD_REQUEST.value());
    return new ResponseEntity<>(response, status);
  }

}
