package com.example.usercenter.once;

import java.util.Date;

import com.example.usercenter.mapper.UserMapper;
import com.example.usercenter.model.domain.User;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;

@Component
public class InsertUsers {
    @Resource
    private UserMapper userMapper;


    /**
     * mock
     * 批量插入用户
     */
//    @Scheduled(fixedDelay = 5000, fixedRate = Long.MAX_VALUE)
    public void doInsertUsers() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        final int INSERT_NUM = 1000;
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
        }

        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());
    }
}
