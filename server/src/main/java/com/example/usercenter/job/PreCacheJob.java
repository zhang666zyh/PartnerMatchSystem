package com.example.usercenter.job;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.usercenter.model.domain.User;
import com.example.usercenter.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 缓存预热任务
 */
@Component
@Slf4j
public class PreCacheJob {
    @Resource
    private UserService userService;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    // 重点用户 -> 推荐
    private List<Long> mainUserList = Arrays.asList(1L);


    // 每天执行, 预热推荐用户
    @Scheduled(cron = "0 4 0 * * *")
    public void doCacheRecommendUser() {
        for (Long userId : mainUserList) {
            // TODO 查数据库
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            Page<User> userPage = userService.page(new Page<>(1, 20), queryWrapper);

            // TODO read to redis
            String redisKey = String.format("yupao:user:recommend:%s", userId);
            ValueOperations<String, Object> valueOptions = redisTemplate.opsForValue();

            // TODO write to redis
            try {
                valueOptions.set(redisKey, userPage, 30000, TimeUnit.MILLISECONDS);
            } catch (Exception e) {
                log.error("redis set key error", e);
            }
        }


    }
}
