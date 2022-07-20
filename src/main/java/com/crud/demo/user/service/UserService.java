package com.crud.demo.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.crud.demo.user.User;

public interface UserService {

    List<User> getAllUsers();

    Optional<User> getOneUser(long id);

    User saveUser(User user);

    int deleteUser(long id);

    User updateUser(long id, User user);
}
