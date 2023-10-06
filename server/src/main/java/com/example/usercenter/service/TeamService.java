package com.example.usercenter.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.usercenter.model.domain.Team;
import com.example.usercenter.model.domain.User;
import com.example.usercenter.model.dto.TeamQuery;
import com.example.usercenter.model.vo.TeamUserVO;

import java.util.List;

/**
* @author 张宇航
* @description 针对表【team(队伍)】的数据库操作Service
* @createDate 2023-10-06 17:05:32
*/
public interface TeamService extends IService<Team> {
    /**
     * 创建队伍
     * @param team
     * @param loginUser
     * @return
     */
    long addTeam(Team team, User loginUser);

    /**
     * 搜索队伍
     * @param teamQuery
     * @return
     */
    List<TeamUserVO> listTeams(TeamQuery teamQuery);
}
