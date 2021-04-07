package com.luan.user.rest.api.repository;

import com.luan.user.rest.api.model.UserPhoneModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserPhoneRepository extends JpaRepository<UserPhoneModel, Integer> {
    UserPhoneModel findByUserIdAndPhone(Integer userId, String phone);
    List<UserPhoneModel> findByUserId(Integer userId);
}
