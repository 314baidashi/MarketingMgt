package com.universe.marketing.users.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.universe.marketing.users.entity.Users;
import com.universe.marketing.users.vo.UsersParams;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UsersMapper extends BaseMapper<Users> {



    @Select("update users set password= #{password,jdbcType=VARCHAR} where name= #{name,jdbcType=VARCHAR}")
    void updatepwd(@Param("name") String name, @Param("password") String pwd);

    @Select("select * from users where name = #{name}")
    Users selectByUsername(@Param("name") String name);

}