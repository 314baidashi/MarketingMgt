package com.universe.marketing.purchase.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryPurchaseSonListDto {
    private int pageSize = 1;//每页显示条数
    private int pageNum = 10;//每页个数
    private String purchaseId;
}
