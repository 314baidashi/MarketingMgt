package com.universe.marketing.users.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

@Data
@TableName("menu")
public class Menu {
    @TableId(value = "id",type = IdType.INPUT)
    private String id;
    //子Id
    private String pid;
    private String name;
    private String path;
    @TableField(exist = false)
    private List<Menu> children;
    private String description;

}
