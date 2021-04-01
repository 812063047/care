package com.xzq.serviceEdu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xzq.serviceEdu.entity.EduTeacher;
import com.xzq.serviceEdu.mapper.EduTeacherMapper;
import com.xzq.serviceEdu.query.EduTeacherQuery;
import com.xzq.serviceEdu.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-01-20
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Autowired
    private  EduTeacherMapper eduTeacherMapper;

    /**
     * @Description: 分页条件查询讲师列表
     * @Author xuzhiqiang
     * @Date 2021/1/20 15:09
     */
    @Override
    public void pageQuery(Page<EduTeacher> teacherPage, EduTeacherQuery eduTeacherQuery) {
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("gmt_create");
        if(eduTeacherQuery==null){
            eduTeacherMapper.selectPage(teacherPage,queryWrapper);
        }
        String name = eduTeacherQuery.getName();
        String begin = eduTeacherQuery.getBegin();
        String end = eduTeacherQuery.getEnd();
        Integer level = eduTeacherQuery.getLevel();
        if(!StringUtils.isEmpty(name)){
            queryWrapper.like("name",name);
        }
        if(!StringUtils.isEmpty(begin)){
            queryWrapper.ge("gmt_create",begin);
        }
        if(!StringUtils.isEmpty(end)){
            queryWrapper.le("gmt_modified",end);
        }
        if(level!=null){
            queryWrapper.eq("level",level);
        }
        eduTeacherMapper.selectPage(teacherPage,queryWrapper);

    }

    @Override
    public List<EduTeacher> getTeacherByIsDeleted(Integer isDeleted) {
        List<EduTeacher> eduTeacherList = eduTeacherMapper.getTeacherByIsDeleted(isDeleted);
        return eduTeacherList;
    }

    @Override
    @Cacheable(value = "teacher",key = "'listTeacher'")
    public List<EduTeacher> listTeacher() {
        QueryWrapper<EduTeacher> teacherQueryWrapper = new QueryWrapper<>();
        teacherQueryWrapper.orderByDesc("id");
        teacherQueryWrapper.last("limit 4");
        List<EduTeacher> eduTeacherList = eduTeacherMapper.selectList(teacherQueryWrapper);
        return eduTeacherList;
    }

    @Override
    public Map<String, Object> getTeacherPageList(Page<EduTeacher> teacherPage) {
        QueryWrapper<EduTeacher> eduTeacherQueryWrapper = new QueryWrapper<>();
        eduTeacherQueryWrapper.orderByAsc("id");
        eduTeacherMapper.selectPage(teacherPage,eduTeacherQueryWrapper);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("items", teacherPage.getRecords());
        map.put("current", teacherPage.getCurrent());
        map.put("pages", teacherPage.getPages());
        map.put("size", teacherPage.getSize());
        map.put("total", teacherPage.getTotal());
        map.put("hasNext", teacherPage.hasNext());
        map.put("hasPrevious", teacherPage.hasPrevious());
        return map;
    }
}
