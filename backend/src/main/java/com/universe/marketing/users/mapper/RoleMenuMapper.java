package com.universe.marketing.users.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.universe.marketing.users.entity.RoleMenu;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {
    @Select("select menu_id from role_menu where role_id = #{roleId}")
    List<String> selectByRoleId(@Param("roleId") String roleId);

    @Delete("delete from role_menu where role_id=#{roleId}")
    int deleteByRoleId(@Param("roleId") String roleId);
}
