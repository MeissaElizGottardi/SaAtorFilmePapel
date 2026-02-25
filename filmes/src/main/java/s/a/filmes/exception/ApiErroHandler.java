package s.a.filmes.exception;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiErroHandler {

    @ExceptionHandler(RequestException.class)
    public ResponseEntity<?> handle (RequestException ex) {
        return ResponseEntity
                .badRequest()
                .body(Map.of("error", ex.getErrorCode(), "message", ex.getMessage()));
    }
}
