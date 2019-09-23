package com.meiya.order.service;

import com.meiya.order.entity.MenuRouter;

import java.util.List;

public interface MenuRouterService {
    public List<MenuRouter> getMenuRouterByParentId(Integer parentId);

    public String insertTestTx();
}
