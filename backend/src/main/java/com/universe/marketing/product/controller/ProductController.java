package com.universe.marketing.product.controller;

import com.universe.marketing.product.mapper.ProductMapper;
import com.universe.marketing.product.pojo.dto.PageParamDto;
import com.universe.marketing.product.pojo.dto.ProductDto;
import com.universe.marketing.product.pojo.vo.Result;
import com.universe.marketing.product.service.Impl.ProductServiceImpl;
import com.universe.marketing.product.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/product")
@Api(value = "产品管理",tags = {"产品管理"})
public class ProductController {
    @Autowired
    private ProductService productService;

    @ApiOperation(value = "添加产品")
    @PostMapping("/add")
    public Result addProduct(@RequestBody ProductDto productDto){
        return productService.addProduct(productDto);
    }

    @ApiOperation(value = "修改产品信息")
    @PostMapping("/update")
    public Result updateProduct(@RequestBody ProductDto productDto){
        return productService.updateProduct(productDto);
    }

    @ApiOperation(value = "删除产品信息")
    @PostMapping("/del/{productId}")
    public Result delProduct(@PathVariable("productId") String productId){
        return productService.delProductById(productId);
    }

    @ApiOperation(value = "查询产品名称和种类列表")
    @GetMapping("/list")
    public Result queryList(){
        return Result.succ(productService.queryProductList());
    }

    @ApiOperation(value = "分页查询产品列表")
    @PostMapping("/query/page")
    public Result queryPageProductList(@RequestBody PageParamDto pageParamDto){
        return productService.queryPageProduceList(pageParamDto);
    }
}
