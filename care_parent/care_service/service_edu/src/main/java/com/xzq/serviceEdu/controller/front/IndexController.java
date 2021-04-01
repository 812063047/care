package com.xzq.serviceEdu.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xzq.commonUtils.ResultMessage;
import com.xzq.serviceEdu.entity.EduCourse;
import com.xzq.serviceEdu.entity.EduTeacher;
import com.xzq.serviceEdu.service.EduCourseService;
import com.xzq.serviceEdu.service.EduTeacherService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api("首页展示")
@RestController
@RequestMapping("/eduservice/index")
@CrossOrigin
public  class IndexController {
    @Autowired
    private EduTeacherService eduTeacherService;

    @Autowired
    private EduCourseService eduCourseService;

    @GetMapping("/getIndexData")
    public ResultMessage index(){
        List<EduTeacher> eduTeacherList = eduTeacherService.listTeacher();

        List<EduCourse> eduCourseList = eduCourseService.listCourse();
        return ResultMessage.ok().data("eduTeacherList",eduTeacherList).data("eduCourseList",eduCourseList);
    }
}
