package com.xzq.serviceEdu.service;

import com.xzq.serviceEdu.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xzq.serviceEdu.entity.video.VideoInfoVo;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-01-28
 */
public interface EduVideoService extends IService<EduVideo> {

    /**
     * @Description: 添加表单
     * @Author xuzhiqiang
     * @Date 2021/2/7 16:46
     */
    boolean saveVideoInfo(VideoInfoVo videoInfoVo);

    /**
     * @Description: 根据ID查找课时
     * @Author xuzhiqiang
     * @Date 2021/2/7 17:19
     */
    VideoInfoVo getVideoInfoById(String id);

    /**
     * @Description: 更新小节
     * @Author xuzhiqiang
     * @Date 2021/2/7 17:25
     */
    boolean updateVideoInfo(VideoInfoVo videoInfoVo);

    /**
     * @Description: 根据ID删除课时
     * @Author xuzhiqiang
     * @Date 2021/2/7 17:32
     */
    boolean removeVideoById(String id);

    /**
     * @Description: 根据课程id删除所有小节
     * @Author xuzhiqiang
     * @Date 2021/3/1 18:08
     */
    boolean deleteByCourseId(String courseId);
}
