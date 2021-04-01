package com.xzq.serviceEdu.client.impl;

import com.xzq.commonUtils.ResultMessage;
import com.xzq.serviceEdu.client.VodClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VodClientImpl implements VodClient {
    @Override
    public ResultMessage deleteVideoByVideoId(String videoId) {
        return ResultMessage.error().message("connect time out");
    }

    @Override
    public ResultMessage deleteBatch(List<String> videoIdList) {
        return ResultMessage.error().message("connect time out");
    }
}
