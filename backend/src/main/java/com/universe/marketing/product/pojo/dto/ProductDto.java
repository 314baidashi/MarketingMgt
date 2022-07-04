package com.universe.marketing.product.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 接受上传的商品信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private String productId;
    private String productName ;
    private String productDescribe;
    private Double productPricePurchase;
    private Double productPriceShipment;
    private Double productPriceSuggest;
    private String productFormat;
    private Integer state;
    private String personId;
    private String sortId;
}
