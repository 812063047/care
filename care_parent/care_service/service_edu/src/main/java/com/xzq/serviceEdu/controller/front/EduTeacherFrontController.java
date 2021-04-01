package com.xzq.serviceEdu.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xzq.commonUtils.ResultMessage;
import com.xzq.serviceEdu.entity.EduCourse;
import com.xzq.serviceEdu.entity.EduTeacher;
import com.xzq.serviceEdu.service.EduCourseService;
import com.xzq.serviceEdu.service.EduTeacherService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/eduservice/teacherfront")
@CrossOrigin
@Api("前台讲师api")
public class EduTeacherFrontController {
    @Autowired
    private EduTeacherService eduTeacherService;

    @Autowired
    private EduCourseService eduCourseService;

    @PostMapping("getTeacherPageList/{page}/{limit}")
    public ResultMessage getTeacherPageList(@PathVariable long page,@PathVariable long limit){
        Page<EduTeacher> teacherPage = new Page<>(page,limit);
        Map<String, Object> pageListMap = eduTeacherService.getTeacherPageList(teacherPage);
        return ResultMessage.ok().data(pageListMap);
    }

    @GetMapping("{id}")
    public ResultMessage getTeacherByid(@PathVariable String id){
        //查询讲师信息
        EduTeacher teacher = eduTeacherService.getById(id);
        //根据讲师id查询这个讲师的课程列表
        List<EduCourse> courseList = eduCourseService.selectByTeacherId(id);
        return ResultMessage.ok().data("teacher",teacher).data("courseList",courseList);
    }
}
