package com.example.usercenter.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.usercenter.mapper.UserTeamMapper;
import com.example.usercenter.model.domain.UserTeam;
import com.example.usercenter.service.UserTeamService;
import org.springframework.stereotype.Service;

/**
 * @author 张宇航
 * @description 针对表【user_team(用户队伍关系)】的数据库操作Service实现
 * @createDate 2023-10-06 17:08:52
 */
@Service
public class UserTeamServiceImpl extends ServiceImpl<UserTeamMapper, UserTeam>
        implements UserTeamService {

}




