package com.xzq.serviceEdu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xzq.serviceEdu.entity.EduSubject;
import com.xzq.serviceEdu.entity.easyexcel.SubjectData;
import com.xzq.serviceEdu.entity.vo.SubjectVoOne;
import com.xzq.serviceEdu.entity.vo.SubjectVoTwo;
import com.xzq.serviceEdu.listener.SubjectEasyExcelListener;
import com.xzq.serviceEdu.mapper.EduSubjectMapper;
import com.xzq.serviceEdu.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-01-28
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {


    @Autowired
    private  EduSubjectMapper eduSubjectMapper;
    @Override
    public void saveSubject(MultipartFile file, EduSubjectService eduSubjectService) {
        try {

            //文件输入流
            InputStream inputStream = file.getInputStream();


            EasyExcel.read(inputStream, SubjectData.class,new SubjectEasyExcelListener(eduSubjectService)).sheet().doRead();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<SubjectVoOne> getAllSubject() {
        List<SubjectVoOne> subjectNestedVoArrayList = new ArrayList<>();
        //查询所有的一级分类
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id",0);
        queryWrapper.orderByAsc("sort","id");
        List<EduSubject> eduSubjectsOne = eduSubjectMapper.selectList(queryWrapper);
        //查询所有的二级分类
        QueryWrapper<EduSubject> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.ne("parent_id", 0);
        queryWrapper2.orderByAsc("sort", "id");
        List<EduSubject> eduSubjectsTwo = baseMapper.selectList(queryWrapper2);

        //填充一级分类
        for (int i = 0; i < eduSubjectsOne.size(); i++) {
            EduSubject eduSubjectOne = eduSubjectsOne.get(i);
            SubjectVoOne subjectVoOne = new SubjectVoOne();
            /*subjectNestedVoOne.setId(eduSubject.getId());
            subjectNestedVoOne.setTitle(eduSubject.getTitle());*/
            //subjectNestedVoOne.setChildern();
            BeanUtils.copyProperties(eduSubjectOne, subjectVoOne);

            //填充二级分类vo数据
            ArrayList<SubjectVoTwo> subjectVoTwoArrayList = new ArrayList<>();
            for (int j = 0; j < eduSubjectsTwo.size(); j++) {
                EduSubject eduSubjectTwo = eduSubjectsTwo.get(j);
                if(eduSubjectTwo.getParentId().equals(eduSubjectOne.getId())){
                    SubjectVoTwo subjectVoTwo = new SubjectVoTwo();
                    BeanUtils.copyProperties(eduSubjectTwo,subjectVoTwo);
                    subjectVoTwoArrayList.add(subjectVoTwo);
                }
            }
            subjectVoOne.setChildren(subjectVoTwoArrayList);
            subjectNestedVoArrayList.add(subjectVoOne);
        }

        return subjectNestedVoArrayList;
    }
}
