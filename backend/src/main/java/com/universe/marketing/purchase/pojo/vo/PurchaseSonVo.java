package com.universe.marketing.purchase.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class PurchaseSonVo {
    private String id;
    private String sortName;
    private Integer purchaseCount;
    private Integer purchaseState;
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date manageDatetime;
    private String msg;
    private String purchaseName;
    private double totalPrice;

}
