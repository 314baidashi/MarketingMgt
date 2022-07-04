package com.universe.marketing.purchase.pojo.vo;

import com.universe.marketing.purchase.pojo.po.Purchase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class PurchaseListVo {
    private Integer total;
    private List<Purchase> pageList;
}
