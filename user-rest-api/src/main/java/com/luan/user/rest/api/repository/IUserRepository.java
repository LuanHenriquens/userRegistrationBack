package com.luan.user.rest.api.repository;

import com.luan.user.rest.api.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepository
        extends JpaRepository<UserModel, Integer> {
//    @Query("select u from User u where u.name = ?1 AND ")
    List<UserModel> findUserByActive(Boolean active);
    List<UserModel> findUserByNameContainingAndActive(String name, Boolean active);
    List<UserModel> findUserByNameContaining(String name);
    UserModel findUserByCpf(String cpf);
    UserModel findUserByCnpj(String cnpj);
}
