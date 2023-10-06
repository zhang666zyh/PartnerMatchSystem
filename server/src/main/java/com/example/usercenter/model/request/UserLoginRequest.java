package com.example.usercenter.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户注册请求体
 */
@Data
public class UserLoginRequest implements Serializable {
    private static final long serialVesionUID = 1L;

    private String userAccount;
    private String userPassword;
    private String checkPassword;
}
