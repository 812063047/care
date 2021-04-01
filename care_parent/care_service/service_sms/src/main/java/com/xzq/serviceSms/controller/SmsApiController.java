package com.xzq.serviceSms.controller;

import com.xzq.commonUtils.ResultMessage;
import com.xzq.serviceSms.service.SmsApiService;
import com.xzq.serviceSms.utils.RandomUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@CrossOrigin
@RequestMapping("/smsservice/smsapi")
public class SmsApiController {
    @Autowired
    private SmsApiService smsApiService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @GetMapping("/send/{phone}")
    public ResultMessage code(@PathVariable String phone){
        String code = redisTemplate.opsForValue().get("phone");
        if(!StringUtils.isEmpty(code)){
            return  ResultMessage.ok().message("验证码还没有过期");
        }
        code = RandomUtil.getSixBitRandom();
        Map<String,Object> param = new HashMap<>();
        param.put("content", code);
        param.put("address", "yellow 你信吗？");
        String templateCode = "SMS_205617106";
        boolean isSend = smsApiService.sendSms(phone,templateCode,param);
        if(isSend){
            redisTemplate.opsForValue().set("phone",code,1, TimeUnit.MINUTES);
            return ResultMessage.ok();
        }else{
            return ResultMessage.error().message("发送短信失败");
        }

    }

}
