package com.xzq.serviceEdu.client.impl;

import com.xzq.commonUtils.ResultMessage;
import com.xzq.serviceEdu.client.OssClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OssClientImpl implements OssClient {
    @Override
    public ResultMessage deleteOssPhotoByFileName(String fileName) {
        return ResultMessage.error().message("connect time out");
    }

    @Override
    public ResultMessage deleteBatch(List<String> fileNames) {
        return ResultMessage.error().message("connect time out");
    }
}
