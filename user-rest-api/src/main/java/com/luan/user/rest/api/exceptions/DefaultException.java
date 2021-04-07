package com.luan.user.rest.api.exceptions;

import com.luan.user.rest.api.model.dto.ErroDto;
import com.luan.user.rest.api.model.dto.ResponseDefaultDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;


public abstract class DefaultException extends RuntimeException {
    public DefaultException() {
        super();
    }
    public DefaultException(String message, Throwable cause) {
        super(message, cause);
    }
    public DefaultException(String message) {
        super(message);
    }
    public DefaultException(Throwable cause) {
        super(cause);
    }

    public ResponseEntity<ResponseDefaultDto> buildResponse() {

        final ResponseDefaultDto responseDefault = new ResponseDefaultDto();

        responseDefault.setCodigo(getCodigo());
        responseDefault.setMensagem(getMensagem());
        responseDefault.setErros(getErros());

        return ResponseEntity.status(getStatusCode()).body(responseDefault);
    }

    protected abstract HttpStatus getStatusCode();

    public List<ErroDto> getErros() {
        return new ArrayList<>();
    }

    protected Integer getCodigo() {
        return getStatusCode().value();
    }

    protected String getMensagem() {
        return getMessage();
    }
}
