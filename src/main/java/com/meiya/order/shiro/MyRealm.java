package com.meiya.order.shiro;

import com.meiya.order.dao.UserMapper;
import com.meiya.order.entity.User;
import com.meiya.order.util.MD5Util;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

public class MyRealm extends AuthorizingRealm {
    private static final Logger logger = LoggerFactory.getLogger("Console");
    @Resource
    UserMapper userMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        logger.info("username:" + username);
        //密码
        String password = new String((char[]) authenticationToken.getCredentials());
        logger.info("password:" + password);
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("loginName", username);
        User user = userMapper.selectUserByLoginName(username);

        if (user == null) {
            throw new AuthenticationException("账号不存在!");
        }
        if (!user.getLoginName().equals(username)) {
            throw new UnknownAccountException("账号错误!");
        }
        if (!user.getPassWord().equals(MD5Util.buildRequestMysign(password, "utf-8"))) {
            throw new IncorrectCredentialsException("密码错误!");
        }
        //身份验证通过,返回一个身份信息
        AuthenticationInfo aInfo = new SimpleAuthenticationInfo(username, password, getName());
        return aInfo;
    }

}
