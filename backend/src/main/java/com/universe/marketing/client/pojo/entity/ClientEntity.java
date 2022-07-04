package com.universe.marketing.client.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 实体类
 */
@Data
@TableName("client_manage")
public class ClientEntity {
    @TableId
    private String clientId;
    private String clientName;
    private String clientPhone;
    private String clientAddress;

}
