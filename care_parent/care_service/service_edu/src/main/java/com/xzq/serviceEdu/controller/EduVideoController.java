package com.xzq.serviceEdu.controller;


import com.xzq.commonUtils.ResultMessage;
import com.xzq.serviceEdu.entity.video.VideoInfoVo;
import com.xzq.serviceEdu.service.EduVideoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-01-28
 */
@RestController
@CrossOrigin
@RequestMapping("/eduservice/eduvideo")
public class EduVideoController {

    @Autowired
    private EduVideoService eduVideoService;

    @ApiOperation(value = "添加课时")
    @PostMapping
    public ResultMessage addVideo(@ApiParam(name = "videoInfoVo", value = "课时对象", required = true)
        @RequestBody VideoInfoVo videoInfoVo){
        boolean save = eduVideoService.saveVideoInfo(videoInfoVo);
        if(save) {
            return ResultMessage.ok();
        }else{
            return  ResultMessage.error();
        }
    }
    @ApiOperation(value = "根据ID查询课时")
    @GetMapping("{id}")
    public ResultMessage getVideoInfoById(@ApiParam(name = "id", value = "课时ID", required = true)
        @PathVariable String id){
        VideoInfoVo videoInfoVo = eduVideoService.getVideoInfoById(id);
        return ResultMessage.ok().data("videoInfoVo",videoInfoVo);
    }

    @ApiOperation(value = "更新课时")
    @PostMapping("updateVideoInfo")
    public ResultMessage updateVideoInfo(@ApiParam(name = "videoInfoVo", value = "课时对象", required = true)
                                         @RequestBody VideoInfoVo videoInfoVo){
        boolean result =eduVideoService.updateVideoInfo(videoInfoVo);
        if(result) {
            return ResultMessage.ok();
        }else{
            return  ResultMessage.error();
        }
    }

    @ApiOperation(value = "根据ID删除课时")
    @DeleteMapping("{id}")
    public ResultMessage deleteVideoInfoById(
        @ApiParam(name = "id", value = "课时ID", required = true)
        @PathVariable String id){
        boolean result = eduVideoService.removeVideoById(id);
        if(result){
            return ResultMessage.ok();
        }else {

            return ResultMessage.error().message("删除失败");

        }
    }
}

