package com.universe.marketing.purchase.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.universe.marketing.purchase.pojo.po.Purchase;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PurchaseMapper extends BaseMapper<Purchase> {
    //分页模糊查询进货表的列表（根据开始时间和负责人 选填）
    List<Purchase> queryPageList(int pageNum, int pageSize, String person, String beginTime);
    //查询符合条件的列表个数 total
    int queryPageListCount(String person, String beginTime);
}
