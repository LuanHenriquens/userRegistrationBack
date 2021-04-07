package com.luan.user.rest.api.service.Impl;

import com.luan.user.rest.api.exceptions.BadRequestException;
import com.luan.user.rest.api.exceptions.ConflictException;
import com.luan.user.rest.api.exceptions.NotFoundException;
import com.luan.user.rest.api.model.UserModel;
import com.luan.user.rest.api.model.UserPhoneModel;
import com.luan.user.rest.api.model.dto.UserInsertDto;
import com.luan.user.rest.api.model.dto.UserDto;
import com.luan.user.rest.api.repository.IUserPhoneRepository;
import com.luan.user.rest.api.repository.IUserRepository;
import com.luan.user.rest.api.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IUserPhoneRepository userPhoneRepository;

    public List<UserModel> GetAll() {
        return userRepository.findAll();
    }
    public UserDto GetById(Integer userId) {

        var user = userRepository.findById(userId).get();

        if (user == null)
            throw new NotFoundException("Usuario não encontrado.");

        var phone = GetPhone(userId);
        var userDto = new UserDto();
        userDto.user = user;
        userDto.phone = phone;
        return  userDto;
    }
    public List<UserModel> GetByName(String name) {
        if (name == null || name.trim().length() == 0)
            throw new NotFoundException("O nome é obrigatório.");

        return userRepository.findUserByNameContaining(name);
    }
    public List<UserModel> GetByActive(Boolean active)
    {
        return userRepository.findUserByActive(active);
    }
    public List<UserModel> GetByNameContainingAndActive(String name,Boolean active)
    {
        return userRepository.findUserByNameContainingAndActive(name,active);
    }

    public UserModel Insert(UserInsertDto userInsertDto){
        Validations(userInsertDto);

        var user = new UserModel();
        user.name = userInsertDto.Name;
        user.personType = userInsertDto.PersonType;
        user.cpf = userInsertDto.Cpf == null || userInsertDto.Cpf.trim().length() == 0 ? "" : userInsertDto.Cpf;
        user.cnpj = userInsertDto.Cnpj == null || userInsertDto.Cnpj.trim().length() == 0 ? "" : userInsertDto.Cnpj;
        user.rg = userInsertDto.Rg == null || userInsertDto.Rg.trim().length() == 0 ? "" : userInsertDto.Rg;
        user.ie = userInsertDto.Ie == null || userInsertDto.Ie.trim().length() == 0 ? "" : userInsertDto.Ie;
        user.active = userInsertDto.Active;
        user.registrationDate = new Date().from(Instant.now());
        user = userRepository.save(user);

        if (userInsertDto.Phone != null) {
            UserModel finalUser = user;
            userInsertDto.Phone.forEach(c -> c.userId = finalUser.id);
        }
            userPhoneRepository.saveAll(userInsertDto.Phone);

        return user;
    }

    public UserDto Update(UserDto userDto){
        UpdateValidations(userDto.user);

        userRepository.save(userDto.user);

        if (userDto.phone != null)
            userPhoneRepository.saveAll(userDto.phone);

        return userDto;
    }


    public void Delete(Integer userId){
        var user = userRepository.findById(userId).get();
        var phones = GetPhone(userId);
        if(phones != null)
            phones.forEach(c ->
                userPhoneRepository.deleteById(c.id));
        userRepository.delete(user);
    }

    public List<UserPhoneModel> GetPhone(Integer userId)
    {
        if (userId == null || userId == 0)
            throw new BadRequestException("Por favor informe o id do usuario");

        return userPhoneRepository.findByUserId(userId);
    }

    public void DeletePhone(String phone, Integer userId)
    {
        var userPhone = userPhoneRepository.findByUserIdAndPhone(userId, phone);

        if (userPhone == null) {
            throw new NotFoundException("Telefone não encontrado.");
        }

        userPhoneRepository.delete(userPhone);
    }

    private void Validations(UserInsertDto userInsertDto) {
        if (userInsertDto.Name == null || userInsertDto.Name.trim().length() == 0)
            throw new BadRequestException("Por favor informe o nome.");

        if (userInsertDto.PersonType == null || userInsertDto.PersonType.trim().length() == 0)
            throw new BadRequestException("Por favor informe o tipo da pessoa.");

        if (!userInsertDto.PersonType.equals("f") && !userInsertDto.PersonType.equals("j"))
            throw new NotFoundException("O tipo de pessoa informado não existe.");

        if (userInsertDto.PersonType.equals("f"))
        {
            if (userInsertDto.Cpf == null || userInsertDto.Cpf.trim().length() == 0)
                throw new BadRequestException("Por favor informe o cpf.");
            if (userInsertDto.Rg == null || userInsertDto.Rg.trim().length() == 0)
                throw new BadRequestException("Por favor informe o rg.");
        } else {
            if (userInsertDto.Cnpj == null || userInsertDto.Cnpj.trim().length() == 0)
                throw new BadRequestException("Por Favor informe o cnpj.");
            if (userInsertDto.Ie == null || userInsertDto.Ie.trim().length() == 0)
                throw new BadRequestException("Por favor informe a inscrição estadual.");
        }

        ValidateUserExists(userInsertDto);
    }

    private void UpdateValidations(UserModel user) {
        if (user.name == null || user.name.trim().length() == 0)
            throw new BadRequestException("Por favor informe o nome.");

        if (user.personType == null || user.personType.trim().length() == 0)
            throw new BadRequestException("Por favor informe o tipo da pessoa.");

        if (!user.personType.equals("f") && !user.personType.equals("j"))
            throw new NotFoundException("O tipo de pessoa informado não existe.");

        if (user.personType.equals("f"))
        {
            if (user.cpf == null || user.cpf.trim().length() == 0)
                throw new BadRequestException("Por favor informe o cpf.");
            if (user.rg == null || user.rg.trim().length() == 0)
                throw new BadRequestException("Por favor informe o rg.");
        } else {
            if (user.cnpj == null || user.cnpj.trim().length() == 0)
                throw new BadRequestException("Por Favor informe o cnpj.");
            if (user.ie == null || user.ie.trim().length() == 0)
                throw new BadRequestException("Por favor informe a inscrição estadual.");
        }
    }

    private void ValidateUserExists(UserInsertDto userInsertDto)
    {
        UserModel user;
        if (userInsertDto.PersonType.equals("f"))
            user = userRepository.findUserByCpf(userInsertDto.Cpf);
        else
            user = userRepository.findUserByCnpj(userInsertDto.Cnpj);

        if(user != null) {
            var document = userInsertDto.PersonType.equals("f") ? "CPF" : "CNPJ";
            throw new ConflictException("Já existe um usuário com esse " + document + ".");
        }
    }
}