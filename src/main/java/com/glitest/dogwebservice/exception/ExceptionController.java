package com.glitest.dogwebservice.exception;

import com.glitest.dogwebservice.exception.type.BadRequestError;
import com.glitest.dogwebservice.exception.type.InternalServerError;
import com.glitest.dogwebservice.exception.type.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(InternalServerError.class)
    public ResponseEntity<GlobalException> handleInternalServerError(InternalServerError e) {
        GlobalException globalException = new GlobalException(
                e.getMessage(),
                e.getReason(),
                LocalDateTime.now()
        );
        return new ResponseEntity<GlobalException>(globalException, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadRequestError.class)
    public ResponseEntity<GlobalException> handleInternalServerError(BadRequestError e) {
        GlobalException globalException = new GlobalException(
                e.getMessage(),
                e.getReason(),
                LocalDateTime.now()
        );
        return new ResponseEntity<GlobalException>(globalException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<GlobalException> handleInternalServerError(NotFoundException e) {
        GlobalException globalException = new GlobalException(
                e.getMessage(),
                e.getReason(),
                LocalDateTime.now()
        );
        return new ResponseEntity<GlobalException>(globalException, HttpStatus.NOT_FOUND);
    }
}
