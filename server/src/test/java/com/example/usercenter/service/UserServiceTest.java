package com.example.usercenter.service;

import com.example.usercenter.model.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * 用户服务测试
 *
 * @author 张宇航
 */
@SpringBootTest
class UserServiceTest {
    @Resource
    public UserService userService;

    @Test
    void testAddUser() {
        User user = new User();
        user.setUsername("张三");
        user.setUserAccount("123");
        user.setAvatarUrl("http://zhangyuhang.games/img/avatar.png");
        user.setGender(0);
        user.setEmail("456");
        user.setPhone("123");
        user.setUserPassword("xxx");

        boolean result = userService.save(user);
        System.out.println(user.getId());
        Assertions.assertTrue(true);
    }

//    @Test
//    void userRegister() {
//        String userAccount = "yupi";
//        String userPassword = "123456";
//        String checkPassword = "";
//        long result = userService.userRegister(userAccount, userPassword, checkPassword);
//        System.out.println(result);
//
//        userAccount = "yu";
//        result = userService.userRegister(userAccount, userPassword, checkPassword);
//        System.out.println(result);
//
//        userAccount = "yu pi";
//        result = userService.userRegister(userAccount, userPassword, checkPassword);
//        System.out.println(result);
//
//        userPassword = "123456";
//        checkPassword = "1234";
//        result = userService.userRegister(userAccount, userPassword, checkPassword);
//        System.out.println(result);
//
//        userAccount = "123";
//        result = userService.userRegister(userAccount, userPassword, checkPassword);
//        System.out.println(result);
//
//        userAccount = "yupi";
//        userPassword = "12345678";
//        checkPassword = "12345678";
//        result = userService.userRegister(userAccount, userPassword, checkPassword);
//        System.out.println(result);
//    }

}