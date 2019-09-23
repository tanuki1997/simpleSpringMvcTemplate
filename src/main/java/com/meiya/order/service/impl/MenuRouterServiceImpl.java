package com.meiya.order.service.impl;

import com.meiya.order.dao.MenuRouterMapper;
import com.meiya.order.entity.MenuRouter;
import com.meiya.order.mapper.MenuRouterMapperExt;
import com.meiya.order.service.MenuRouterService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("menuRouterService")
public class MenuRouterServiceImpl implements MenuRouterService {
    @Resource
    MenuRouterMapper menuRouterMapper;
    @Resource
    MenuRouterMapperExt menuRouterMapperExt;

    public List<MenuRouter> getMenuRouterByParentId(Integer parentId) {
        List<MenuRouter> menuRouters = menuRouterMapper.selectByParentId(parentId);
        return menuRouters;
    }

    public String insertTestTx() {
        System.out.println(222);

        MenuRouter menuRouter = new MenuRouter();
        menuRouter.setId(18);
        menuRouter.setMenuCode("dsadsa");
        menuRouterMapperExt.insert(menuRouter);
        if (true) {
            throw new RuntimeException("123");
        }
        return "success";
    }

}
