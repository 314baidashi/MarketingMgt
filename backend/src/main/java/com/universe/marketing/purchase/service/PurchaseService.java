package com.universe.marketing.purchase.service;

import com.universe.marketing.product.pojo.vo.Result;
import com.universe.marketing.purchase.pojo.dto.PurchaseDto;
import com.universe.marketing.purchase.pojo.dto.PurchaseSonDto;
import com.universe.marketing.purchase.pojo.dto.QueryPurchasePageDto;
import com.universe.marketing.purchase.pojo.dto.QueryPurchaseSonListDto;

import java.text.ParseException;
import java.util.List;

public interface PurchaseService {
    //上传进货分表数据
    Result insertPurchaseSonMsg(PurchaseSonDto purchaseSonDto);
    //上传整体进货表的数据
    Result insertPurchaseMsg(PurchaseDto purchaseDto);
    //添加进货主表和分表的关联信息
    int insertPurchaseSwap(String purchaseId, List<String>purchaseSonList);
    //根据主表查询对应子表的信息
    Result queryPurchaseSonById(QueryPurchaseSonListDto queryDto);
    //分页查询进货总表的列表信息（根据负责人和生成时间）
    Result queryPurchasePage(QueryPurchasePageDto queryDto) throws ParseException;
}
