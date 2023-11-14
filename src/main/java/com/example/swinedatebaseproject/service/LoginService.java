package com.example.swinedatebaseproject.service;

import com.example.swinedatebaseproject.domain.Administrator;
import com.example.swinedatebaseproject.domain.CommonUser;

/**
 * @Author 刘铭康
 * @Date 2022/11/14
 */
public interface LoginService {
    boolean userLogin(CommonUser commonUser);

    boolean administratorLogin(Administrator administrator);
}
