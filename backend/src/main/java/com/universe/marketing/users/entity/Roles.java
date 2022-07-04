package com.universe.marketing.users.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("roles")
public class Roles {

  @TableId(value = "id",type = IdType.INPUT)
  private String id;
  private String name;
  private String keyname;
  private String description;


}
