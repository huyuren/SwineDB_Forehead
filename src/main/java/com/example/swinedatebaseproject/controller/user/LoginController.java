package com.example.swinedatebaseproject.controller.user;

import com.example.swinedatebaseproject.domain.Administrator;
import com.example.swinedatebaseproject.domain.CommonUser;
import com.example.swinedatebaseproject.service.LoginService;
import com.example.swinedatebaseproject.util.JwtUtils;
import com.example.swinedatebaseproject.response.ResponseResult;
import com.example.swinedatebaseproject.response.ResponseResultCode;
import com.example.swinedatebaseproject.util.ResponseResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * @Author 123
 * @Date 2022/11/14
 */
@RestController
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    LoginService loginService;
    @PostMapping(value = "/user")
    public ResponseResult userLogin(@RequestBody CommonUser commonUser) {
        System.out.println(commonUser);
        if (loginService.userLogin(commonUser)) {
            String jwt = JwtUtils.createJwt("Login", Map.of("userName", commonUser.getName()));
            return ResponseResultUtils.getResponseResult(ResponseResultCode.LOGIN_SUCCESS.getCode(), ResponseResultCode.LOGIN_SUCCESS.getMessage(), "token", jwt);
        }else {
            return ResponseResult.error(ResponseResultCode.LOGIN_FAIL.getCode(), ResponseResultCode.LOGIN_FAIL.getMessage());
        }
    }
    @PostMapping(value = "/administrator")
    public ResponseResult administratorLogin(@RequestBody Administrator administrator) {
        if (loginService.administratorLogin(administrator)) {
            String jwt = JwtUtils.createJwt("Login", Map.of("userName", administrator.getName()));
            return ResponseResultUtils.getResponseResult(ResponseResultCode.LOGIN_SUCCESS.getCode(), ResponseResultCode.LOGIN_SUCCESS.getMessage(), "token", jwt);
        }else {
            return ResponseResult.error(ResponseResultCode.LOGIN_FAIL.getCode(), ResponseResultCode.LOGIN_FAIL.getMessage());
        }
    }
}
