package com.universe.marketing.purchase.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDto {
    private String msg;
    private String purchaseChargePersonId;
    private List<String> purchaseSonList;
    private Double totalPrice;
}
