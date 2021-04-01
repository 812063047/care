package com.xzq.serviceEdu.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xzq.commonUtils.ResultMessage;
import com.xzq.serviceEdu.entity.EduCourse;
import com.xzq.serviceEdu.entity.publish.CoursePublishVo;
import com.xzq.serviceEdu.entity.vo.CourseInfoVo;
import com.xzq.serviceEdu.query.EduCourseQuery;
import com.xzq.serviceEdu.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-01-28
 */
@Api("课程管理")
@RestController
@CrossOrigin
@RequestMapping("/eduservice/educourse")
public class EduCourseController {
    @Autowired
    private EduCourseService eduCourseService;

    @ApiOperation(value="添加课程的接口")
    @PostMapping("/addCourseInfo")
    public ResultMessage addCourseInfo(@ApiParam(name = "courseInfoVo", value = "课程基本信息", required = true)
            @RequestBody(required = true) CourseInfoVo courseInfoVo){
        String id =eduCourseService.saveCourseInfo(courseInfoVo);
        if(!StringUtils.isEmpty(id)) {
            return ResultMessage.ok().data("id",id);
        }else{
            return ResultMessage.error();
        }
    }
    @ApiOperation(value = "根据ID查询课程")
    @GetMapping("{id}")
    public ResultMessage getCourseInfoVoById(@ApiParam(name = "id", value = "课程ID", required = true)
        @PathVariable String id){
        CourseInfoVo courseInfoVo =eduCourseService.getCourseInfoVoById(id);
        return ResultMessage.ok().data("courseInfoVo",courseInfoVo);
    }

    @ApiOperation(value = "更新课程")
    @PostMapping("/updateCourseInfo")
    public ResultMessage updateCourseInfo(@ApiParam(name = "courseInfoVo", value = "课程基本信息", required = true)
                                              @RequestBody(required = true) CourseInfoVo courseInfoVo){
        String courseId = eduCourseService.updateCourseInfo(courseInfoVo);

        return ResultMessage.ok().data("courseId",courseId);

    }

    @ApiOperation(value = "根据ID获取课程发布信息")
    @GetMapping("coursePublishInfo/{id}")
    public ResultMessage getCoursePublishVoById( @ApiParam(name = "id", value = "课程ID", required = true)@PathVariable String id){
        CoursePublishVo coursePublishVo =eduCourseService.getCoursePublishVoById(id);
        return ResultMessage.ok().data("coursePublishVo",coursePublishVo);
    }


    @ApiOperation(value = "根据id发布课程")
    @PostMapping("publishCourse/{courseId}")
    public ResultMessage publishCourseById(@ApiParam(name = "courseId", value = "课程ID", required = true)@PathVariable String courseId){
        boolean blag = eduCourseService.publishCourseById(courseId);
        if(blag){
            return ResultMessage.ok();
        }else {
            return ResultMessage.error();
        }
    }

    @ApiOperation(value = "分页课程列表")
    @PostMapping("/{current}/{limit}")
    public  ResultMessage pageQueryCourse(@ApiParam(name = "current", value = "当前页", required = true)@PathVariable long current,
                                           @ApiParam(name = "limit", value = "每页记录数", required = true)@PathVariable long limit,
                                           @ApiParam(name = "eduCourseQuery",value = "查询对象", required = false)
                                           @RequestBody(required = false) EduCourseQuery eduCourseQuery){
        IPage<EduCourse> eduCourseIPage = eduCourseService.pageQueryCourse(current, limit, eduCourseQuery);
        List<EduCourse> records = eduCourseIPage.getRecords();
        long total = eduCourseIPage.getTotal();
        Map<String,Object> map = new HashMap<>();
        map.put("records",records);
        map.put("total",total);
        return ResultMessage.ok().data("map",map);
    }
    @ApiOperation(value = "根据ID删除课程")
    @DeleteMapping("{courseId}")
    public ResultMessage deleteByCourseId(@ApiParam(name = "courseId", value = "课程ID", required = true)
        @PathVariable String courseId){
        boolean blag =eduCourseService.deleteByCourseId(courseId);
        if(blag){
            return ResultMessage.ok();
        }else {
            return ResultMessage.error().message("删除失败");
        }
    }

    @ApiOperation(value = "批量删除课程")
    @DeleteMapping("/deleteBatch/{courseIds}")
    public ResultMessage deleteBatch(@ApiParam(name = "courseIds", value = "多个课程ID", required = true)
                                          @PathVariable String courseIds){
        boolean blag =eduCourseService.deleteBatch(courseIds);
        if(blag){
            return ResultMessage.ok();
        }else {
            return ResultMessage.error().message("删除失败");
        }
    }
}

