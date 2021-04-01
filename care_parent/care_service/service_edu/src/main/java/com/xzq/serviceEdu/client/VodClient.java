package com.xzq.serviceEdu.client;

import com.xzq.commonUtils.ResultMessage;
import com.xzq.serviceEdu.client.impl.VodClientImpl;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "service-vod",fallback = VodClientImpl.class)
@Component
public interface VodClient {

    @DeleteMapping("vodservice/uploadvideo/{videoId}")
    public ResultMessage deleteVideoByVideoId(@ApiParam(name = "videoId", value = "云端视频id", required = true)
                                                  @PathVariable String videoId);

    @DeleteMapping("vodservice/uploadvideo/deleteBatch")
    public  ResultMessage deleteBatch(@ApiParam(name = "videoIdList", value = "云端视频id", required = true)
                                      @RequestParam("videoIdList") List<String> videoIdList);
}
