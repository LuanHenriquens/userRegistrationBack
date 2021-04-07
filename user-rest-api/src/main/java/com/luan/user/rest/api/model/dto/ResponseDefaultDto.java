package com.luan.user.rest.api.model.dto;

import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

@JsonRootName("responseDefault")
public class ResponseDefaultDto {

    private static final long serialVersionUID = 7180897155494459587L;

    private Integer codigo;
    private String mensagem;
    private List<ErroDto> erros;


    public ResponseDefaultDto(Integer codigo, String mensagem) {
        super();
        this.codigo = codigo;
        this.mensagem = mensagem;
    }

    public ResponseDefaultDto() {
        super();
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

    public List<ErroDto> getErros() {
        if(isNull(erros)) {
            erros = new ArrayList<>();
        }
        return erros;
    }

    public void setErros(final List<ErroDto> erros) {
        this.erros = erros;
    }

    @Override
    public String toString() {
        return "ResponseDefaultDto [codigo=" + codigo + ", mensagem=" + mensagem + ", erros=" + erros + "]";
    }

}
