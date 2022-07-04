package com.universe.marketing.store.service;

import com.universe.marketing.product.pojo.vo.Result;
import com.universe.marketing.store.pojo.dto.PageParamDto;
import com.universe.marketing.store.pojo.dto.UpdateStoreDto;

public interface StoreService {
    //分页模糊查询库存信息
    Result queryPageStoreMSG(PageParamDto pageParamDto);
    //修改库存信息
    Result updateStoreMSG(UpdateStoreDto updateStoreDto);
}
