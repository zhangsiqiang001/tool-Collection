package com.itgod.restController.Rest;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class TestRedis {

    @Resource
    RedisTemplate redisTemplate;

    public void getsetredis() {
        new Thread();
        redisTemplate.opsForValue().set("hello", "xiaoli");
        System.out.println(redisTemplate.opsForValue().get("hello"));
    }



}
