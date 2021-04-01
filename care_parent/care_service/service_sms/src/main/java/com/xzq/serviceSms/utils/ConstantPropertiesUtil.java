package com.xzq.serviceSms.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConstantPropertiesUtil implements InitializingBean {
    @Value("${aliyun.sms.file.keyid}")
    private String keyId;

    @Value("${aliyun.sms.file.keysecret}")
    private String keySecret;



    public static String ACCESS_KEY_ID;

    public static String ACCESS_KEY_SECRET;


    @Override
    public void afterPropertiesSet() throws Exception {
        ACCESS_KEY_ID = keyId;
        ACCESS_KEY_SECRET = keySecret;
    }
}
