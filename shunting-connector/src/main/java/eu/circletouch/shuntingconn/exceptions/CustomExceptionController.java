package eu.circletouch.shuntingconn.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionController {

    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<Object> exception(CustomException customException) {
        return new ResponseEntity<>(customException.getMessage(), customException.getHttpStatus());
    }
}