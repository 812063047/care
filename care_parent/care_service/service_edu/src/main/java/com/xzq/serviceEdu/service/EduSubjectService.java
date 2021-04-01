package com.xzq.serviceEdu.service;

import com.xzq.serviceEdu.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xzq.serviceEdu.entity.vo.SubjectVoOne;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-01-28
 */
public interface EduSubjectService extends IService<EduSubject> {

    void saveSubject(MultipartFile file, EduSubjectService eduSubjectService);

    List<SubjectVoOne> getAllSubject();
}
