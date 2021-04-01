package com.xzq.serviceEdu.mapper;

import com.xzq.serviceEdu.entity.EduTeacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 讲师 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2021-01-20
 */
@Repository
public interface EduTeacherMapper extends BaseMapper<EduTeacher> {

    /**
     * @Description: 查询已经删除的讲师
     * @Author xuzhiqiang
     * @Date 2021/1/28 15:47
     */
    List<EduTeacher> getTeacherByIsDeleted(Integer isDeleted);
}
