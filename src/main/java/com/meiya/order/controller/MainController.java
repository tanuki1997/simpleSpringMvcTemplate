package com.meiya.order.controller;

import com.meiya.order.mapper.UserMapperExt;
import com.meiya.order.service.MenuRouterService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class MainController {
    @Resource
    MenuRouterService menuRouterService;

    @Resource
    UserMapperExt userMapperExt;

    @RequestMapping("/")
    public String main() {
        return "start success!";
    }

    @RequestMapping("/test")
    public String test() {
//        Example example = new Example(User.class);
//        Example.Criteria criteria = example.createCriteria();
//        criteria.andEqualTo("loginName", "11111");
//        User user =  userMapperExt.selectByExample(example);
        return menuRouterService.insertTestTx();
    }
}
