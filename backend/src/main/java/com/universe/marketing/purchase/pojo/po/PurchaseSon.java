package com.universe.marketing.purchase.pojo.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("purchase_sort")
public class PurchaseSon {
    @TableId
    @TableField("id")
    private String id;
    @TableField("sort_id")
    private String sort_id;
    @TableField("purchase_count")
    Integer purchaseCount;
    @TableField("purchase_state")
    private Integer purchaseState;
    @TableField("manage_datetime")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date manageDatetime;
    @TableField("msg")
    private String msg;
    @TableField("total_price")
    private double totalPrice;
    @TableField("purchase_name")
    private String purchaseName;
    @TableField("product_id")
    private String productId;

}
