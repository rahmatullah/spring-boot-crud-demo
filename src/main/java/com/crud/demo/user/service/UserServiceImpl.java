package com.crud.demo.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.demo.user.User;
import com.crud.demo.user.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        // return null;
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getOneUser(long id) {
        return userRepository.findById(id);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public int deleteUser(long id) {
        userRepository.deleteById(id);

        return 1;
    }

    @Override
    public User updateUser(long id, User user) {
        Optional<User> userOptional = userRepository.findById(id);

        if (!userOptional.isPresent())
            return null;

        user.setId(id);
        userRepository.save(user);
        return user;
    }
}
