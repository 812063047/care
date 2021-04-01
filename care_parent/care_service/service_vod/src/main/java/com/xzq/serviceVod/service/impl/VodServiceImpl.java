package com.xzq.serviceVod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.xzq.serviceBase.Exception.CareException;
import com.xzq.serviceVod.service.VodService;
import com.xzq.serviceVod.utils.AliyunVodSDKUtils;
import com.xzq.serviceVod.utils.ConstantPropertiesUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class VodServiceImpl implements VodService {
    @Override
    public String UploadVideoVod(MultipartFile file) {
        String videoId = "";
        try {
            String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
            String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
            InputStream inputStream = file.getInputStream();
            String fileName = file.getOriginalFilename();
            String title = fileName.substring(0,fileName.indexOf("."));
            UploadStreamRequest request = new UploadStreamRequest(accessKeyId, accessKeySecret, title, fileName, inputStream);
            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);
            videoId = response.getVideoId();//请求视频点播服务的请求ID
            if (!response.isSuccess()) {
                String errorMessage = "阿里云上传错误：" + "code：" + response.getCode() + ", message：" + response.getMessage();
                if(StringUtils.isEmpty(videoId)){
                    throw new CareException(20001, errorMessage);
                }
            }
        } catch (IOException e) {
            throw new CareException(20001, "上传视频失败");
        }
        return videoId;
    }

    @Override
    public void deleteVideoByVideoId(String videoId) {
        String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
        DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(accessKeyId, accessKeySecret);
        DeleteVideoRequest  request = new DeleteVideoRequest();
        DeleteVideoResponse response = new DeleteVideoResponse();
        request.setVideoIds(videoId);
        try {
            client.getAcsResponse(request);

        } catch (ClientException e) {
            throw new CareException(20001, "删除视频失败");
        }

    }

    @Override
    public void deleteVideoList(List<String> videoIdList) {
        String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
        DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(accessKeyId, accessKeySecret);
        String videoIds = StringUtils.join(videoIdList.toArray(),",");
        DeleteVideoRequest  request = new DeleteVideoRequest();
        DeleteVideoResponse response = new DeleteVideoResponse();
        request.setVideoIds(videoIds);
        try {
           client.getAcsResponse(request);
        } catch (ClientException e) {
            throw new CareException(20001, "删除视频失败");
        }
    }
}
