package com.universe.marketing.login.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.universe.marketing.login.pojo.po.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
