package com.danielbrowne.ecommerce.rest.api.service;

import com.danielbrowne.ecommerce.rest.api.entity.User;

import java.util.List;

public interface UserService {
    public User createUser(User user);
    public List<User> getAllUsers();
    public User getUserById(Long id);

    public User updateUser(Long id, User user);

    public String deleteUser(Long id);

}
