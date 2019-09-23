package com.meiya.order.service.impl;

import com.meiya.order.common.BaseResponse;
import com.meiya.order.entity.User;
import com.meiya.order.enums.ResponseCodeEnum;
import com.meiya.order.mapper.UserMapperExt;
import com.meiya.order.service.UserService;
import com.meiya.order.util.MD5Util;
import com.meiya.order.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserMapperExt userMapperExt;
    @Override
    public BaseResponse addUser(UserVo userVo) {
        BaseResponse baseResponse = new BaseResponse();
        User user = new User();
        BeanUtils.copyProperties(userVo,user);
        user.setPassWord(MD5Util.buildRequestMysign(user.getPassWord(), "utf-8"));
        int i = userMapperExt.insert(user);
        if (i > 0) {
            baseResponse.setCode(ResponseCodeEnum.SUCCESS.code);
            baseResponse.setMsg("添加成功");
        } else {
            baseResponse.setCode(ResponseCodeEnum.FAIL.code);
            baseResponse.setCode("添加失败");
        }
        return baseResponse;
    }
}
