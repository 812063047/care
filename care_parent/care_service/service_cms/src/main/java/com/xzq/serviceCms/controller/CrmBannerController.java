package com.xzq.serviceCms.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xzq.commonUtils.ResultMessage;
import com.xzq.serviceCms.entity.CrmBanner;
import com.xzq.serviceCms.service.CrmBannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author care
 * @since 2021-03-05
 */
@RestController
@RequestMapping("/cmsservice/crmbanner")
@CrossOrigin
@Api("网站首页Banner列表")
public class CrmBannerController {

    @Autowired
    private CrmBannerService crmBannerService;

    @ApiOperation(value = "根据id获取Banner")
    @GetMapping("{id}")
    public ResultMessage getBannerById(@PathVariable String id){
        CrmBanner crmBanner = crmBannerService.getById(id);
        return ResultMessage.ok().data("crmBanner",crmBanner);
    }

    @ApiOperation(value = "新增Banner")
    @PostMapping("save")
    public ResultMessage save(@RequestBody CrmBanner banner) {

        boolean blag = crmBannerService.save(banner);
        if(blag){
            return ResultMessage.ok();
        }else{
            return ResultMessage.error();
        }
    }

    @ApiOperation(value = "获取Banner分页列表")
    @GetMapping("{current}/{limit}")
    public ResultMessage getBannerQuery(@ApiParam(name = "current", value = "当前页码", required = true)
                                                   @PathVariable Long current,
                                        @ApiParam(name = "limit", value = "每页记录数", required = true)
                                                    @PathVariable Long limit){
        Page<CrmBanner> page = new Page<>();
        crmBannerService.page(page,null);
        List<CrmBanner> records = page.getRecords();
        long total = page.getTotal();
        Map<String,Object> map = new HashMap<>();
        map.put("crmBanner",records);
        map.put("total",total);
        return ResultMessage.ok().data("map",map);
    }

    @ApiOperation(value = "删除Banner")
    @DeleteMapping("{id}")
    public ResultMessage remove(@PathVariable String id) {
        crmBannerService.removeById(id);
        return ResultMessage.ok();
    }

    @ApiOperation(value = "修改Banner")
    @PostMapping("/update")
    public ResultMessage updateBanner(@RequestBody CrmBanner banner){
        boolean b = crmBannerService.updateById(banner);
        if(b){
            return ResultMessage.ok();
        }else {
            return ResultMessage.error();
        }
    }

    @ApiOperation(value = "获取首页banner")
    @GetMapping("/getAllBanner")
    public ResultMessage getAllBanner(){
        List<CrmBanner> crmBanners = crmBannerService.listBanner();
        return ResultMessage.ok().data("crmBanners",crmBanners);
    }

}

