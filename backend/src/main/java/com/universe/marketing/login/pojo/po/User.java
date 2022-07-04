package com.universe.marketing.login.pojo.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@TableName("users")
public class User {
    private String id;
    private String enabled;
    private String name;
    private String password;
    private long telNumber;
    private String realName;
    private String roleName;
}
