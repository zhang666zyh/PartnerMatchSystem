package com.example.usercenter;

import com.example.usercenter.model.domain.User;
import com.example.usercenter.service.UserService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class UsercenterApplicationTests {
    @Resource
    private UserService userService;

    @Test
    void contextLoads() {
        List<String> tagNameList = Arrays.asList("java", "python");
        List<User> userList = userService.searchUserByTags(tagNameList);
        Assert.assertNotNull(userList);
    }

}
