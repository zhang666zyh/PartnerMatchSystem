package com.example.usercenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.usercenter.model.domain.User;
import com.example.usercenter.model.request.UserRegisterRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 张宇航
 * @description 针对表【user】的数据库操作Service
 * @createDate 2023-09-11 09:58:53
 */
public interface UserService extends IService<User> {


    /**
     * 用户注册
     *
     * @return 新用户id
     */
    long userRegister(UserRegisterRequest userRegisterRequest);


    /**
     * @param userAccount  用户账户
     * @param userPassword 用户密码
     * @return 脱敏后的用户信息
     */
    User userLogin(String userAccount, String userPassword, HttpServletRequest request);

    // 返回用户脱敏
    User getSafetyUser(User originUser);


    // 用户注销
    int userLogout(HttpServletRequest request);

    /**
     * 根据标签搜索用户
     *
     * @param tagNameList 用户要拥有的标签
     * @return
     */
    List<User> searchUserByTags(List<String> tagNameList);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    int updateUser(User user, User loginUser);

    /**
     * 获取当前登录用户
     * @param httpServletRequest
     * @return
     */
    User getLoginUser(HttpServletRequest httpServletRequest);

    /**
     * 判断是否是管理员
     * @param loginUser
     * @return
     */
    boolean isAdmin(User loginUser);
    boolean isAdmin(HttpServletRequest request);
}
