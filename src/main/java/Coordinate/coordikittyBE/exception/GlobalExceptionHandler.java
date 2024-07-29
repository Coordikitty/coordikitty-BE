package Coordinate.coordikittyBE.exception;

import Coordinate.coordikittyBE.global.common.response.ExceptionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {
    private final Logger log = LoggerFactory.getLogger(getClass());
    @ExceptionHandler(CoordikittyException.class)
    public ResponseEntity<ExceptionResponse> handle(CoordikittyException ex){
        log.warn("Coordikitty exception [status={},errorCode={},message={}]", ex.getStatusCode(),ex.getErrorCode(),ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(ExceptionResponse.of(ex.getErrorType()));
    }
}
