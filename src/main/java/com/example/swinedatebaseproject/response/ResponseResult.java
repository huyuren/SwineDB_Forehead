package com.example.swinedatebaseproject.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * &#064;Author
 * @Date  2022/11/14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseResult {
    private String code;
    private String message;
    private Object data;

    public static ResponseResult success() {
        return new ResponseResult(ResponseResultCode.SUCCESS.getCode(), ResponseResultCode.SUCCESS.getMessage(),null);
    }

    public static ResponseResult success(Object data) {
        return new ResponseResult(ResponseResultCode.SUCCESS.getCode(), ResponseResultCode.SUCCESS.getMessage(),data);
    }

    public static ResponseResult success(String code,String message) {
        return new ResponseResult(code,message,null);
    }

    public static ResponseResult success(String code, String message,Object data) {
        return new ResponseResult(code,message,data);
    }

    public static ResponseResult error(String code,String message) {
        return new ResponseResult(code,message,null);
    }
}
