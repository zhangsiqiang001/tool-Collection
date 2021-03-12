package com.itgod.restController.Rest;

import com.itgod.restController.Myannotation.Firstcut;
import com.itgod.restController.entity.DoermeiUserInfoParam;
import com.itgod.restController.entity.testentity;
import com.itgod.restController.util.SignatureVerifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.net.InetAddress;

/**
 * date: 2020/2/17 19:23
 * author: zhangsq
 * 任务：redis分布式锁，注解开发
 * version: 1.0
 */
@RestController
@RequestMapping
public class TestController {

    @Resource
    private SignatureVerifier signatureVerifier;

    @Firstcut(Value = "hello")
    @GetMapping("/backAliFace")
    public String backAliFace(@RequestParam(required = false) String redirectUrl) {
        return "null";
    }

    @GetMapping("/get")
    public String getAliDetectInfo() throws Exception {
        InetAddress addr = InetAddress.getLocalHost();
        return addr.getHostName().toString();
    }

    @GetMapping("/passwordtest")
    public String passwordtest() {
        testentity testentity = new testentity();
        testentity.setBirthday("2020-12-12");
        testentity.setCardNum("111111111111111111");
        testentity.setCardTypeCode("1");
        testentity.setDescription("救死扶伤");
        testentity.setGender("1");
        testentity.setJobNum("666666");
        testentity.setName("章鱼");
        testentity.setNickname("神");
        testentity.setOrgCode("11111");
        testentity.setPhone("13222222288");
        testentity.setPortrait("http://aaa.jpgpositional");
        testentity.setFirstCode("第一职称");
        testentity.setPositionalSecondCode("第二职称");
        testentity.setSkillful("简介简介");
        testentity.setTimestamp("111111111");
        testentity.setUpdateTime("999999999");
        String s = signatureVerifier.verifySignature(testentity);
        return s;
    }

    @GetMapping("/entiytest")
    public String entiytest() {
        long timeNew = System.currentTimeMillis();
        DoermeiUserInfoParam doermeiUserInfoParam = new DoermeiUserInfoParam();
        doermeiUserInfoParam.setToken("2500860ce7fef0285b6b32c86551d2f2");
        doermeiUserInfoParam.setTimestamp(timeNew);
        String s = signatureVerifier.verifySignature(doermeiUserInfoParam);
        System.out.println(s);
        System.out.println(timeNew);
        return s;
    }

    public static void main(String[] args) {

    }
}
