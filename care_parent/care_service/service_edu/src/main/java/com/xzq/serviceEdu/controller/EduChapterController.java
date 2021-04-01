package com.xzq.serviceEdu.controller;


import com.xzq.commonUtils.ResultMessage;
import com.xzq.serviceEdu.entity.EduChapter;
import com.xzq.serviceEdu.entity.chapter.ChapterVo;
import com.xzq.serviceEdu.service.EduChapterService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-01-28
 */
@RestController
@CrossOrigin
@RequestMapping("/eduservice/educhapter")
public class EduChapterController {

    @Autowired
    private EduChapterService eduChapterService;

    @ApiOperation(value = "查询课程courseId的章节")
    @GetMapping("getChapterVideo/{courseId}")
    public ResultMessage getChapterVideo(@PathVariable String courseId){
        List<ChapterVo> chapterVoList =eduChapterService.getChapterVideoByCourseId(courseId);
        return ResultMessage.ok().data("chapterVoList",chapterVoList);
    }

    @ApiOperation(value = "新增章节")
    @PostMapping()
    public ResultMessage addChapter(
            @ApiParam(name = "eduChapter", value = "章节对象", required = true)
            @RequestBody(required = true) EduChapter eduChapter){
        boolean blag = eduChapterService.save(eduChapter);
        if(blag){
            return ResultMessage.ok();
        }else{
            return ResultMessage.error();
        }

    }

    @ApiOperation(value = "根据ID获取章节")
    @GetMapping("{id}")
    public ResultMessage getChapterById(@ApiParam(name = "id", value = "章节ID", required = true)
            @PathVariable String id){
        EduChapter eduChapter = eduChapterService.getById(id);
        return ResultMessage.ok().data("eduChapter",eduChapter);

    }

    @ApiOperation(value = "修改章节")
    @PostMapping("updateChapter")
    public ResultMessage updateChapter(@ApiParam(name = "eduChapter", value = "章节对象", required = true)
                                           @RequestBody(required = true) EduChapter eduChapter){
        boolean blag = eduChapterService.updateById(eduChapter);
        if(blag){
            return ResultMessage.ok();
        }else{
            return ResultMessage.error();
        }
    }
    @ApiOperation(value = "根据ID删除章节")
    @DeleteMapping("{id}")
    public ResultMessage deleteChapterById(@ApiParam(name = "id", value = "章节ID", required = true)
                                               @PathVariable String id){

        boolean blag = eduChapterService.removeChapterById(id);
        if(blag){
            return ResultMessage.ok();
        }else{
            return ResultMessage.error();
        }
    }
}

