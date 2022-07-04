package com.universe.marketing.users.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.universe.marketing.users.entity.Roles;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


import java.util.List;

/**
 * @author yuanjs
 * @description:
 * @date 2020-04-02 19:19
 */
@Mapper
public interface RolesMapper extends BaseMapper<Roles> {
    @Select("select id from roles where keyname = #{keyname}")
    String selectByRoleName(@Param("keyname") String role);
}
