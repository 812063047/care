package com.xzq.serviceEdu.controller;

import com.xzq.commonUtils.ResultMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@Api("登录")
@RestController
@RequestMapping("/eduservice/user")
//解决跨域问题
@CrossOrigin
public class EduLoginController {
    //login
    @ApiOperation("登录方法")
    @PostMapping("login")
    public ResultMessage login(){

        return  ResultMessage.ok().data("token","admin");
    }

    @ApiOperation("验证方法")
    @GetMapping("info")
    public ResultMessage info(){
        return  ResultMessage.ok().data("roles","[admin]").data("name","admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }


    @ApiOperation("退出")
    @GetMapping("logout")
    public ResultMessage logout(){
        return  ResultMessage.ok().data("token","").data("roles","");
    }

}
