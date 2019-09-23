package com.meiya.order.service;

import com.meiya.order.common.BaseResponse;
import com.meiya.order.vo.UserVo;

public interface UserService {
    public BaseResponse addUser(UserVo user);
}
