package com.capstone.alta.hms.api.v1.core.exceptions;

import com.capstone.alta.hms.api.v1.core.dtos.BaseResponseDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@EnableWebMvc
@ControllerAdvice
public class RestControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandler(Exception ex, WebRequest request) {
        BaseResponseDTO response = new BaseResponseDTO<>();
                response.setMessage(ex.getMessage() + ", " + request.getDescription(false));
                response.setData(null);

        return handleExceptionInternal(ex, response, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(ValidationErrorException.class)
    public ResponseEntity<?> validationErrorExceptionHandler(Exception ex, WebRequest request) {
        BaseResponseDTO<Object> response = new BaseResponseDTO<>();
        response.setMessage(ex.getMessage() + ", " + request.getDescription(false));
        response.setData(null);

        return handleExceptionInternal(ex, response, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundErrorExceptionHandler(Exception ex, WebRequest request) {
        BaseResponseDTO<Object> response = new BaseResponseDTO<>();
        response.setMessage(ex.getMessage() + ", " + request.getDescription(false));
        response.setData(null);

        return handleExceptionInternal(ex, response, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(DataAlreadyExistException.class)
    public ResponseEntity<?> dataAlreadyExistsErrorExceptionHandler(Exception ex, WebRequest request) {
        BaseResponseDTO<Object> response = new BaseResponseDTO<>();
        response.setMessage(ex.getMessage() + ", " + request.getDescription(false));
        response.setData(null);

        return handleExceptionInternal(ex, response, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<?> unauthorizedErrorExceptionHandler(Exception ex, WebRequest request) {
        BaseResponseDTO<Object> response = new BaseResponseDTO<>();
        response.setMessage(ex.getMessage() + ", " + request.getDescription(false));
        response.setData(null);

        return handleExceptionInternal(ex, response, new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
    }
}
