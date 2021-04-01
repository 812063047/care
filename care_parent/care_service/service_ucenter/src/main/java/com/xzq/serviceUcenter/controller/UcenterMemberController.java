package com.xzq.serviceUcenter.controller;


import com.xzq.commonUtils.ResultMessage;
import com.xzq.commonUtils.jwt.JwtUtils;
import com.xzq.serviceBase.Exception.CareException;
import com.xzq.serviceUcenter.entity.UcenterMember;
import com.xzq.serviceUcenter.entity.utilsVo.LoginVo;
import com.xzq.serviceUcenter.entity.utilsVo.RegisterVo;
import com.xzq.serviceUcenter.service.UcenterMemberService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author care
 * @since 2021-03-11
 */
@RestController
@RequestMapping("/ucenterservice/ucentermember")
@CrossOrigin
public class UcenterMemberController {
    @Autowired
    private UcenterMemberService ucenterMemberService;

    @ApiOperation(value = "会员登录")
    @PostMapping("/login")
    public ResultMessage login(@RequestBody LoginVo loginVo){
        String token =ucenterMemberService.login(loginVo);
        return ResultMessage.ok().data("token",token);
    }
    @ApiOperation(value = "会员注册")
    @PostMapping("/register")
    public ResultMessage register(@RequestBody RegisterVo registerVo){
        Integer result = ucenterMemberService.register(registerVo);
        if(result>0) {
            return ResultMessage.ok();
        }else{
            return ResultMessage.error();
        }
    }

    @ApiOperation(value = "根据token获取登录信息")
    @GetMapping("/getLoginInfo")
    public ResultMessage getLoginInfo(HttpServletRequest request){
        try{
            String memberId = JwtUtils.getMemberIdByJwtToken(request);
            UcenterMember ucenterMember = ucenterMemberService.getById(memberId);
            return ResultMessage.ok().data("ucenterMember",ucenterMember);
        }catch (Exception e){
            e.printStackTrace();
            throw new CareException(20001,"error");
        }
    }

}

