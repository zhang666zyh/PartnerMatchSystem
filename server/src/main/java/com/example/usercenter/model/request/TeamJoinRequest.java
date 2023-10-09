package com.example.usercenter.model.request;


import lombok.Data;

@Data
public class TeamJoinRequest {

    /**
     * teamId
     */
    private Long teamId;

    /**
     * 密码
     */
    private String password;

}
