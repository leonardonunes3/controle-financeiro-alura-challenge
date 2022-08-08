package br.com.alura.controlefinanceiro.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestControllerExceptionAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<ErrorMessage> errorMessageList = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> {
                    return new ErrorMessage( ((FieldError) error).getField(), error.getDefaultMessage() );
                })
                .collect(Collectors.toList());

        return ResponseEntity.badRequest().body(errorMessageList);
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    class ErrorMessage{
        private String field;
        private String message;
    }

}
