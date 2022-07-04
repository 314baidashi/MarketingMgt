package com.universe.marketing.login.service;

import com.universe.marketing.login.pojo.dto.LoginDto;
import com.universe.marketing.product.pojo.vo.Result;

public interface LoginService {
    Result login(LoginDto loginDto);
    Result loginOut(String token);
    Result findUserByToken(String token);
}
