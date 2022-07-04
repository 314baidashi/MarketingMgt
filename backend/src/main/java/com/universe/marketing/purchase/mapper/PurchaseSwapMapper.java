package com.universe.marketing.purchase.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.universe.marketing.purchase.pojo.po.PurchaseSwap;
import com.universe.marketing.purchase.pojo.vo.PurchaseSonVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PurchaseSwapMapper extends BaseMapper<PurchaseSwap> {
    //绑定进货表和进货分表
    int insertSwap(@Param("purchaseId")String purchaseId, List<String> list);
    //通过进货表来查询进货分表的列表信息
    List<PurchaseSonVo> queryPurchaseSonById(String purchaseId, List<String> list, int pageNum, int pageSize);
    //查询符合条件的进货子表数目
    int queryPurchaseSonCount(List<String> purchaseIds);
}
