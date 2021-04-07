package com.luan.user.rest.api.exceptions;

import com.luan.user.rest.api.model.dto.ResponseDefaultDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ResponseDefaultDto> NotFoundException(final NotFoundException ex, final WebRequest request) {
        return ex.buildResponse();
    }

    @ExceptionHandler(value = {BadRequestException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseDefaultDto> BadRequestException(final BadRequestException ex, final WebRequest request) {
        return ex.buildResponse();
    }

    @ExceptionHandler(value = {ConflictException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ResponseDefaultDto> ConflictException(final ConflictException ex, final WebRequest request) {
        return ex.buildResponse();
    }
}