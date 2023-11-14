package com.example.swinedatebaseproject.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author 132
 * @Date  2022/11/14
 */
@Getter
@AllArgsConstructor
@SuppressWarnings("all")
public enum ResponseResultCode {
    SUCCESS("A0001", "请求成功"),
    DATA_NOT_FOUND("A0002", "目标数据未找到"),
    DELETE_SUCCESS("A0003", "数据删除成功"),
    DELETE_FAIL("A0004", "数据删除失败"),
    ADD_SUCESS("A0005", "数据添加成功"),
    ADD_FAIL("A0006", "数据添加失败"),
    FILE_TYPE_ERROR("A0007", "文件类型错误"),

    DATA_TYPE_ERROR("A0008", "数据类型错误"),
    UPDATE_DATA_FAIL("A0009", "数据更新失败"),
    LOGIN_SUCCESS("B0001", "登录成功"),
    LOGIN_FAIL("B0002", "登录失败"),
    REGISTER_SUCCESS("B0003", "注册成功"),
    REGISTER_FAIL("B0004", "注册失败"),
    NOT_REGISTER("B0005", "未注册"),
    NOT_LOGIN("B0006", "未登录"),
    AUTHEMTICATION_FAIL("B0007", "认证失败，请重新登录");


    private final String code;
    private final String message;
}

