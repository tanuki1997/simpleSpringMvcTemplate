package com.meiya.order.controller;

import com.meiya.order.common.BaseResponse;
import com.meiya.order.service.UserService;
import com.meiya.order.vo.UserVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@RequestMapping("/user")
@Controller()
public class UserController {
    @Resource
    UserService userService;

    @RequestMapping("/addUser")
    @ResponseBody
    public BaseResponse<UserVo> addUser(UserVo userVo) {
        userVo.setUserName("陈家亮");
        userVo.setPassWord("123456");
        userVo.setLoginName("cjl");
        BaseResponse<UserVo> baseResponse = userService.addUser(userVo);
        return baseResponse;
    }


}
