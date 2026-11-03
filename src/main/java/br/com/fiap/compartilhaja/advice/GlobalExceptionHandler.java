package br.com.fiap.compartilhaja.advice;

import br.com.fiap.compartilhaja.exception.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Classe responsável pelo tratamento global de exceções da aplicação.
// Cada método anotado com @ExceptionHandler intercepta um tipo de exceção e devolve ao consumidor da API uma resposta clara com o status HTTP adequado.

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 400 - Bad Request: campos que não passaram no Bean Validation

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException error) {
        Map<String, String> errorMap = new HashMap<>();
        List<FieldError> campos = error.getBindingResult().getFieldErrors();

        for (FieldError campo : campos) {
            errorMap.put(campo.getField(), campo.getDefaultMessage());
        }

        return errorMap;
    }

    // 404 - Not Found: recurso solicitado não existe no banco de dados

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public Map<String, String> handleResourceNotFound(ResourceNotFoundException error) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("erro", error.getMessage());
        return errorMap;
    }

    // 409 - Conflict: violação de integridade (registro duplicado ou chave estrangeira)

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public Map<String, String> handleIntegrityViolation() {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("erro", "Registro já cadastrado ou vinculado a outro registro!");
        return errorMap;
    }

}
