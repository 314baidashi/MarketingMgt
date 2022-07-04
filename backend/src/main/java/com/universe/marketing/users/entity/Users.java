package com.universe.marketing.users.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;


@Data
@TableName("users")
public class Users {

  @TableId(value = "id",type = IdType.INPUT)
  private String id;
  private String name;
  @JsonIgnore
  private String password;
  private String realName;
  private String telNumber;
  private Integer enabled;
  private String roleName;

}