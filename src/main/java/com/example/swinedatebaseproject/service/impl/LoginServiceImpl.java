package com.example.swinedatebaseproject.service.impl;

import com.example.swinedatebaseproject.cache.UserCache;
import com.example.swinedatebaseproject.domain.Administrator;
import com.example.swinedatebaseproject.domain.CommonUser;
import com.example.swinedatebaseproject.service.AdministratorService;
import com.example.swinedatebaseproject.service.CommonUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @Author 刘铭康
 * @Date 2022/11/14
 */
@Service
public class LoginServiceImpl implements com.example.swinedatebaseproject.service.LoginService {

    @Autowired
    CommonUserService commonUserService;

    @Autowired
    AdministratorService administratorService;
    @PostConstruct
    public void initUserCache() {
        List<CommonUser> commonUsers = commonUserService.list();
        for (CommonUser commonUser : commonUsers) {
            UserCache.COMMON_USERS.add(commonUser.getName());
        }

        List<Administrator> administratorUsers = administratorService.list();
        for (Administrator administratorUser:
                administratorUsers) {
            UserCache.ADMINISTRATORS.add(administratorUser.getName());

        }
    }
    @Override
    public boolean userLogin(CommonUser commonUser) {
        return UserCache.COMMON_USERS.contains(commonUser.getName());
    }

    @Override
    public boolean administratorLogin(Administrator administrator) {
        return UserCache.ADMINISTRATORS.contains(administrator.getName());
    }

}
