package com.luan.user.rest.api.service;

import com.luan.user.rest.api.model.UserModel;
import com.luan.user.rest.api.model.UserPhoneModel;
import com.luan.user.rest.api.model.dto.UserInsertDto;
import com.luan.user.rest.api.model.dto.UserDto;

import java.util.List;

public interface IUserService {
    List<UserModel> GetAll();
    List<UserModel> GetByActive(Boolean active);
    List<UserModel> GetByNameContainingAndActive(String name,Boolean active);
    List<UserModel> GetByName(String name) throws Exception;
    UserDto GetById(Integer userId);
    UserModel Insert(UserInsertDto userInsertDto);
    UserDto Update(UserDto userDto);
    void Delete(Integer userId);
    List<UserPhoneModel> GetPhone(Integer userId);
    void DeletePhone(String phone, Integer userId);
}
