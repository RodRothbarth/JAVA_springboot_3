package med.voll.api.infra.appError;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratar404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratar400(MethodArgumentNotValidException error){
        var err = error.getFieldErrors();

        return ResponseEntity.badRequest().body(err.stream().map(DadosErrorValidacao::new).toList());
    }

    private record DadosErrorValidacao(String campo, String mensagem){
        public DadosErrorValidacao(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }

}