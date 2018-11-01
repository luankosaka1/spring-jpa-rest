package br.com.devmedia.curso.resource.exception;

import br.com.devmedia.curso.domain.DetalheErro;
import br.com.devmedia.curso.exception.NaoExisteDaoException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    public ResponseEntity<Object> propriedadeNula (org.hibernate.PropertyValueException ex, WebRequest request) {
        return handleExceptionInternal(
                ex,
                DetalheErro.builder()
                    .addDetalhe("O atributo '" + ex.getPropertyName() + "' não pode ser nulo.")
                    .addErro((ex.getMessage()))
                    .addStatus(HttpStatus.BAD_REQUEST)
                    .addHttpMethod(getHttpMethod(request))
                    .addPath(getPath(request))
                    .build(),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST,
                request
        );
    }

    @ExceptionHandler({NaoExisteDaoException.class})
    public ResponseEntity<Object> serverException (RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(
                ex,
                DetalheErro.builder()
                        .addDetalhe("Recurso não encontrado na base de dados.")
                        .addErro(ex.getMessage())
                        .addStatus(HttpStatus.NOT_FOUND)
                        .addHttpMethod(getHttpMethod(request))
                        .addPath(getPath(request))
                        .build(),
                new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                request
        );
    }

    private String getHttpMethod(WebRequest request) {
        return ((ServletWebRequest) request).getRequest().getRequestURI();
    }

    private String getPath(WebRequest request) {
        return ((ServletWebRequest) request).getRequest().getMethod();
    }
}
