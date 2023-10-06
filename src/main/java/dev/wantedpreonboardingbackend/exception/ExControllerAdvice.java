package dev.wantedpreonboardingbackend.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<?> apiExHandle(ApiException e) {
        return ResponseEntity.badRequest().body(new ErrorResult(e.getCustomErrorCode().name(), e.getCustomErrorCode().getMessage()));
    }
}
