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
@TableName("purchase")
public class Purchase {
    @TableId
    @TableField("purchase_id")
    private String purchaseId;
    @TableField("create_datetime")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDatetime;
    @TableField("purchase_charge_person_id")
    private String purchaseChargePersonId;
    @TableField("purchase_state")
    private Integer purchaseState;
    @TableField("msg")
    private String msg;
    @TableField("total_price")
    private Double totalPrice;
}
