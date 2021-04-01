package com.xzq.serviceUcenter.service;

import com.xzq.serviceUcenter.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xzq.serviceUcenter.entity.utilsVo.LoginVo;
import com.xzq.serviceUcenter.entity.utilsVo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author care
 * @since 2021-03-11
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    /**
     * @Description: 登录方法
     * @Author xuzhiqiang
     * @Date 2021/3/11 14:14
     */
    String login(LoginVo loginVo);

    /**
     * @Description: 会员注册
     * @Author xuzhiqiang
     * @Date 2021/3/11 14:28
     */
    Integer register(RegisterVo registerVo);

    /**
     * @Description: 根据openid查找用户
     * @Author xuzhiqiang
     * @Date 2021/4/1 9:58
     */
    UcenterMember getUcenterMemberByOpenId(String openid);
}
