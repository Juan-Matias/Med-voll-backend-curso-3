package med.voll.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GestorDeErrores {

    // ERROR 404
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity gestionarError404() {
        // Devolvemos código 404 Not Found
        return ResponseEntity.notFound().build();
    }

    // ERROR 400
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity gestionarError400(MethodArgumentNotValidException ex) {
        var errores = ex.getFieldErrors();

        return ResponseEntity.badRequest().body(errores.stream()
                .map(DatosErrorValidacion::new).toList());
    }

    // Record para representar errores de validación
    public record DatosErrorValidacion(String campo, String mensaje) {
    public DatosErrorValidacion(FieldError error){
        this(error.getField(), error.getDefaultMessage());
    }
    }
}
