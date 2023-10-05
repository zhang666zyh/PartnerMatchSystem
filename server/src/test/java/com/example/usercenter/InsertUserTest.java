package com.example.usercenter;


import com.example.usercenter.mapper.UserMapper;
import com.example.usercenter.model.domain.User;
import com.example.usercenter.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

// 加启动类类名, 不然会报错
@SpringBootTest(classes = {UsercenterApplication.class})
public class InsertUserTest {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserService userService;

    @Test
    void doInsertUsers() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        final int INSERT_NUM = 1000;
        List<User> userList = new ArrayList<>();

        for (int i = 0; i < INSERT_NUM; i++) {
            User user = new User();
            user.setUsername("mock用户" + i);
            user.setUserAccount("mock用户" + i);
            user.setAvatarUrl("http://usercenter.zhangyuhang.games/img/avatar.png");
            user.setGender(0);
            user.setEmail("32389224u@qq.com");
            user.setPhone("12345555");
            user.setUserPassword("12345678");
            user.setTags("[]");
            user.setUserStatus(0);
            user.setIsDelete(0);
            user.setUserRole(0);
            user.setPlanetCode("111111");

            // userMapper.insert(user);

            userList.add(user);
        }

        userService.saveBatch(userList, 100);


        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());
    }

}