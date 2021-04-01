package com.xzq.serviceUcenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xzq.commonUtils.jwt.JwtUtils;
import com.xzq.serviceBase.Exception.CareException;
import com.xzq.serviceUcenter.entity.UcenterMember;
import com.xzq.serviceUcenter.entity.utilsVo.LoginVo;
import com.xzq.serviceUcenter.entity.utilsVo.RegisterVo;
import com.xzq.serviceUcenter.mapper.UcenterMemberMapper;
import com.xzq.serviceUcenter.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xzq.serviceUcenter.utils.MD5;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author care
 * @since 2021-03-11
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

    @Autowired
    private UcenterMemberMapper ucenterMemberMapper;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public String login(LoginVo loginVo) {
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();
        if(StringUtils.isEmpty(mobile)||StringUtils.isEmpty(password)){
            throw  new CareException(20001,"用户和密码不能为空");
        }
        QueryWrapper<UcenterMember> ucenterMemberQueryWrapper = new QueryWrapper<>();
        ucenterMemberQueryWrapper.eq("mobile",mobile);
        UcenterMember ucenterMember = ucenterMemberMapper.selectOne(ucenterMemberQueryWrapper);
        if(ucenterMember == null){
            throw  new CareException(20001,"不存在该用户");
        }
        //检验密码
        if(!MD5.encrypt(password).equals(ucenterMember.getPassword())){
            throw  new CareException(20001,"密码不正确");
        }
        if(ucenterMember.getIsDisabled()){
            throw  new CareException(20001,"该用户被禁用");
        }
        //最后使用Jwt生成token
        String jwtToken = JwtUtils.getJwtToken(ucenterMember.getId(), ucenterMember.getNickname());
        return jwtToken;
    }

    @Override
    public Integer register(RegisterVo registerVo) {
        String code = registerVo.getCode();
        String mobile = registerVo.getMobile();
        String nickname = registerVo.getNickname();
        String password = registerVo.getPassword();
        if(StringUtils.isEmpty(code)||StringUtils.isEmpty(mobile)||StringUtils.isEmpty(nickname)||StringUtils.isEmpty(password)){
            throw  new CareException(20001,"带星号的不能为空");
        }
        /*//获取redis中的验证码
        String mobileCode = redisTemplate.opsForValue().get(mobile);
        if(!mobileCode.equals(code)){
            throw  new CareException(20001,"验证码错误");
        }*/
        //判断是否存在这个电话号码注册的用户
        QueryWrapper<UcenterMember> ucenterMemberQueryWrapper = new QueryWrapper<>();
        ucenterMemberQueryWrapper.eq("mobile",mobile);
        Integer integer = ucenterMemberMapper.selectCount(ucenterMemberQueryWrapper);
        if(integer>0){
            throw  new CareException(20001,"这个号码已经注册了");
        }
        UcenterMember ucenterMember = new UcenterMember();
        ucenterMember.setMobile(mobile);
        ucenterMember.setNickname(nickname);
        ucenterMember.setPassword(MD5.encrypt(password));
        Integer insert = ucenterMemberMapper.insert(ucenterMember);
        return insert;
    }

    @Override
    public UcenterMember getUcenterMemberByOpenId(String openid) {
        QueryWrapper<UcenterMember> ucenterMemberQueryWrapper = new QueryWrapper<>();
        ucenterMemberQueryWrapper.eq("openid",openid);
        UcenterMember ucenterMember = ucenterMemberMapper.selectOne(ucenterMemberQueryWrapper);
        return ucenterMember;
    }
}
