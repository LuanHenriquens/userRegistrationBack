package com.luan.user.rest.api.model.dto;

import com.luan.user.rest.api.model.UserPhoneModel;
import com.sun.istack.Nullable;

import java.util.List;

public class UserInsertDto {

    public String Name;
    public String PersonType;
    public String Cpf;
    public String Cnpj;
    public String Rg;
    public String Ie;
    public Boolean Active;
    public List<UserPhoneModel> Phone;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPersonType() {
        return PersonType;
    }

    public void setPersonType(String personType) {
        PersonType = personType;
    }

    public String getCpf() {
        return Cpf;
    }

    public void setCpf(String cpf) {
        Cpf = cpf;
    }

    public String getCnpj() {
        return Cnpj;
    }

    public void setCnpj(String cnpj) {
        Cnpj = cnpj;
    }

    public String getRg() {
        return Rg;
    }

    public void setRg(String rg) {
        Rg = rg;
    }

    public String getIe() {
        return Ie;
    }

    public void setIe(String ie) {
        Ie = ie;
    }

    public Boolean getActive() {
        return Active;
    }

    public void setActive(Boolean active) {
        Active = active;
    }

    public List<UserPhoneModel> getPhone() {
        return Phone;
    }

    public void setPhone(List<UserPhoneModel> phone) {
        Phone = phone;
    }
}