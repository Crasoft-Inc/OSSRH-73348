package com.crasoftinc.exceptionsjma.exceptions;

import java.util.List;
import java.util.stream.Collectors;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseBody
public class ApplicationExceptionsHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = {CustomGeneralException.class})
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseEntity<Object> generalExceptionHandler(CustomGeneralException ex,
                                                        WebRequest request) {
    CustomErrorModel response = new CustomErrorModel(ex.getMessage(), ex.getCode(),
        HttpStatus.INTERNAL_SERVER_ERROR.value());
    return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(value = {InvalidRequestException.class})
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ResponseEntity<Object> handleInvalidRequests(InvalidRequestException ex,
                                                      WebRequest request) {
    CustomErrorModel response =
        new CustomErrorModel(ex.getMessage(), ex.getCode(), HttpStatus.BAD_REQUEST.value());
    return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(value = {NotFoundRequestException.class})
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  public ResponseEntity<Object> handleNotFoundRequests(NotFoundRequestException ex,
                                                       WebRequest request) {
    CustomErrorModel response =
        new CustomErrorModel(ex.getMessage(), ex.getCode(), HttpStatus.NOT_FOUND.value());
    return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(value = {UnauthorizedRequestException.class})
  @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
  public ResponseEntity<Object> handleUnauthenticatedExceptionHandler(CustomGeneralException ex,
                                                                      WebRequest request) {
    CustomErrorModel response =
        new CustomErrorModel(ex.getMessage(), ex.getCode(), HttpStatus.UNAUTHORIZED.value());
    return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(value = {ForbiddenRequestException.class})
  @ResponseStatus(value = HttpStatus.FORBIDDEN)
  public ResponseEntity<Object> handleForbiddenExceptionHandler(CustomGeneralException ex,
                                                                WebRequest request) {
    CustomErrorModel response =
        new CustomErrorModel(ex.getMessage(), ex.getCode(), HttpStatus.FORBIDDEN.value());
    return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.FORBIDDEN);
  }

  @Override
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                HttpHeaders headers,
                                                                HttpStatus status,
                                                                WebRequest request) {
    CustomErrorModel response =
        new CustomErrorModel(ex.getMessage(), "0000", HttpStatus.BAD_REQUEST.value());
    return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(value = {ConstraintViolationException.class})
  public ResponseEntity<Object> handleConstraintViolationException(
      ConstraintViolationException ex) {
    CustomErrorModel response =
        new CustomErrorModel("Invalid input parameters: " + ex.getMessage(), "1003",
            HttpStatus.BAD_REQUEST.value());
    return ResponseEntity.badRequest().body(response);
  }

  @Override
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                HttpHeaders headers, HttpStatus status, WebRequest request) {
    System.out.println(ex);
    String message = ex.getAllErrors().stream().map(o -> o.getDefaultMessage()).collect(Collectors.joining(" |\n"));
    CustomErrorModel response = new CustomErrorModel(message , "0000", HttpStatus.BAD_REQUEST.value());
    return new ResponseEntity<>(response, status);
  }

  @ExceptionHandler(value = {UsernameNotFoundException.class})
  public ResponseEntity<Object> handleUsernameNotFoundException(
      UsernameNotFoundException ex) {
    CustomErrorModel response =
        new CustomErrorModel("No user with given username found", "0000",
            HttpStatus.FORBIDDEN.value());
    return ResponseEntity.badRequest().body(response);
  }
}
