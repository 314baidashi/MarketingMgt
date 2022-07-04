package com.universe.marketing.product.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductSortListVo {
    private String productName;
    private Double productPricePurchase;
    private String sortName;
    private String productId;
}
