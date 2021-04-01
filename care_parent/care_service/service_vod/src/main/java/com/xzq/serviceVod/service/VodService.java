package com.xzq.serviceVod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VodService {

    /**
     * @Description: 上传视频
     * @Author xuzhiqiang
     * @Date 2021/3/2 15:34
     */
    public String UploadVideoVod(MultipartFile file);

    /**
     * @Description: 删除阿里云的视频
     * @Author xuzhiqiang
     * @Date 2021/3/2 16:51
     */
    void deleteVideoByVideoId(String videoId);

    /**
     * @Description: 删除多个视频
     * @Author xuzhiqiang
     * @Date 2021/3/3 14:18
     */
    void deleteVideoList(List<String> videoIdList);
}
