package jwtexam.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //@RequestBody @Valid  검증실패하면 여기 실행
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValid(MethodArgumentNotValidException e){
        Map<String, Object> body = new HashMap<>();
        Map<String , String> errors = new HashMap<>();

        for(FieldError fe : e.getBindingResult().getFieldErrors()){
            errors.put(fe.getField(), fe.getDefaultMessage());
        }
        body.put("message","요청 본문 검증에 실패했습니다. ");
        body.put("errors",errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }
}
