package com.universe.marketing.login.service.Impl;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.universe.marketing.login.mapper.UserMapper;
import com.universe.marketing.login.pojo.dto.LoginDto;
import com.universe.marketing.login.pojo.po.User;
import com.universe.marketing.login.service.LoginService;
import com.universe.marketing.product.pojo.vo.Result;
import com.universe.utils.ChUtils.JWTUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@Transactional //事务回滚 理应加载接口类上 这里只有一个实现类
public class LoginServiceImpl implements LoginService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private static final String slat = "chh!@#";

    @Override
    public Result login(LoginDto loginDto) {
        if(StringUtils.isBlank(loginDto.getUsername()) || StringUtils.isBlank(loginDto.getUsername())){
            return Result.fail("用户名或密码为空");
        }
        //String password = DigestUtils.md5Hex(loginDto.getPassword() + slat);
        User existUser = isExistUser(loginDto.getUsername(), loginDto.getPassword());
        if(existUser==null) {
            return new Result().fail("用户名或密码错误");
        }
        String token = JWTUtils.createToken(existUser.getId());
        redisTemplate.opsForValue().set("TOKEN_" + token, JSON.toJSONString(existUser), 1, TimeUnit.DAYS);
        return Result.succ("登录成功！", token);
    }

    /**
     * 检擦用户名密码是否正确
     * @param username
     * @param password
     * @return
     */
    private User isExistUser(String username, String password) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getName, username);
        wrapper.eq(User::getPassword, password);
        return userMapper.selectOne(wrapper);
    }

    @Override
    public Result loginOut(String token) {
        redisTemplate.delete("TOKEN_" + token);
        return Result.succ("成功退出！");
    }

    @Override
    public Result findUserByToken(String token) {
        User user = checkToken(token);
        if(user!=null){
            return Result.succ(user);
        }
        return Result.fail();
    }

    /**
     * 检查token是否正确
     * @param token
     * @return
     */
    public User checkToken(String token) {
        //验证Token
        if(StringUtils.isBlank(token)){//是否为空
            return null;
        }
        Map<String, Object> stringObjectMap = JWTUtils.checkToken(token);
        if(stringObjectMap == null){
            return null;
        }
        String userJson = redisTemplate.opsForValue().get("TOKEN_" + token);
        if(StringUtils.isBlank(userJson)){
            return null;
        }
        User user = JSON.parseObject(userJson, User.class);
        return user;
    }
}
