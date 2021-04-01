package com.xzq.serviceUcenter.controller;

import com.google.gson.Gson;
import com.xzq.commonUtils.jwt.JwtUtils;
import com.xzq.serviceBase.Exception.CareException;
import com.xzq.serviceUcenter.entity.UcenterMember;
import com.xzq.serviceUcenter.service.UcenterMemberService;
import com.xzq.serviceUcenter.utils.ConstantPropertiesUtil;
import com.xzq.serviceUcenter.utils.HttpClientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

@CrossOrigin
@Controller
@RequestMapping("/api/ucenter/wx")
public class WxApiController {

    @Autowired
    private UcenterMemberService ucenterMemberService;

    @GetMapping("/wxlogin")
    public String getConnect(HttpSession httpSession){
        // 微信开放平台授权baseUrl
        String baseUrl = "https://open.weixin.qq.com/connect/qrconnect" +
        "?appid=%s" +
        "&redirect_uri=%s" +
        "&response_type=code" +
        "&scope=snsapi_login" +
        "&state=%s" +
        "#wechat_redirect";
        // 回调地址
        String redirectUrl = ConstantPropertiesUtil.WX_OPEN_REDIRECT_URL; //获取业务服务器重定向地址
        try {
            redirectUrl = URLEncoder.encode(redirectUrl, "UTF-8"); //url编码
        } catch (UnsupportedEncodingException e) {
            throw new CareException(20001, e.getMessage());
        }
        String state = "care";
        String qrcodeUrl = String.format(
                baseUrl,
                ConstantPropertiesUtil.WX_OPEN_APP_ID,
                redirectUrl,
                state);
        return "redirect:" + qrcodeUrl;
    }
    @GetMapping("callback")
    public String callback(String code, String state){
        try{
            String baseAccessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";
            String accessTokenUrl = String.format(
                    baseAccessTokenUrl,
                    ConstantPropertiesUtil.WX_OPEN_APP_ID,
                    ConstantPropertiesUtil.WX_OPEN_APP_SECRET,
                    code
            );
            String accessTokenOpen = HttpClientUtils.get(accessTokenUrl);
            Gson gson = new Gson();
            HashMap map = gson.fromJson(accessTokenOpen, HashMap.class);
            String openid =(String) map.get("openid");
            String accessToken  =(String) map.get("access_token");
            //根据openid判断数据库是否存在该用户用微信登录过
            UcenterMember ucenterMember = ucenterMemberService.getUcenterMemberByOpenId(openid);
            String token = "";
            if(ucenterMember == null){
                //如果不存在先注册后登录
                String baseUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s";
                String userInfoUrl  = String.format(
                        baseUserInfoUrl,
                        accessToken,
                        openid
                );
                String userInfoUrlString = HttpClientUtils.get(userInfoUrl);
                HashMap userInfoMap = gson.fromJson(userInfoUrlString, HashMap.class);
                System.err.println(userInfoMap);
                String nickname = (String)userInfoMap.get("nickname");
                String headimgurl = (String)userInfoMap.get("headimgurl");
                Object sex = userInfoMap.get("sex");
                int age = 0;
                if(sex.equals(1.0)){
                    age =1;
                }else if(sex.equals(2.0)){
                    age =2;
                }
                UcenterMember centerMember = new UcenterMember();
                centerMember.setNickname(nickname);
                centerMember.setAvatar(headimgurl);
                centerMember.setOpenid(openid);
                centerMember.setAge(age);
                ucenterMemberService.save(centerMember);
                token = JwtUtils.getJwtToken(centerMember.getId(),centerMember.getNickname());
            }else{
                token = JwtUtils.getJwtToken(ucenterMember.getId(),ucenterMember.getNickname());
            }
            return "redirect:http://localhost:3000?token="+token;
        }
        catch (Exception e){
            throw new CareException(20001,"登录失败");
        }
    }
}
