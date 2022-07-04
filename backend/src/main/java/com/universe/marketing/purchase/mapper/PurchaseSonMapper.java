package com.universe.marketing.purchase.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.universe.marketing.purchase.pojo.po.PurchaseSon;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PurchaseSonMapper extends BaseMapper<PurchaseSon> {
    //查询当前进货分表的列表
    List<PurchaseSon> queryNowPurchaseSonList(List<String> list);
}
