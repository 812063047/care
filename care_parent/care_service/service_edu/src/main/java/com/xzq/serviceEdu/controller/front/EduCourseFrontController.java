package com.xzq.serviceEdu.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xzq.commonUtils.ResultMessage;
import com.xzq.serviceEdu.entity.EduCourse;
import com.xzq.serviceEdu.entity.chapter.ChapterVo;
import com.xzq.serviceEdu.entity.frontVo.CourseFrontVo;
import com.xzq.serviceEdu.entity.frontVo.CourseWebVo;
import com.xzq.serviceEdu.service.EduChapterService;
import com.xzq.serviceEdu.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/eduservice/coursefront")
@CrossOrigin
@Api("前台课程api")
public class EduCourseFrontController {
    @Autowired
    private EduCourseService eduCourseService;
    @Autowired
    private EduChapterService eduChapterService;

    @ApiOperation(value = "分页课程列表")
    @PostMapping(value = "{page}/{limit}")
    public ResultMessage pageList(
        @ApiParam(name = "page", value = "当前页码", required = true)
        @PathVariable Long page,
        @ApiParam(name = "limit", value = "每页记录数", required = true)
        @PathVariable Long limit,
        @ApiParam(name = "courseQuery", value = "查询对象", required = false)
        @RequestBody(required = false) CourseFrontVo courseFrontVo) {

        Page<EduCourse> pageParam = new Page<EduCourse>(page, limit);

        Map<String, Object> map = eduCourseService.pageListWeb(pageParam, courseFrontVo);
        return  ResultMessage.ok().data(map);
    }

    @ApiOperation(value = "根据ID查询课程")
    @GetMapping(value = "{courseId}")
    public ResultMessage getCourseWebById(@ApiParam(name = "courseId", value = "课程ID", required = true)
    @PathVariable String courseId){
        //查询课程信息和讲师信息
        CourseWebVo courseWebVo = eduCourseService.selectInfoWebById(courseId);

        //查询当前课程的章节信息
        List<ChapterVo> chapterVoList = eduChapterService.getChapterVideoByCourseId(courseId);
        return ResultMessage.ok().data("course", courseWebVo).data("chapterVoList", chapterVoList);
    }
}
