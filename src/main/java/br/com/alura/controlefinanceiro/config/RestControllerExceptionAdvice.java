package br.com.alura.controlefinanceiro.config;

import br.com.alura.controlefinanceiro.exceptions.ValidacaoException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<ErrorMessage>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<ErrorMessage> errorMessageList = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> {
                    return new ErrorMessage(((FieldError) error).getField(), error.getDefaultMessage());
                })
                .collect(Collectors.toList());

        return ResponseEntity.badRequest().body(errorMessageList);
    }

    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity<ErrorMessage> handleValidationException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage("Validation", ex.getMessage()));
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    static class ErrorMessage{
        private String field;
        private String message;
    }

}
