package com.luan.user.rest.api.model.dto;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("erro")
public class ErroDto {

    private static final long serialVersionUID = -2243742480047312690L;

    private Integer codigo;
    private String mensagem;

    public ErroDto() {
        super();
    }

    public ErroDto(final Integer codigo, final String mensagem) {
        super();
        this.codigo = codigo;
        this.mensagem = mensagem;
    }

    public ErroDto(final String mensagem) {
        super();
        this.mensagem = mensagem;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(final Integer codigo) {
        this.codigo = codigo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(final String mensagem) {
        this.mensagem = mensagem;
    }

    @Override
    public String toString() {
        return "ErroDto [codigo=" + codigo + ", mensagem=" + mensagem + "]";
    }

}

