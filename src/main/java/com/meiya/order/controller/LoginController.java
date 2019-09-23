package com.meiya.order.controller;

import com.meiya.order.common.BaseResponse;
import com.meiya.order.entity.User;
import com.meiya.order.vo.UserVo;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger("Console");

    @PostMapping(value = "/login")
    @ResponseBody
    public Object login(UserVo user) {
        BaseResponse baseResponse = new BaseResponse();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getLoginName(), user.getPassWord());
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (UnknownAccountException uae) {
            logger.info(uae.getMessage());
            baseResponse.setCode("1");
            baseResponse.setMsg(uae.getMessage());
        } catch (IncorrectCredentialsException ice) {
            logger.info(token.getPrincipal() + " 的密码不正确!");
            baseResponse.setCode("1");
            baseResponse.setMsg(ice.getMessage());
        } catch (LockedAccountException lae) {
            logger.info(token.getPrincipal() + " 被锁定 ，请联系管理员");
            baseResponse.setCode("1");
            baseResponse.setMsg(" 被锁定 ，请联系管理员");
        } catch (AuthenticationException ae) {
            //其他未知的异常
            baseResponse.setCode("1");
            baseResponse.setMsg("未知异常");
        }
        baseResponse.setCode("true");
        baseResponse.setMsg("登录成功");
        return baseResponse;
    }

    @RequestMapping("/logout")
    @ResponseBody
    public Object logout(User user) {
        System.out.println(JSONObject.fromObject(user).toString());
        Map<String, String> userMap = new HashMap<>();
        userMap.put("code", "TRUE");
        userMap.put("msg", "登錄成功");
        return userMap;
    }

}
