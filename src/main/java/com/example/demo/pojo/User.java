package com.example.demo.pojo;

import lombok.Data;

/**
 * @Author: Eden
 * @Date: 2019/4/17 16:36
 * @Version 1.0
 */
@Data
public class User {
    private Integer uid;
    private String uuid;
    private String logname;
    private String password;
    private String username;
    private Integer age;
    private String address;
}
