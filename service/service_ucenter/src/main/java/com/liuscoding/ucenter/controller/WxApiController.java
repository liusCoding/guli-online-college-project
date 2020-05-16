package com.liuscoding.ucenter.controller;

import com.google.gson.Gson;
import com.liuscoding.commonutils.JwtUtils;
import com.liuscoding.commonutils.result.ResultCode;
import com.liuscoding.servicebase.exceptionhandler.exception.GuliException;
import com.liuscoding.ucenter.constants.WxOpenConstants;
import com.liuscoding.ucenter.entity.Member;
import com.liuscoding.ucenter.service.MemberService;
import com.liuscoding.ucenter.utlis.HttpClientUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Objects;

/**
 * @className: WxApiController
 * @description:
 * @author: liusCoding
 * @create: 2020-05-17 02:54
 */

@CrossOrigin
@Controller
@RequestMapping("/api/ucenter/wx")
public class WxApiController {

    private final MemberService memberService;

    public WxApiController(MemberService memberService) {
        this.memberService = memberService;
    }

    /**
     * 1.生成微信扫描二维码
     */

    @GetMapping("/login")
    public String getWxCode(){
        //固定地址，后面拼接参数
//        String url = "https://open.weixin.qq.com/" +
//                "connect/qrconnect?appid="+ ConstantWxUtils.WX_OPEN_APP_ID+"&response_type=code";

        // 微信开放平台授权baseUrl  %s相当于?代表占位符
        String baseUrl = "https://open.weixin.qq.com/connect/qrconnect" +
                "?appid=%s" +
                "&redirect_uri=%s" +
                "&response_type=code" +
                "&scope=snsapi_login" +
                "&state=%s" +
                "#wechat_redirect";

        //对redirect_url进行URLEncoder编码
        String redirectUrl = WxOpenConstants.WX_OPEN_REDIRECT_URL;
        try {
            redirectUrl = URLEncoder.encode(redirectUrl, "utf-8");
        }catch(Exception e) {
        }

        //设置%s里面值
        String url = String.format(
                baseUrl,
                WxOpenConstants.WX_OPEN_APP_ID,
                redirectUrl,
                "liuscoding"
        );

        //重定向到请求微信地址里面
        return "redirect:"+url;
    }

    /**
     * 2.获取扫描人信息、添加信息
     */

    @GetMapping("/callback")
    public String callBack(String code,String state){


        try {
            //1.获取code值，临时票据，类似于验证码
            //2.拿着code请求，微信固定的地址，得到两个值 access_token 和openId

            String baseAccessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token" +
                    "?appid=%s" +
                    "&secret=%s" +
                    "&code=%s" +
                    "&grant_type=authorization_code";
            //拼接三个参数 ：id  秘钥 和 code值
            String accessTokenUrl = String.format(
                    baseAccessTokenUrl,
                    WxOpenConstants.WX_OPEN_APP_ID,
                    WxOpenConstants.WX_OPEN_APP_SECRET,
                    code
            );

            //请求这个拼接好的地址，得到返回两个值 access_token 和 openId
            //使用httpclient发送请求，得到返回结果

            String accessTokenInfo = HttpClientUtils.get(accessTokenUrl);
            //从accessTokenInfo 字符串出来两个值 accessToken 和 openId
            //把accessTokenInfo 字符串转换成map集合，根据map里面可以 获取对应的值

            //使用json转换工具
            Gson gson = new Gson();
            HashMap<String,String> accessTokenMap = gson.fromJson(accessTokenInfo, HashMap.class);

            String accessToken = accessTokenMap.get("access_token");

            String openid = accessTokenMap.get("openid");

            //把扫描人信息添加到数据库里面
            //判断数据库表里面是否存在相同微信信息，根据openid判断
            Member member = memberService.getMemberByOpenid(openid);

            if(Objects.isNull(member)){
                //3.拿着得到access_token和openid ,再去请求微信提供的固定地址，获取到扫描人信息
                // 访问微信的资源服务器，获取到用户信息

                String baseUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo" +
                        "?access_token=%s" +
                        "&openid=%s";
                //拼接两个参数
                String userInfoUrl = String.format(
                        baseUserInfoUrl,
                        accessToken,
                        openid
                );

                //发送请求
                String userInfo = HttpClientUtils.get(userInfoUrl);

                //获取返回的userInfo信息
                HashMap<String, String> userInfoMap = gson.fromJson(userInfo, HashMap.class);

                //昵称
                String nickname = userInfoMap.get("nickname");
                //头像
                String headimgurl = userInfoMap.get("headimgurl");

                member = new Member();
                member.setOpenid(openid);
                member.setNickname(nickname);
                member.setAvatar(headimgurl);
                memberService.save(member);
            }

            //使用jwt根据member对象生成token字符串
            String token = JwtUtils.getJwtToken(member.getId(), member.getNickname());

            //最后: 返回首页面，通过路径传递token字符串

            return "redirect:http://localhost:3000?token="+token;

        } catch (Exception e) {
            e.printStackTrace();

            throw GuliException.from(ResultCode.LOGIN_ERROR);
        }

    }

}
