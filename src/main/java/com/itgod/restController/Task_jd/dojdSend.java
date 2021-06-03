package com.itgod.restController.Task_jd;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhangsq
 * @date 2021/5/31 11:07
 */
@Component
public class dojdSend {


    @Scheduled(cron = "* 0 12 * * ? ")
    public void dojd_send() {
        RestTemplate restTemplate = new RestTemplate();
        String content = JSON.toJSONString(null);
        HttpHeaders headers = new HttpHeaders();
        String xSignature = null;
        headers.set("X-Signature", xSignature);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Accept", "*/*");
        headers.set("Accept-Encoding", "gzip, deflate, br");
        headers.set("Connection", "keep-alive");
        HttpEntity<String> httpEntity = new HttpEntity<>(content, headers);
        ResponseEntity<String> exchange = restTemplate.exchange(null, HttpMethod.POST, httpEntity, String.class);
        JSONObject jsonObject = JSONObject.parseObject(exchange.getBody());
        String code = jsonObject.getString("code");
        if (!"0".equals(code)) {

        }
        // restTemplate.exchange();
        System.out.println("定时好了");
    }
}
