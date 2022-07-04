package com.universe.marketing.product.service;

import com.universe.marketing.product.pojo.vo.Result;

public interface SortService {
    //添加分类
    Result addSort(String sortName);
    //删除分类
    Result delSort(String sortId);
    //查询分类列表
    Result queryAllSort();
}
