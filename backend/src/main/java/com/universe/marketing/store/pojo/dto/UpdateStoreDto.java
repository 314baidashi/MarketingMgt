package com.universe.marketing.store.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStoreDto {
    private String storeId;
    private String productId;
    private String productName;
    private Integer total;
    private Integer state;
    private String sortId;
}
