package com.universe.marketing.purchase.pojo.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseSonDto {
    private String sort_id;
    private String productId;
    private Integer purchaseCount;
    private String productName;
    private String msg;
    private Double totalPrice;
}
