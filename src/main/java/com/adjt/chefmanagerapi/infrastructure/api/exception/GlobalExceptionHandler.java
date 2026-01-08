package com.adjt.chefmanagerapi.infrastructure.api.exception;

import com.adjt.chefmanagerapi.core.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.OffsetDateTime;
import java.util.NoSuchElementException;


@RestControllerAdvice
public class GlobalExceptionHandler {

    public static final String TIMESTAMP = "timestamp";

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ProblemDetail> handleDomainException(BaseException ex) {
        HttpStatus status = mapStatus(ex);
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(status, ex.getMessage());
        pd.setTitle(ex.getClass().getSimpleName());
        pd.setType(URI.create("https://example.com/problems/" + ex.getClass().getSimpleName()));
        pd.setProperty(TIMESTAMP, OffsetDateTime.now());
        return ResponseEntity.status(status).body(pd);
    }

    private HttpStatus mapStatus(BaseException ex) {
        if (ex instanceof SenhaInvalidaException
                || ex instanceof LoginJaCadastradoException
                || ex instanceof LoginObrigatorioException
                || ex instanceof EmailJaCadastradoException
                || ex instanceof EmailInvalidoException
                || ex instanceof EmailObrigatorioException) {
            return HttpStatus.BAD_REQUEST;
        }
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ProblemDetail> handleIllegal(IllegalArgumentException ex) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        pd.setTitle("Bad Request");
        pd.setType(URI.create("https://example.com/problems/bad-request"));
        pd.setProperty(TIMESTAMP, OffsetDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(pd);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetail> handleValidation(MethodArgumentNotValidException ex) {
        ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        pd.setTitle("Validation Error");
        pd.setDetail("Campos inválidos ou ausentes");
        var errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(f -> f.getField() + ": " + f.getDefaultMessage())
                .toList();
        pd.setProperty("errors", errors);
        pd.setType(URI.create("https://example.com/problems/validation-error"));
        pd.setProperty(TIMESTAMP, OffsetDateTime.now());
        return ResponseEntity.badRequest().body(pd);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ProblemDetail> handleNotFound(NoSuchElementException ex) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        pd.setTitle("Not Found");
        pd.setType(URI.create("https://example.com/problems/not-found"));
        pd.setProperty(TIMESTAMP, OffsetDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(pd);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> handleUnexpected(Exception ex) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        pd.setTitle("Erro Interno do Servidor");
        pd.setType(URI.create("https://example.com/problems/internal-server-error"));
        pd.setProperty(TIMESTAMP, OffsetDateTime.now());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(pd);
    }
}
