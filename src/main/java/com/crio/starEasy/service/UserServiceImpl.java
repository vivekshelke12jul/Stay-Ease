package com.crio.starEasy.service;

import com.crio.starEasy.exchanges.request.UserRequest;
import com.crio.starEasy.model.User;
import com.crio.starEasy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(UserRequest userRequest) {
        return null;
    }

    @Override
    public User getUser(Integer userId) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return List.of();
    }

    @Override
    public User updateUser(Integer userId, UserRequest userRequest) {
        return null;
    }

    @Override
    public void deleteUser(Integer userId) {

    }
}
