package com.example.usercenter.model.request;


import lombok.Data;

/**
 * 用户退出队伍请求体
 */
@Data
public class TeamQuitRequest {

    /**
     * teamId
     */
    private Long teamId;

}
