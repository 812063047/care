package com.xzq.serviceEdu.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xzq.commonUtils.ResultMessage;
import com.xzq.serviceBase.Exception.CareException;
import com.xzq.serviceEdu.client.OssClient;
import com.xzq.serviceEdu.entity.EduTeacher;
import com.xzq.serviceEdu.query.EduTeacherQuery;
import com.xzq.serviceEdu.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-01-20
 */
@Api("讲师管理")
@RestController
@RequestMapping("/eduservice/eduteacher")
//解决跨域问题
@CrossOrigin
public class EduTeacherController {
//访问地址是localhost:8081/serviceEdu/findAll
    @Autowired
    private EduTeacherService eduTeacherService;

    @Autowired
    private OssClient ossClient;

    @ApiOperation(value = "所有讲师列表")
    @GetMapping("/findAll")
    public ResultMessage getAllEduTeacher(){
        List<EduTeacher> eduTeachers = eduTeacherService.list(null);
        return ResultMessage.ok().data("item",eduTeachers);
    }

    @ApiOperation(value = "根据ID删除讲师")
    @DeleteMapping("{id}")
    public  ResultMessage deleteById(@ApiParam(name = "id", value = "讲师ID", required = true) @PathVariable String id){
        //根据讲师ID获取头像地址
        EduTeacher eduTeacher = eduTeacherService.getById(id);
        String avatar = eduTeacher.getAvatar();
        if(!StringUtils.isEmpty(avatar)) {
            String fileNameStr = avatar.substring(47);
            String fileName = fileNameStr.replaceAll("/", "-");
            //判断是否是默认封面
            if (!fileName.contains("default")) {
                //调用微服务框架OSS
                ResultMessage resultMessage = ossClient.deleteOssPhotoByFileName(fileName);
                if (resultMessage.getCode() == 20001) {
                    throw new CareException(20001, "删除头像失败");
                }
            }
        }
        boolean b = eduTeacherService.removeById(id);
        if(b){
            return ResultMessage.ok();
        }else {
            return ResultMessage.error();
        }
    }

    @ApiOperation(value = "分页查询讲师列表")
    @GetMapping("pageTeacher/{current}/{limit}")
    public  ResultMessage pageList(@ApiParam(name = "current", value = "当前页", required = true)@PathVariable long current,
                                   @ApiParam(name = "limit", value = "每页记录数", required = true)@PathVariable long limit){
        Page<EduTeacher> pageTeacher = new Page<>(current,limit);
        eduTeacherService.page(pageTeacher,null);
        List<EduTeacher> records = pageTeacher.getRecords();
        long total = pageTeacher.getTotal();
        Map map = new HashMap();
        map.put("rows",records);
        map.put("total",total);
        return ResultMessage.ok().data("map",map);

    }

    @ApiOperation(value = "分页条件查询讲师列表")
    @PostMapping("/{current}/{limit}")
    public  ResultMessage pageQueryTeacher(@ApiParam(name = "current", value = "当前页", required = true)@PathVariable long current,
                                           @ApiParam(name = "limit", value = "每页记录数", required = true)@PathVariable long limit,
                                           @ApiParam(name = "eduTeacherQuery",value = "查询对象", required = false)
                                               @RequestBody(required = false) EduTeacherQuery eduTeacherQuery){
        Page<EduTeacher> teacherPage = new Page<>(current,limit);
        eduTeacherService.pageQuery(teacherPage,eduTeacherQuery);
        List<EduTeacher> records = teacherPage.getRecords();
        long total = teacherPage.getTotal();
        Map map = new HashMap();
        map.put("rows",records);
        map.put("total",total);
        return ResultMessage.ok().data("map",map);
    }

    @ApiOperation(value = "新增讲师")
    @PostMapping("/AddEduTeacher")
    public  ResultMessage AddEduTeacher(@ApiParam(name = "eduTeacher", value = "讲师对象", required = true)@RequestBody EduTeacher eduTeacher){
        boolean b = eduTeacherService.save(eduTeacher);
        if(b){
            return ResultMessage.ok();
        }else {
            return ResultMessage.error();
        }

    }
    @ApiOperation(value = "查询讲师")
    @GetMapping("{id}")
    public ResultMessage selectById(@PathVariable String id){

        EduTeacher eduTeacher = eduTeacherService.getById(id);
        return ResultMessage.ok().data("teacher",eduTeacher);
    }

    @ApiOperation(value = "讲师的修改")
    @PostMapping("updateEduTeacher")
    public  ResultMessage UpdateTeacher(@RequestBody EduTeacher eduTeacher){
        boolean b = eduTeacherService.updateById(eduTeacher);
        if(b){
            return ResultMessage.ok();
        }else {
            return ResultMessage.error();
        }
    }

    @ApiOperation(value = "查询所有删除的讲师")
    @GetMapping("/isDeleted/{isDeleted}")
    public ResultMessage getTeacherByIsDeleted(@PathVariable Integer isDeleted){
        List<EduTeacher> eduTeacherList = eduTeacherService.getTeacherByIsDeleted(isDeleted);
        return ResultMessage.ok().data("teacherList",eduTeacherList);
    }

    @ApiOperation(value = "批量删除讲师")
    @DeleteMapping("/deleteBatch/{ids}")
    public ResultMessage deleteBatch(@PathVariable(value = "ids") String ids){
        String[] idStr = ids.split(",");
        List<String> fileNameList = new ArrayList<>();
        for (String id:
                idStr) {
            //先删除讲师里的头像
            EduTeacher eduTeacher = eduTeacherService.getById(id);
            String avatar = eduTeacher.getAvatar();
            if(!StringUtils.isEmpty(avatar)) {
                String fileNameStr = avatar.substring(47);
                String fileName = fileNameStr.replaceAll("/", "-");
                if (!fileName.contains("default")) {
                    fileNameList.add(fileName);
                }
            }
        }
        if(fileNameList.size()>0) {
            ResultMessage resultMessage = ossClient.deleteBatch(fileNameList);
            if (resultMessage.getCode() == 20001) {
                throw new CareException(20001, "批量删除头像失败");
            }
        }
        for (String id:
                idStr) {
            eduTeacherService.removeById(id);
        }
        return ResultMessage.ok();
    }

}

