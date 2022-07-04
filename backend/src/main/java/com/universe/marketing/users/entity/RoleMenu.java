package com.universe.marketing.users.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("role_menu")
public class RoleMenu {
    private String roleId;
    private String menuId;
}
