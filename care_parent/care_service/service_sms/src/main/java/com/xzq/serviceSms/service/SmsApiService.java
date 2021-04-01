package com.xzq.serviceSms.service;

import java.util.Map;

public interface SmsApiService {
    /**
     * @Description: 发送短信接口
     * @Author xuzhiqiang
     * @Date 2021/3/10 16:44
     */
    boolean sendSms(String phone, String templateCode, Map<String, Object> param);
}
