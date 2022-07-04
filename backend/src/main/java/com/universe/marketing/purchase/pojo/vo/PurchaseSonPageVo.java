package com.universe.marketing.purchase.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseSonPageVo {
    private Integer total;
    List<PurchaseSonVo> purchaseSonList;
}
