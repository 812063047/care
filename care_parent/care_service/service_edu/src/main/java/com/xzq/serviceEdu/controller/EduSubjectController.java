package com.xzq.serviceEdu.controller;


import com.xzq.commonUtils.ResultMessage;
import com.xzq.serviceEdu.entity.vo.SubjectVoOne;
import com.xzq.serviceEdu.service.EduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-01-28
 */

@Api("课程分类管理")
@RestController
@CrossOrigin
@RequestMapping("/eduservice/edusubject")
public class EduSubjectController {
    @Autowired
    private EduSubjectService eduSubjectService;

    @PostMapping("addSubject")
    @ApiOperation(value = "Excel批量导入")
    public ResultMessage addSubject(@RequestParam("file") MultipartFile file){
        eduSubjectService.saveSubject(file,eduSubjectService);
        return ResultMessage.ok();
    }

    @ApiOperation(value = "课程分类列表树形")
    @GetMapping
    public ResultMessage getAllSubject(){
        List<SubjectVoOne> subjectVoOneList =eduSubjectService.getAllSubject();
        return ResultMessage.ok().data("subjectList", subjectVoOneList);
    }

}

