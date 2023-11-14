package com.example.swinedatebaseproject.service.impl;

import com.example.swinedatebaseproject.cache.UserCache;
import com.example.swinedatebaseproject.domain.Administrator;
import com.example.swinedatebaseproject.domain.CommonUser;
import com.example.swinedatebaseproject.service.AdministratorService;
import com.example.swinedatebaseproject.service.CommonUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author 刘铭康
 * @Date 2022/11/14
 */

@Service
public class RegisterServiceImpl implements com.example.swinedatebaseproject.service.RegisterService {

    @Autowired
    CommonUserService commonUserService;

    @Autowired
    AdministratorService administratorService;


    @Override
    public boolean userRegister(CommonUser commonUser) {
        if (UserCache.COMMON_USERS.contains(commonUser.getName())) {
            return false;
        } else {
            commonUserService.save(commonUser);
            UserCache.COMMON_USERS.add(commonUser.getName());
            return true;
        }
    }

    @Override
    public boolean administratorRegister(Administrator administrator) {
        if (UserCache.ADMINISTRATORS.contains(administrator.getName())) {
            return false;
        } else {
            administratorService.save(administrator);
            UserCache.COMMON_USERS.add(administrator.getName());
            return true;
        }
    }

}
