package com.xzq.serviceOss.service;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UploadFileService {

    @ApiOperation(value = "上传教师头像接口")
    String uploadAvatarFile(MultipartFile file);
    @ApiOperation(value = "上传课程封面接口")
    String uploadCoverFile(MultipartFile file);
    @ApiOperation(value = "根据文件名删除指定照片")
    void deleteOssPhotoByFileName(String fileName);

    @ApiOperation(value = "批量删除照片")
    void deleteBatch(List<String> fileNames);
}
