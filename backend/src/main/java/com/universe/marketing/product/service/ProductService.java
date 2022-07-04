package com.universe.marketing.product.service;

import com.universe.marketing.product.pojo.dto.ProductDto;
import com.universe.marketing.product.pojo.dto.PageParamDto;
import com.universe.marketing.product.pojo.vo.Result;

public interface ProductService {
    //添加商品
    Result addProduct(ProductDto productDto);
    //删除商品
    Result delProductById(String productId);
    //更新商品
    Result updateProduct(ProductDto productDto);
    //分页查询商品
    Result queryPageProduceList(PageParamDto pageParamDto);
    //查询商品的名称喝种类名称
    Result queryProductList();
}
