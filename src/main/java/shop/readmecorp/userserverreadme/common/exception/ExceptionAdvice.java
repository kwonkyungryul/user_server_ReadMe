package shop.readmecorp.userserverreadme.common.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(Exception400.class)
    public ResponseEntity<Map<String, Object>> handleException400(Exception400 ex) {
        Map<String, Object> errorMap = new HashMap<>();
        errorMap.put("status", HttpStatus.BAD_REQUEST.value());
        errorMap.put("message", ex.getMessage());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(errorMap, headers, HttpStatus.BAD_REQUEST);
    }

}
