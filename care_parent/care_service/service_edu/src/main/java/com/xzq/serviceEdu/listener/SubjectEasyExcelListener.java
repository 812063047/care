package com.xzq.serviceEdu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xzq.serviceBase.Exception.CareException;
import com.xzq.serviceEdu.entity.EduSubject;
import com.xzq.serviceEdu.entity.easyexcel.SubjectData;
import com.xzq.serviceEdu.service.EduSubjectService;

import java.util.Map;

public class SubjectEasyExcelListener extends AnalysisEventListener<SubjectData> {
    public EduSubjectService eduSubjectService;
    public SubjectEasyExcelListener(){}//无参构造
    public SubjectEasyExcelListener(EduSubjectService eduSubjectService){
        this.eduSubjectService = eduSubjectService;
    }

    //一行一行的读
    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if(subjectData ==null){
            throw  new CareException(20001,"添加失败");
        }
        EduSubject eduSubjectOne =  this.existOneSubject(eduSubjectService,subjectData.getOneSubjectName());
        //判断一级分类是否重复
        if(eduSubjectOne == null){
            eduSubjectOne = new EduSubject();
            eduSubjectOne.setParentId("0");
            eduSubjectOne.setTitle(subjectData.getOneSubjectName());
            eduSubjectService.save(eduSubjectOne);
        }

        //判断二级分类是否重复
        //获取二级分类ID
        String pId = eduSubjectOne.getId();
        EduSubject eduSubjectTwo = this.existTwoSubject(eduSubjectService,subjectData.getTwoSubjectName(),pId);
        if(eduSubjectTwo == null){
            eduSubjectTwo = new EduSubject();
            eduSubjectTwo.setTitle(subjectData.getTwoSubjectName());
            eduSubjectTwo.setParentId(pId);
            eduSubjectService.save(eduSubjectTwo);
        }
    }


    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    //判断一级分类是否有相同
    private EduSubject existOneSubject(EduSubjectService eduSubjectService,String name) {
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title",name);
        queryWrapper.eq("parent_id",0);
        EduSubject eduSubject = eduSubjectService.getOne(queryWrapper);
        return eduSubject;
    }
    //判断二级分类是否有相同

    private EduSubject existTwoSubject(EduSubjectService eduSubjectService,String name,String pId) {
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title",name);
        queryWrapper.eq("parent_id",pId);
        EduSubject eduSubject = eduSubjectService.getOne(queryWrapper);
        return eduSubject;
    }
}
