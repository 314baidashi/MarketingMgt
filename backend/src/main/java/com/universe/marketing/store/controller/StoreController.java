package com.universe.marketing.store.controller;

import com.universe.marketing.product.pojo.vo.Result;
import com.universe.marketing.store.mapper.ProductStoreMapper;
import com.universe.marketing.store.pojo.dto.PageParamDto;
import com.universe.marketing.store.pojo.dto.UpdateStoreDto;
import com.universe.marketing.store.service.Impl.StoreServiceImpl;
import com.universe.marketing.store.service.StoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/store")
@Api(value = "库存管理",tags = {"库存管理"})
public class StoreController {
    @Autowired
    private StoreService storeService;
    @Autowired
    private ProductStoreMapper storeMapper;

    @PostMapping("page/list")
    @ApiOperation(value = "分页模糊查询库存列表")
    public Result queryPageLikeList(@RequestBody PageParamDto pageParamDto){
        return storeService.queryPageStoreMSG(pageParamDto);
    }

    @PostMapping("update")
    @ApiOperation(value = "修改库存信息")
    public Result updateStore(@RequestBody UpdateStoreDto updateStoreDto){
        return storeService.updateStoreMSG(updateStoreDto);
    }

    @GetMapping("all")
    @ApiOperation(value = "查询全部数据")
    public Result queryAll(){
        return Result.succ(storeMapper.queryStoreMsg());
    }
}
