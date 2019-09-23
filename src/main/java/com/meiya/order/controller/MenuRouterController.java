package com.meiya.order.controller;

import com.meiya.order.entity.MenuRouter;
import com.meiya.order.service.MenuRouterService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


@RequestMapping("/menuRouter")
@RestController
public class MenuRouterController {
    @Resource
    MenuRouterService menuRouterService;

    @RequestMapping("/getMenuRouter")
    public Object getMenuRouter() {
        Long startTime = System.currentTimeMillis();
        List<MenuRouter> menuRouters = menuRouterService.getMenuRouterByParentId(0);
        System.out.println("查询耗时:" + (System.currentTimeMillis() - startTime));
        return menuRouters;
    }

}
