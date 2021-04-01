package com.xzq.serviceEdu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xzq.serviceEdu.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xzq.serviceEdu.query.EduTeacherQuery;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-01-20
 */
public interface EduTeacherService extends IService<EduTeacher> {

    /**
     * @Description: 分页条件查询讲师列表
     * @Author xuzhiqiang
     * @Date 2021/1/20 15:08
     */
    public void pageQuery(Page<EduTeacher> teacherPage, EduTeacherQuery eduTeacherQuery);

    /**
     * @Description: 查询已删除的讲师
     * @Author xuzhiqiang
     * @Date 2021/1/28 15:46
     */
    List<EduTeacher> getTeacherByIsDeleted(Integer isDeleted);

    /**
     * @Description: 获取前四位讲师
     * @Author xuzhiqiang
     * @Date 2021/3/8 16:55
     */
    List<EduTeacher> listTeacher();
    /**
     * @Description: 前台分页查询
     * @Author xuzhiqiang
     * @Date 2021/4/1 11:28
     */
    Map<String, Object> getTeacherPageList(Page<EduTeacher> teacherPage);
}
