package com.example.demo.service.impl;

import com.example.demo.dao.UserMapper;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Eden
 * @Date: 2019/4/17 16:57
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    //存储字符串类型
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    //存储对象类型
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public PageInfo<User> findAll(int currentPage, int pageSize) {
        PageHelper.startPage(currentPage,pageSize);
        List<User> users = userMapper.findAll();
        PageInfo<User> pageInfo = new PageInfo<>(users);
        return pageInfo;
    }

    @Override
    public User login(String logname, String pwd) {
        return userMapper.findOne(logname,pwd);
    }

    @Override
    public int addOne(User user) {
        return userMapper.addOne(user);
    }

    @Override
    public boolean deleteOne(int uid) {
        return userMapper.deleteOne(uid) > 0;
    }

    @Override
    public List<User> findLike(String un) {
        return userMapper.findLike(un);
    }

    @Override
    public boolean modifyOne(User user) {
        return userMapper.modifyOne(user) > 0;
    }
}
