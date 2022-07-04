package com.universe.config.Login;


import com.universe.marketing.users.entity.Users;
import com.universe.marketing.users.mapper.UsersMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private UsersMapper usersMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String name = request.getHeader("name");
        if (StringUtils.isBlank(name)) {
            return false;
        }
        Users users = usersMapper.selectByUsername(name);
        if (users == null) {
            return false;
        }
        return true;
    }
}
