package com.universe.marketing.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.universe.marketing.product.pojo.po.Product;
import com.universe.marketing.product.pojo.vo.ProductShowListVo;
import com.universe.marketing.product.pojo.vo.ProductSortListVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper extends BaseMapper<Product> {
    //查询商品的基础信息列表
    List<ProductSortListVo> queryProductList();
    //根据商品名称分页模糊查询商品列表
    List<ProductShowListVo> queryPageProductList(int pageSize, int pageNum, String query);
    //获取模糊查询的条数 total
    int queryPageProductCount(String query);
}
