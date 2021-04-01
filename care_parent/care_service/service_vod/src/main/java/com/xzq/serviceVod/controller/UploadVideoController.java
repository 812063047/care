package com.xzq.serviceVod.controller;

import com.xzq.commonUtils.ResultMessage;
import com.xzq.serviceVod.service.VodService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Api("阿里云视频点播微服务")
@CrossOrigin
@RestController
@RequestMapping("vodservice/uploadvideo")
public class UploadVideoController {
    @Autowired
    private VodService vodService;


    @ApiOperation(value = "上传视频到VOD")
    @PostMapping("upload")
    public ResultMessage UploadVideoVod(@ApiParam(name = "file", value = "文件", required = true)
        @RequestParam("file")MultipartFile file){
        String videoId = vodService.UploadVideoVod(file);
        return ResultMessage.ok().data("videoId",videoId);
    }

    @DeleteMapping("{videoId}")
    public ResultMessage deleteViderByVideoId(@ApiParam(name = "videoId", value = "云端视频id", required = true)
        @PathVariable String videoId){
        vodService.deleteVideoByVideoId(videoId);
        return ResultMessage.ok().message("删除成功");
    }

    @DeleteMapping("deleteBatch")
    public  ResultMessage deleteBatch(@ApiParam(name = "videoIdList", value = "云端视频id", required = true)
                                          @RequestParam("videoIdList") List<String> videoIdList){
        vodService.deleteVideoList(videoIdList);
        return ResultMessage.ok().message("视频删除成功");
    }
}
