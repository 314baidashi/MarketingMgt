package com.universe.marketing.users.vo;

import com.universe.marketing.users.entity.Menu;
import lombok.Data;

import java.util.List;

/**
 * 登录接收信息
 */
@Data
public class UserRequest {
    private String id;
    private String name;
    private String password;
    private String roleName;
    private String token;
    private List<Menu> menus;
}
