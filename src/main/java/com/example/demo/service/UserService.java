package com.example.demo.service;

import com.example.demo.pojo.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Author: Eden
 * @Date: 2019/4/17 16:56
 * @Version 1.0
 */
public interface UserService {
    PageInfo<User> findAll(int currentPage, int pageSize);

    User login(String logname, String pwd);

    int addOne(User user);

    boolean deleteOne(int uid);

    //模糊查询
    List<User> findLike(String un);

    boolean modifyOne(User user);
}
