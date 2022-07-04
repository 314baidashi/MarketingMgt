package com.universe.marketing.purchase.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.universe.marketing.product.pojo.vo.Result;
import com.universe.marketing.purchase.mapper.PurchaseMapper;
import com.universe.marketing.purchase.pojo.dto.PurchaseDto;
import com.universe.marketing.purchase.pojo.dto.PurchaseSonDto;
import com.universe.marketing.purchase.pojo.dto.QueryPurchasePageDto;
import com.universe.marketing.purchase.pojo.dto.QueryPurchaseSonListDto;
import com.universe.marketing.purchase.pojo.po.Purchase;
import com.universe.marketing.purchase.service.PurchaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@CrossOrigin
@RestController
@RequestMapping("/purchase")
@Api(value = "进货管理",tags = {"进货管理"})
public class PurchaseController {

    @Autowired
    PurchaseService purchaseService;

    @PostMapping("/son/add")
    @ApiOperation(value = "添加进货分表信息")
    public Result addPurchaseSon(@RequestBody PurchaseSonDto purchaseSonDto){
        return purchaseService.insertPurchaseSonMsg(purchaseSonDto);
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加进货总表信息")
    public Result addPurchase(@RequestBody PurchaseDto purchaseDto){
        return purchaseService.insertPurchaseMsg(purchaseDto);
    }

    @PostMapping("/son/list")
    @ApiOperation(value = "根据进货总表Id查询对应信息")
    public Result queryPurchaseSonList(@RequestBody QueryPurchaseSonListDto queryDto){
        return purchaseService.queryPurchaseSonById(queryDto);
    }

    @PostMapping("/page/list")
    @ApiOperation(value = "分页模糊查询进货总表信息")
    public Result queryPurchasePageList(@RequestBody QueryPurchasePageDto queryDto) throws ParseException {
        return purchaseService.queryPurchasePage(queryDto);
    }

}
