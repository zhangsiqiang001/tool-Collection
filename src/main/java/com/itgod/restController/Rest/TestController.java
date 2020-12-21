package com.itgod.restController.Rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * date: 2020/2/17 19:23
 * author: zhangsq
 * version: 1.0
 */
@RestController
@RequestMapping
public class TestController {

    @GetMapping("/backAliFace")
    public String backAliFace(@RequestParam String redirectUrl) {
        return null;
    }

    @GetMapping("/getAliDetectInfo")
    public Boolean getAliDetectInfo(@RequestParam String bizToken) {
        return null;
    }
}
