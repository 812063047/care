package com.xzq.serviceEdu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xzq.commonUtils.ResultMessage;
import com.xzq.serviceBase.Exception.CareException;
import com.xzq.serviceEdu.client.OssClient;
import com.xzq.serviceEdu.entity.EduCourse;
import com.xzq.serviceEdu.entity.EduCourseDescription;
import com.xzq.serviceEdu.entity.EduTeacher;
import com.xzq.serviceEdu.entity.frontVo.CourseFrontVo;
import com.xzq.serviceEdu.entity.frontVo.CourseWebVo;
import com.xzq.serviceEdu.entity.publish.CoursePublishVo;
import com.xzq.serviceEdu.entity.vo.CourseInfoVo;
import com.xzq.serviceEdu.mapper.EduCourseDescriptionMapper;
import com.xzq.serviceEdu.mapper.EduCourseMapper;
import com.xzq.serviceEdu.query.EduCourseQuery;
import com.xzq.serviceEdu.service.EduChapterService;
import com.xzq.serviceEdu.service.EduCourseDescriptionService;
import com.xzq.serviceEdu.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xzq.serviceEdu.service.EduVideoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-01-28
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {
    @Autowired
    private EduCourseMapper eduCourseMapper;

    @Autowired
    private EduCourseDescriptionMapper eduCourseDescriptionMapper;

    @Autowired
    private EduVideoService eduVideoService;

    @Autowired
    private EduChapterService eduChapterService;

    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;

    @Autowired
    private OssClient ossClient;

    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        //向课程表添加基本信息
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int eduCourseNum = eduCourseMapper.insert(eduCourse);
        if(eduCourseNum<=0){
            throw  new CareException(20001,"添加课程信息失败");
        }
        //获取添加课程表成功Id
        String id = eduCourse.getId();
        //向课程简介表添加课程简介
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setDescription(courseInfoVo.getDescription());
        eduCourseDescription.setId(id);
        int eduCourseDescriptionNum = eduCourseDescriptionMapper.insert(eduCourseDescription);
        if(eduCourseDescriptionNum<=0){
            throw  new CareException(20001,"添加课程简介信息失败");
        }
        return eduCourse.getId();

    }

    @Override
    public CourseInfoVo getCourseInfoVoById(String id) {
        EduCourse eduCourse = eduCourseMapper.selectById(id);
        if(eduCourse == null){
            throw new CareException(20001,"数据不存在");
        }
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(eduCourse,courseInfoVo);
        EduCourseDescription eduCourseDescription = eduCourseDescriptionMapper.selectById(id);
        String description = eduCourseDescription.getDescription();
        if(eduCourse!=null) {
            courseInfoVo.setDescription(description);
        }
        return courseInfoVo;
    }

    @Override
    public String updateCourseInfo(CourseInfoVo courseInfoVo) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int i = eduCourseMapper.updateById(eduCourse);
        if(i==0){
            throw new CareException(20001,"修改课程信息失败");
        }
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setId(courseInfoVo.getId());
        eduCourseDescription.setDescription(courseInfoVo.getDescription());
        int i1 = eduCourseDescriptionMapper.updateById(eduCourseDescription);
        if(i1==0){
            throw new CareException(20001,"修改课程描述失败");
        }
        return eduCourse.getId();
    }

    @Override
    public CoursePublishVo getCoursePublishVoById(String id) {
        CoursePublishVo coursePublishVo =eduCourseMapper.getCoursePublishVoById(id);
        return coursePublishVo;
    }

    @Override
    public boolean publishCourseById(String courseId) {
        EduCourse eduCourse= new EduCourse();
        eduCourse.setId(courseId);
        eduCourse.setStatus("Normal");//设置课程的发布状态
        Integer result = eduCourseMapper.updateById(eduCourse);
        return null != result && result > 0;
    }

    @Override
    public IPage<EduCourse> pageQueryCourse(Long current, Long limit, EduCourseQuery eduCourseQuery) {
        Page<EduCourse> page = new Page<>(current,limit);
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("gmt_create");
        if(eduCourseQuery==null){
            IPage<EduCourse> eduCourseIPage =eduCourseMapper.selectPage(page,queryWrapper);
            return eduCourseIPage;
        }
        String subjectId = eduCourseQuery.getSubjectId();
        String subjectParentId = eduCourseQuery.getSubjectParentId();
        String teacherId = eduCourseQuery.getTeacherId();
        String title = eduCourseQuery.getTitle();
        if(!StringUtils.isEmpty(subjectId)){
            queryWrapper.ge("subject_id",subjectId);
        }
        if(!StringUtils.isEmpty(subjectParentId)){
            queryWrapper.ge("subject_parent_id",subjectParentId);
        }
        if(!StringUtils.isEmpty(title)){
            queryWrapper.like("title",title);
        }
        if(!StringUtils.isEmpty(teacherId)){
            queryWrapper.eq("teacher_id",teacherId);
        }
        IPage<EduCourse> eduCourseIPage = eduCourseMapper.selectPage(page, queryWrapper);
        return eduCourseIPage;
    }

    @Override
    public boolean deleteByCourseId(String courseId) {
        //先删除课程所有小节
        eduVideoService.deleteByCourseId(courseId);
        //删除课程所有章节
        eduChapterService.deleteByCourseId(courseId);
        //删课程描述
        eduCourseDescriptionService.removeById(courseId);
        //删除课程的头像
        //获取课程信息
        EduCourse eduCourse = eduCourseMapper.selectById(courseId);
        String cover = eduCourse.getCover();
        String fileNameStr = cover.substring(47);
        String fileName = fileNameStr.replaceAll("/","-");
        //判断是否是默认封面
        if(!fileName.contains("default")){
            //调用微服务框架OSS
            ResultMessage resultMessage = ossClient.deleteOssPhotoByFileName(fileName);
            if(resultMessage.getCode()==20001){
                throw  new CareException(20001,"删除课程封面失败");
            }
        }
        //最后删除课程
        Integer result = eduCourseMapper.deleteById(courseId);
        return null != result && result > 0;
    }

    @Override
    public boolean deleteBatch(String courseIds) {
        String[] courseIdStr = courseIds.split(",");
        List<String> fileNameList = new ArrayList<>();
        for (String courseId :
                courseIdStr) {
            //先删除课程所有小节
            eduVideoService.deleteByCourseId(courseId);
            //删除课程所有章节
            eduChapterService.deleteByCourseId(courseId);
            //删课程描述
            eduCourseDescriptionService.removeById(courseId);
            //获取课程信息
            EduCourse eduCourse = eduCourseMapper.selectById(courseId);
            String cover = eduCourse.getCover();
            if(!StringUtils.isEmpty(cover)) {
                String fileNameStr = cover.substring(47);
                String fileName = fileNameStr.replaceAll("/", "-");
                if (!fileName.contains("default")) {
                    fileNameList.add(fileName);
                }
            }
        }
        if(fileNameList.size()>0) {
            ResultMessage resultMessage = ossClient.deleteBatch(fileNameList);
            if (resultMessage.getCode() == 20001) {
                throw new CareException(20001, "批量删除课程封面失败");
            }
        }
        Integer  result =null;
        for (String courseId :
                courseIdStr) {
            //最后删除课程
             result = eduCourseMapper.deleteById(courseId);
        }
        return null != result && result > 0;
    }

    @Override
    //@Cacheable(value = "course",key = "'listCourse'")
    public List<EduCourse> listCourse() {
        QueryWrapper<EduCourse> courseQueryWrapper = new QueryWrapper<>();
        courseQueryWrapper.orderByDesc("gmt_create");
        courseQueryWrapper.last("limit 8");
        List<EduCourse> eduCourseList = eduCourseMapper.selectList(courseQueryWrapper);
        return eduCourseList;
    }

    @Override
    public List<EduCourse> selectByTeacherId(String id) {
        QueryWrapper<EduCourse> eduCourseQueryWrapper = new QueryWrapper<>();
        eduCourseQueryWrapper.eq("teacher_id",id);
        List<EduCourse> eduCourseList = eduCourseMapper.selectList(eduCourseQueryWrapper);
        return eduCourseList;
    }

    @Override
    public Map<String, Object> pageListWeb(Page<EduCourse> coursePage, CourseFrontVo courseQuery) {
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(courseQuery.getSubjectParentId())) {
            queryWrapper.eq("subject_parent_id", courseQuery.getSubjectParentId());
        }
        if (!StringUtils.isEmpty(courseQuery.getSubjectId())) {
            queryWrapper.eq("subject_id", courseQuery.getSubjectId());
        }
        if (!StringUtils.isEmpty(courseQuery.getBuyCountSort())) {
            queryWrapper.orderByDesc("buy_count");
        }
        if (!StringUtils.isEmpty(courseQuery.getGmtCreateSort())) {
            queryWrapper.orderByDesc("gmt_create");
        }
        if (!StringUtils.isEmpty(courseQuery.getPriceSort())) {
            queryWrapper.orderByDesc("price");
        }
        eduCourseMapper.selectPage(coursePage, queryWrapper);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("items", coursePage.getRecords());
        map.put("current", coursePage.getCurrent());
        map.put("pages", coursePage.getPages());
        map.put("size", coursePage.getSize());
        map.put("total", coursePage.getTotal());
        map.put("hasNext", coursePage.hasNext());
        map.put("hasPrevious", coursePage.hasPrevious());
        return map;
    }

    @Override
    public CourseWebVo selectInfoWebById(String courseId) {
        EduCourse eduCourse = eduCourseMapper.selectById(courseId);
        eduCourse.setViewCount(eduCourse.getViewCount() + 1);
        eduCourseMapper.updateById(eduCourse);
        return eduCourseMapper.selectInfoWebById(courseId);
    }
}
