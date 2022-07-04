package com.universe.marketing.store.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreAllVo {
    private int total;
    private List<StoreVo> list;
}
