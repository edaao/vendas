package projeto.vendas.infra.exception;

import feign.FeignException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import projeto.vendas.domain.ValidacaoException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String>excecaoChaveDuplicada(DataIntegrityViolationException exception) {
        Throwable cause = exception.getRootCause();
        if(cause != null && cause.getMessage().contains("Duplicate entry")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erro: CPF ou e-mail já cadastrado");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro interno no servido");
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> ViolacaoConstraint(ConstraintViolationException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Erro: CPF ou e-mail já cadastrado.");
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErro400() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors();

        return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList());
    }

    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity tratarErrosRegraNegocio(ValidacaoException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity tratarErroFeign(FeignException ex) {
        String responseBody = ex.contentUTF8();

        if(responseBody == null || responseBody.isEmpty()) {
            return ResponseEntity.status(ex.status()).body("Erro ao consultar API externa.");
        }
        return ResponseEntity.status(ex.status()).body(responseBody);
    }

    private record DadosErroValidacao(String campo, String mensagem) {
        public DadosErroValidacao(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }

}
