package com.xzq.serviceOss.controller;

import com.xzq.commonUtils.ResultMessage;
import com.xzq.serviceOss.service.UploadFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin
@Api("OSS接口")
@RequestMapping("ossservice/uploadfile")
public class UploadFileController {
    @Autowired
    private UploadFileService uploadFileService;


    @ApiOperation(value = "上传头像接口")
    @PostMapping("/uploadAvatar")
    public ResultMessage uploadAvatarFile(@ApiParam(name = "file", value = "文件", required = true)
        @RequestParam("file")MultipartFile file){
        String url = uploadFileService.uploadAvatarFile(file);
        return ResultMessage.ok().data("url",url);
    }

    @ApiOperation(value = "上传课程封面")
    @PostMapping("/uploadCover")
    public ResultMessage uploadCoverFile(@ApiParam(name = "file", value = "文件", required = true)
                                    @RequestParam("file")MultipartFile file){
        String url = uploadFileService.uploadCoverFile(file);
        return ResultMessage.ok().data("url",url);
    }

    @ApiOperation(value = "删除照片")
    @DeleteMapping("{fileName}")
    public ResultMessage deleteOssPhotoByFileName(@ApiParam(name = "fileName", value = "文件名字", required = true)
                                                      @PathVariable String fileName){
        uploadFileService.deleteOssPhotoByFileName(fileName);
        return  ResultMessage.ok().message("删除成功");
    }

    @ApiOperation(value = "批量删除照片")
    @DeleteMapping("deleteBatch")
    public ResultMessage deleteBatch(@ApiParam(name = "fileNames", value = "多个文件名字", required = true)
                                     @RequestParam(value = "fileNames")List<String> fileNames){
        uploadFileService.deleteBatch(fileNames);
        return ResultMessage.ok().message("批量删除照片成功");
    }

}
