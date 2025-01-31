package com.crio.starEasy.service;

import com.crio.starEasy.exchanges.request.UserRequest;
import com.crio.starEasy.model.User;

import java.util.List;

public interface UserService {

    public User createUser(UserRequest userRequest);
    public User getUser(Integer userId);
    public List<User> getAllUsers();
    public User updateUser(Integer userId, UserRequest userRequest);
    public void deleteUser(Integer userId);
}
