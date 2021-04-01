package com.xzq.serviceOss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.DeleteObjectsRequest;
import com.xzq.serviceBase.Exception.CareException;
import com.xzq.serviceOss.service.UploadFileService;
import com.xzq.serviceOss.utils.ConstantPropertiesUtil;
import com.xzq.serviceOss.utils.InitOssUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UploadFileServiceImpl implements UploadFileService {

    /**
     * @Description: 头像上传功能
     * @Author xuzhiqiang
     * @Date 2021/1/26 17:06
     */
    @Override
    public String uploadAvatarFile(MultipartFile file) {
        OSS ossClient = InitOssUtils.initOss();
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;
        String endpoint = ConstantPropertiesUtil.END_POINT;
        String url ="";
        try{
            InputStream inputStream = file.getInputStream();
            // <yourObjectName>上传文件到OSS时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg。
            String fileName = file.getOriginalFilename();

            String s = UUID.randomUUID().toString().replaceAll("-","");
            fileName = s+fileName;
            String str =new DateTime().toString("yyyy/MM/dd");
            fileName = "teacherAvatar/"+str+"/"+fileName;


            ossClient.putObject(bucketName, fileName, inputStream);
            StringBuilder urlBuilder = new StringBuilder();
            urlBuilder.append("https://").append(bucketName).append(".").append(endpoint).append("/").append(fileName);
            url = urlBuilder.toString();

            ossClient.shutdown();
        }catch (Exception e){
            e.printStackTrace();
        }
        return url;
    }

    /**
     * @Description: 上传课程封面
     * @Author xuzhiqiang
     * @Date 2021/3/3 16:00
     */
    @Override
    public String uploadCoverFile(MultipartFile file) {
        OSS ossClient = InitOssUtils.initOss();
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;
        String endpoint = ConstantPropertiesUtil.END_POINT;
        String url ="";
        try{
            InputStream inputStream = file.getInputStream();
            // <yourObjectName>上传文件到OSS时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg。
            String fileName = file.getOriginalFilename();

            String s = UUID.randomUUID().toString().replaceAll("-","");
            fileName = s+fileName;
            String str =new DateTime().toString("yyyy/MM/dd");
            fileName = "courseCover/"+str+"/"+fileName;

            ossClient.putObject(bucketName, fileName, inputStream);
            StringBuilder urlBuilder = new StringBuilder();
            urlBuilder.append("https://").append(bucketName).append(".").append(endpoint).append("/").append(fileName);
            url = urlBuilder.toString();

            ossClient.shutdown();
        }catch (Exception e){
            e.printStackTrace();
        }
        return url;
    }

    @Override
    public void deleteOssPhotoByFileName(String fileNameStr) {
        try{
            OSS ossClient = InitOssUtils.initOss();
            String bucketName = ConstantPropertiesUtil.BUCKET_NAME;
            String fileName = fileNameStr.replaceAll("-", "/");
            ossClient.deleteObject(bucketName,fileName);
            ossClient.shutdown();
        }catch (Exception e){
            throw new CareException(20001,"删除失败");
        }
    }

    @Override
    public void deleteBatch(List<String> fileNames) {
        OSS ossClient = InitOssUtils.initOss();
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;
        List<String> fileNameList = new ArrayList<>();
        for (String fileNameStr :
                fileNames) {
            String fileName = fileNameStr.replaceAll("-", "/");
            fileNameList.add(fileName);
        }
        ossClient.deleteObjects(new DeleteObjectsRequest(bucketName).withKeys(fileNameList));
        ossClient.shutdown();
    }
}
