package com.example.usermanagesystem.service.impl;

import com.example.usermanagesystem.entity.User;
import com.example.usermanagesystem.mapper.UserMapper;
import com.example.usermanagesystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl extends UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getAllUsers() {
        return userMapper.selectList(null);
    }

    @Override
    public Optional<User> getUserById(int id) {
        return Optional.ofNullable(userMapper.selectById(id));
    }

    @Override
    public boolean addUser(User user) {
        // 为新用户生成一个默认密码
        String defaultPassword = "password123"; // 可以使用更安全的生成方式
        user.setPassword(defaultPassword);
        return userMapper.insert(user) > 0;
    }

    @Override
    public boolean updateUser(User user) {
        return userMapper.updateById(user) > 0;
    }

    @Override
    public boolean deleteUser(int id) {
        return userMapper.deleteById(id) > 0;
    }
} 