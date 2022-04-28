package com.api.cadastro.cadastroms.view.handlers;

import java.util.ArrayList;
import java.util.List;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ValidationHandler extends ResponseEntityExceptionHandler {
    
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {

        final List<FieldError> erros = ex.getBindingResult().getFieldErrors();

        final List<String> listaErros = new ArrayList<>();

        for (FieldError fe : erros) {
            // nome do atributo que] deu erro mais a mensagem do erro
            listaErros.add(fe.getField() + " " + fe.getDefaultMessage());
        }

        return new ResponseEntity<Object>(listaErros, HttpStatus.NOT_ACCEPTABLE);
    }
}
