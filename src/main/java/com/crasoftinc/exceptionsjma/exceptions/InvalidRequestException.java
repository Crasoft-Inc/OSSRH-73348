package com.crasoftinc.exceptionsjma.exceptions;
// some changes might take place
public class InvalidRequestException extends CustomGeneralException {

  public InvalidRequestException(String message, String code) {
    super(message, code);
  }

  public InvalidRequestException(String code) {
    this("Invalid Request", code);
  }

  public InvalidRequestException() {
    this("Invalid Request", "0000");
  }
}
