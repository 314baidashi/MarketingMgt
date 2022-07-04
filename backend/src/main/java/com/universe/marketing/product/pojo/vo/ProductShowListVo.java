package com.universe.marketing.product.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductShowListVo {
    private String productId;
    private String productName;
    private String productDescribe;
    private Double productPricePurchase; //进价
    private Double productPriceShipment;//出货价
    private Double productPriceSuggest;//建议零售价
    private String productFormat;
    private Integer state;
    private Date createDatetime;
    private Date updateDatetime;
    private String name;
    private String sortName;
}
