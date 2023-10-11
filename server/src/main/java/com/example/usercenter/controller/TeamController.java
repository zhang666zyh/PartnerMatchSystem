package com.example.usercenter.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.usercenter.common.BaseResponse;
import com.example.usercenter.common.ErrorCode;
import com.example.usercenter.exception.BusinessException;
import com.example.usercenter.model.domain.Team;
import com.example.usercenter.model.domain.User;
import com.example.usercenter.model.domain.UserTeam;
import com.example.usercenter.model.dto.TeamQuery;
import com.example.usercenter.model.request.TeamAddRequest;
import com.example.usercenter.model.request.TeamJoinRequest;
import com.example.usercenter.model.request.TeamQuitRequest;
import com.example.usercenter.model.request.TeamUpdateRequest;
import com.example.usercenter.model.vo.TeamUserVO;
import com.example.usercenter.service.TeamService;
import com.example.usercenter.service.UserService;
import com.example.usercenter.service.UserTeamService;
import com.example.usercenter.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 队伍接口
 */
@RestController
@RequestMapping("/team")
@CrossOrigin(origins = {"http://localhost:5173"}, allowCredentials = "true")
@Slf4j
public class TeamController {

    @Resource
    private UserService userService;

    @Resource
    private TeamService teamService;

    @Resource
    private UserTeamService userTeamService;


    @PostMapping("/add")
    public BaseResponse<Long> addTeam(@RequestBody TeamAddRequest teamAddRequest, HttpServletRequest httpServletRequest) {
        if (teamAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        User loginUser = userService.getLoginUser(httpServletRequest);

        Team team = new Team();
        BeanUtils.copyProperties(teamAddRequest, team);

        long teamId = teamService.addTeam(team, loginUser);
        return ResultUtils.success(teamId);
    }

    @GetMapping("/get")
    public BaseResponse<Team> getTeamById(long id) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        Team team = teamService.getById(id);
        if (team == null) {
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }

        return ResultUtils.success(team);
    }

    @PostMapping("/update")
    public BaseResponse<Boolean> updateTeam(@RequestBody TeamUpdateRequest teamUpdateRequest, HttpServletRequest httpServletRequest) {
        if (teamUpdateRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        User loginUser = userService.getLoginUser(httpServletRequest);
        boolean result = teamService.updateTeam(teamUpdateRequest, loginUser);

        return ResultUtils.success(result);
    }

    @GetMapping("/list")
    public BaseResponse<List<TeamUserVO>> listTeams(TeamQuery teamQuery, HttpServletRequest httpServletRequest) {
        if (teamQuery == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        boolean isAdmin = userService.isAdmin(httpServletRequest);
        List<TeamUserVO> teamList = teamService.listTeams(teamQuery, isAdmin);

        return ResultUtils.success(teamList);
    }

    /**
     * 获取我创建的队伍
     *
     * @param teamQuery
     * @param httpServletRequest
     * @return
     */
    @GetMapping("/list/mycreate")
    public BaseResponse<List<TeamUserVO>> listMyCreateTeams(TeamQuery teamQuery, HttpServletRequest httpServletRequest) {
        if (teamQuery == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        User loginUser = userService.getLoginUser(httpServletRequest);
        teamQuery.setUserId(loginUser.getId());
        List<TeamUserVO> teamList = teamService.listTeams(teamQuery, true);

        return ResultUtils.success(teamList);
    }

    /**
     * 获取我加入的队伍
     *
     * @param teamQuery
     * @param httpServletRequest
     * @return
     */
    @GetMapping("/list/myjoin")
    public BaseResponse<List<TeamUserVO>> listMyJoinTeams(TeamQuery teamQuery, HttpServletRequest httpServletRequest) {
        if (teamQuery == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        User loginUser = userService.getLoginUser(httpServletRequest);

        /**
         * 查询操作应该写在service里, 这里偷懒了就直接写在这了
         */
        QueryWrapper<UserTeam> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userId", loginUser.getId());
        List<UserTeam> userTeamList = userTeamService.list(queryWrapper);

        // 取出不重复的队伍id
        Map<Long, List<UserTeam>> listlMap = userTeamList.stream()
                .collect(Collectors.groupingBy(UserTeam::getTeamId));
        List<Long> idList = new ArrayList<>(listlMap.keySet());
        teamQuery.setIdList(idList);

        List<TeamUserVO> teamList = teamService.listTeams(teamQuery, true);

        return ResultUtils.success(teamList);
    }

    @GetMapping("/list/page")
    public BaseResponse<Page<Team>> listTeamsByPage(TeamQuery teamQuery) {
        if (teamQuery == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        Team team = new Team();
        BeanUtils.copyProperties(team, teamQuery);

        Page<Team> page = new Page<>(teamQuery.getPageNum(), teamQuery.getPageSize());
        QueryWrapper<Team> queryWrapper = new QueryWrapper<>(team);

        Page<Team> pageRes = teamService.page(page, queryWrapper);
        return ResultUtils.success(pageRes);
    }

    @PostMapping("/join")
    public BaseResponse<Boolean> joinTeam(@RequestBody TeamJoinRequest teamJoinRequest, HttpServletRequest httpServletRequest) {
        if (teamJoinRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        User loginUser = userService.getLoginUser(httpServletRequest);
        boolean result = teamService.joinTeam(teamJoinRequest, loginUser);
        return ResultUtils.success(result);
    }

    @PostMapping("/quit")
    public BaseResponse<Boolean> quitTeam(@RequestBody TeamQuitRequest teamQuitRequest, HttpServletRequest httpServletRequest) {
        if (teamQuitRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        User loginUser = userService.getLoginUser(httpServletRequest);
        boolean result = teamService.quitTeam(teamQuitRequest, loginUser);
        return ResultUtils.success(result);
    }

    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteTeam(@RequestBody Long id, HttpServletRequest httpServletRequest) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        User loginUser = userService.getLoginUser(httpServletRequest);
        boolean result = teamService.deleteTeam(id, loginUser);
        if (!result) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "删除失败");
        }

        return ResultUtils.success(result);
    }


}
