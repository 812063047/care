package com.xzq.serviceCms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xzq.serviceCms.entity.CrmBanner;
import com.xzq.serviceCms.mapper.CrmBannerMapper;
import com.xzq.serviceCms.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author care
 * @since 2021-03-05
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {
    @Autowired
    private  CrmBannerMapper crmBannerMapper;

    @Override
    @Cacheable(value = "bannerList",key = "'getAllBanner'")
    public List<CrmBanner> listBanner() {
        QueryWrapper<CrmBanner> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        queryWrapper.last("limit 2");
        List<CrmBanner> crmBannerList = crmBannerMapper.selectList(queryWrapper);
        return crmBannerList;
    }
}
