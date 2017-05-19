package controller;


import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.text.ParseException;


@ControllerAdvice
@Slf4j
public class ExceptionHandlingController {

    @ExceptionHandler(ParseException.class)
    public String handleParseException(ParseException ex) {
        log.error("PARSE EXCEPTION - " + ex.getMessage());
        return "error";

    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public String handleNumberFormatException(DataIntegrityViolationException ex) {
        log.error("NUMBER FORMAT EXCEPTION - " + ex.getMessage());
        return "error/userExists";

    }

    @ExceptionHandler(ConstraintViolationException.class)
    public String handleConstraintViolationException(ConstraintViolationException ex) {
        log.error("CONSTRAINT VIOLATION EXCEPTION - " + ex.getMessage());
        return "error/userExists";

    }

    @ExceptionHandler(NullPointerException.class)
    public String handleNullPointerException(NullPointerException ex) {
        log.error("NULL POINTER EXCEPTION - " + ex.getMessage());
        return "error";
    }

    @ExceptionHandler(IndexOutOfBoundsException.class)
    public String handleIndexOutOfBoundsException(IndexOutOfBoundsException ex) {
        log.error("INDEX OUT OF BOUNDS - " + ex.getMessage());
        return "error";
    }
}
