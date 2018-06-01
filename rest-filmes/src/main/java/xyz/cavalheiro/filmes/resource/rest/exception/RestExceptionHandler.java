package xyz.cavalheiro.filmes.resource.rest.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import xyz.cavalheiro.filmes.domain.DetalheErro;
import xyz.cavalheiro.filmes.exception.IdNaoValidoServiceException;
import xyz.cavalheiro.filmes.exception.NaoExisteDaoException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({IdNaoValidoServiceException.class})
    public ResponseEntity<Object> idInvalido(IdNaoValidoServiceException ex, WebRequest request) {

        return handleExceptionInternal(ex, DetalheErro.builder().addErro(ex.getMessage()).addStatus(HttpStatus.BAD_REQUEST).addHttpMethod(getHttpMethod(request)).addPath(getPath(request)).build(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({org.hibernate.exception.ConstraintViolationException.class})
    public ResponseEntity<Object> constraintViolada(org.hibernate.exception.ConstraintViolationException ex, WebRequest request) {

        return handleExceptionInternal(ex, DetalheErro.builder().addDetalhe("Constraint violada: " + ex.getConstraintName()).addErro(ex.getMessage()).addStatus(HttpStatus.CONFLICT).addHttpMethod(getHttpMethod(request)).addPath(getPath(request)).build(), new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler({org.hibernate.PropertyValueException.class})
    public ResponseEntity<Object> propriedadeNula(org.hibernate.PropertyValueException ex, WebRequest request) {

        return handleExceptionInternal(ex, DetalheErro.builder().addDetalhe("O atributo '" + ex.getPropertyName() + "' não pode ser nulo.").addErro(ex.getMessage()).addStatus(HttpStatus.BAD_REQUEST).addHttpMethod(getHttpMethod(request)).addPath(getPath(request)).build(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({NaoExisteDaoException.class})
    public ResponseEntity<Object> entidadeNaoEncontrada(NaoExisteDaoException ex, WebRequest request) {

        return handleExceptionInternal(ex, DetalheErro.builder().addDetalhe("Recurso não encontrado na base de dados.").addErro(ex.getMessage()).addStatus(HttpStatus.NOT_FOUND).addHttpMethod(getHttpMethod(request)).addPath(getPath(request)).build(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({NullPointerException.class, IllegalArgumentException.class})
    public ResponseEntity<Object> serverException(RuntimeException ex, WebRequest request) {

        return handleExceptionInternal(ex, DetalheErro.builder().addDetalhe("Exceção Lançada").addErro(ex.getMessage()).addStatus(HttpStatus.INTERNAL_SERVER_ERROR).addHttpMethod(getHttpMethod(request)).addPath(getPath(request)).build(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    private String getPath(WebRequest request) {

        return ((ServletWebRequest) request).getRequest().getRequestURI();
    }

    private String getHttpMethod(WebRequest request) {

        return ((ServletWebRequest) request).getRequest().getMethod();
    }
}
