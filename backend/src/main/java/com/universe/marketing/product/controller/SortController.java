package com.universe.marketing.product.controller;


import com.universe.marketing.product.pojo.vo.Result;
import com.universe.marketing.product.service.SortService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("sort")
@Api(value = "分类管理",tags = {"分类管理"})
public class SortController {

    @Autowired
    private SortService sortService;

    @ApiOperation(value = "添加分类")
    @PostMapping("add")
    public Result addSort(@RequestParam String sortName){
        return sortService.addSort(sortName);
    }

    @ApiOperation(value = "删除分类")
    @PostMapping("del/{sortId}")
    public Result delSort(@PathVariable("sortId") String sortId){
        return sortService.delSort(sortId);
    }

    @ApiOperation(value = "查询分类列表")
    @GetMapping("list")
    public Result allSort(){
        return sortService.queryAllSort();
    }

}
