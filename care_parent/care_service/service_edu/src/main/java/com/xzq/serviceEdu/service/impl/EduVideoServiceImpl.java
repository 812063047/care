package com.xzq.serviceEdu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xzq.commonUtils.ResultMessage;
import com.xzq.serviceBase.Exception.CareException;
import com.xzq.serviceEdu.client.VodClient;
import com.xzq.serviceEdu.entity.EduVideo;
import com.xzq.serviceEdu.entity.video.VideoInfoVo;
import com.xzq.serviceEdu.mapper.EduVideoMapper;
import com.xzq.serviceEdu.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-01-28
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Autowired
    private  EduVideoMapper eduVideoMapper;

    @Autowired
    private VodClient vodClient;

    @Override
    public boolean saveVideoInfo(VideoInfoVo videoInfoVo) {
        EduVideo eduVideo = new EduVideo();
        BeanUtils.copyProperties(videoInfoVo,eduVideo);
        int insert = eduVideoMapper.insert(eduVideo);
        if(insert==0){
            throw  new CareException(20001,"课时信息保存失败");
        }
        return true;
    }

    @Override
    public VideoInfoVo getVideoInfoById(String id) {
        EduVideo eduVideo = eduVideoMapper.selectById(id);
        if(eduVideo == null){
            throw new CareException(20001, "数据不存在");
        }
        VideoInfoVo videoInfoVo = new VideoInfoVo();
        BeanUtils.copyProperties(eduVideo,videoInfoVo);
        return videoInfoVo;
    }

    @Override
    public boolean updateVideoInfo(VideoInfoVo videoInfoVo) {
        EduVideo eduVideo = new EduVideo();
        BeanUtils.copyProperties(videoInfoVo,eduVideo);
        int i = eduVideoMapper.updateById(eduVideo);
        if(i==0){
            throw new CareException(20001, "课时更新失败");
        }
        return true;
    }

    @Override
    public boolean removeVideoById(String id) {
        EduVideo eduVideo = eduVideoMapper.selectById(id);
        String videoSourceId = eduVideo.getVideoSourceId();
        if(!StringUtils.isEmpty(videoSourceId)){
            ResultMessage resultMessage = vodClient.deleteVideoByVideoId(videoSourceId);
            if(resultMessage.getCode()==20001){
                throw  new CareException(20001,"删除小节视频失败");
            }
        }
        Integer result = eduVideoMapper.deleteById(id);
        return null != result && result > 0;
    }

    @Override
    //TODO 删小节对应删里面的视频
    public boolean deleteByCourseId(String courseId) {
        //根据课程id查询出所有的视频id
        QueryWrapper<EduVideo> queryWrapperVideo = new QueryWrapper<>();
        queryWrapperVideo.eq("course_id",courseId);
        queryWrapperVideo.select("video_source_id");
        List<EduVideo> eduVideoList = eduVideoMapper.selectList(queryWrapperVideo);
        List<String> videoSourceIds = new ArrayList<>();
        for (EduVideo eduVideo :
                eduVideoList) {
            String videoSourceId = eduVideo.getVideoSourceId();
            if(!StringUtils.isEmpty(videoSourceId)) {
                videoSourceIds.add(videoSourceId);
            }
        }
        if(videoSourceIds.size()>0) {
            ResultMessage resultMessage = vodClient.deleteBatch(videoSourceIds);
            if(resultMessage.getCode()==20001){
                throw  new CareException(20001,"删除所有小节视频失败");
            }
        }
        QueryWrapper<EduVideo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);
        Integer result = eduVideoMapper.delete(queryWrapper);
        return null != result && result > 0;
    }
}
