package com.universe.marketing.login.controller;

import com.universe.marketing.login.pojo.dto.LoginDto;
import com.universe.marketing.login.service.LoginService;
import com.universe.marketing.product.pojo.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@Api(value = "登录",tags = {"登录"})
public class LoginController {
    @Autowired
    LoginService loginService;

    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public Result login(@RequestBody LoginDto loginDto){
        return loginService.login(loginDto);
    }

    @ApiOperation(value = "退出登录")
    @GetMapping("loginOut")
    public Result logout(@RequestHeader("Authorization") String token){
        return loginService.loginOut(token);
    }

    /**
     * 获取用户信息
     * @param token （头部信息）
     * @return
     */
    @ApiOperation(value = "获取用户信息")
    @PostMapping("/user/current")
    public Result currentUser(@RequestHeader("Authorization") String token){
        return loginService.findUserByToken(token);
    }
}
