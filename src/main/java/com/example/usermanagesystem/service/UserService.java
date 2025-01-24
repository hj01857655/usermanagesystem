package com.example.usermanagesystem.service;

import com.example.usermanagesystem.entity.User;
import com.example.usermanagesystem.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> getAllUsers() {
        return userMapper.selectList(null);
    }

    public Optional<User> getUserById(int id) {
        return Optional.ofNullable(userMapper.selectById(id));
    }

    public boolean addUser(User user) {
        return userMapper.insert(user) > 0;
    }

    public boolean updateUser(User user) {
        return userMapper.updateById(user) > 0;
    }

    public boolean deleteUser(int id) {
        return userMapper.deleteById(id) > 0;
    }
} 