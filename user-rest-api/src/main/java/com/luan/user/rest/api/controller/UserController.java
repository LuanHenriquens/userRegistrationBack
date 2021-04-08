package com.luan.user.rest.api.controller;

import com.luan.user.rest.api.model.UserModel;
import com.luan.user.rest.api.model.UserPhoneModel;
import com.luan.user.rest.api.model.dto.UserInsertDto;
import com.luan.user.rest.api.model.dto.UserDto;
import com.luan.user.rest.api.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private IUserService service;

    @GetMapping
    public List<UserModel> Get(String name, Boolean active) throws Exception {
        if (active != null && (name == null || name.trim().length() == 0))
            return service.GetByActive(active);
        if (name != null && name.trim().length() > 0 && active == null)
            return service.GetByName(name);
        if (name != null && name.trim().length() > 0 && active != null)
            return service.GetByNameContainingAndActive(name, active);

        return service.GetAll();
    }

    @GetMapping(path = "/{userId}")
    public UserDto GetById(@PathVariable("userId") Integer userId) throws Exception {
        return service.GetById(userId);
    }

    @PostMapping
    public UserModel Insert(@RequestBody UserInsertDto userInsertDto) {
        return service.Insert(userInsertDto);
    }

    @PutMapping
    public UserDto Update(@RequestBody UserDto userDto) {
        return service.Update(userDto);
    }

    @PostMapping(path = "/delete/user")
    public void Delete(@RequestBody UserModel user) {
        service.Delete(user);
    }

    @GetMapping(path = "/phone")
    public List<UserPhoneModel> GetPhone(Integer userId) {
        return service.GetPhone(userId);
    }

    @PostMapping(path = "/delete/phone")
    public void DeletePhone(@RequestBody UserPhoneModel phone) {
        try {
            service.DeletePhone(phone);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
