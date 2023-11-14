package com.example.swinedatebaseproject.controller.user;

import com.example.swinedatebaseproject.domain.CommonUser;
import com.example.swinedatebaseproject.service.RegisterService;
import com.example.swinedatebaseproject.response.ResponseResult;
import com.example.swinedatebaseproject.response.ResponseResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 123
 * @Date 2022/11/14
 */
@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    RegisterService registerService;

    @PostMapping("/user")
    public ResponseResult userLogin(@RequestBody CommonUser commonUser) {
        if (registerService.userRegister(commonUser)) {
            return ResponseResult.success(ResponseResultCode.REGISTER_SUCCESS.getCode(), ResponseResultCode.REGISTER_SUCCESS.getMessage());
        }else {
            return ResponseResult.error(ResponseResultCode.REGISTER_FAIL.getCode(), ResponseResultCode.REGISTER_FAIL.getMessage());
        }
    }
//    @PostMapping("/administrator")
//    public ResponseResult administratorLogin(@RequestBody Administrator administrator) {
//        if (registerService.administratorRegister(administrator)) {
//            return ResponseResult.success(ResponseResultCode.REGISTER_SUCCESS.getCode(), ResponseResultCode.REGISTER_SUCCESS.getMessage());
//        }else {
//            return ResponseResult.error(ResponseResultCode.REGISTER_FAIL.getCode(), ResponseResultCode.REGISTER_FAIL.getMessage());
//        }
//    }

}
