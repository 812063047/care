package com.xzq.serviceEdu.client;

import com.xzq.commonUtils.ResultMessage;
import com.xzq.serviceEdu.client.impl.OssClientImpl;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "service-oss",fallback = OssClientImpl.class)
@Component
public interface OssClient {

    @DeleteMapping("ossservice/uploadfile/{fileName}")
    public ResultMessage deleteOssPhotoByFileName(@ApiParam(name = "fileName", value = "文件名字", required = true)
                                                  @PathVariable String fileName);

    @DeleteMapping("ossservice/uploadfile/deleteBatch")
    public ResultMessage deleteBatch(@ApiParam(name = "fileNames", value = "多个文件名字", required = true)
                                     @RequestParam(value = "fileNames") List<String> fileNames);
}
