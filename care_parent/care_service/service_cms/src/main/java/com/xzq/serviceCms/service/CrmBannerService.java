package com.xzq.serviceCms.service;

import com.xzq.serviceCms.entity.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author care
 * @since 2021-03-05
 */
public interface CrmBannerService extends IService<CrmBanner> {

    /**
     * @Description: 获取轮播图前两张
     * @Author xuzhiqiang
     * @Date 2021/3/8 16:53
     */
    List<CrmBanner> listBanner();
}
