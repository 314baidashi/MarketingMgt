package com.universe.marketing.product.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductPageListVo {
    private Integer total;
    private List<ProductShowListVo> productShowList;
}
