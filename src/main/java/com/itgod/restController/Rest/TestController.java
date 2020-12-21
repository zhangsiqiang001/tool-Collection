package com.itgod.restController.Rest;

import com.itgod.restController.Myannotation.Firstcut;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * date: 2020/2/17 19:23
 * author: zhangsq
 * 任务：redis分布式锁，注解开发
 * version: 1.0
 */
@RestController
@RequestMapping
public class TestController {
    @Firstcut(Value = "hello")
    @GetMapping("/backAliFace")
    public String backAliFace(@RequestParam(required = false) String redirectUrl) {
        return "null";
    }

    @GetMapping("/getAliDetectInfo")
    public Boolean getAliDetectInfo(@RequestParam String bizToken) {
        return null;
    }
}
