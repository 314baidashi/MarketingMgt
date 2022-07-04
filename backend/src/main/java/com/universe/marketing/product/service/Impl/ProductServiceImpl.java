package com.universe.marketing.product.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.universe.marketing.product.mapper.ProductMapper;
import com.universe.marketing.product.pojo.dto.ProductDto;
import com.universe.marketing.product.pojo.dto.PageParamDto;
import com.universe.marketing.product.pojo.po.Product;
import com.universe.marketing.product.pojo.vo.ProductPageListVo;
import com.universe.marketing.product.pojo.vo.ProductShowListVo;
import com.universe.marketing.product.pojo.vo.ProductSortListVo;
import com.universe.marketing.product.pojo.vo.Result;
import com.universe.marketing.product.service.ProductService;
import com.universe.utils.UuidUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public Result addProduct(ProductDto productDto) {
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        product.setProductId(UuidUtil.getUuid());
        //添加
        if(productMapper.insert(product)>0){
            return Result.succ("商品添加成功！");
        }
        return Result.fail("商品添加失败！请检查参数");
    }

    @Override
    public Result delProductById(String productId) {
        LambdaQueryWrapper<Product> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Product::getProductId, productId);
        //删除
        if(productMapper.delete(wrapper)>0){
            return Result.succ("删除成功！");
        }
        return Result.fail("删除失败！");
    }

    @Override
    public Result updateProduct(ProductDto productDto) {
        LambdaQueryWrapper<Product> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Product::getProductId, productDto.getProductId());
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        product.setUpdateDatetime(new Date());
        //修改
        if(productMapper.update(product, wrapper)>0){
            return Result.succ("商品修改成功！");
        }
        return Result.fail("商品修改失败！请检查参数");
    }

    @Override
    public Result queryPageProduceList(PageParamDto pageParamDto) {
        //查询total
        int total = productMapper.queryPageProductCount(pageParamDto.getQuery());
        //获取符合条件的商品列表
        List<ProductShowListVo> list = productMapper.queryPageProductList(pageParamDto.getPageNum()*(pageParamDto.getPageSize()-1),
                pageParamDto.getPageNum(),
                pageParamDto.getQuery());
        return Result.succ(new ProductPageListVo(total, list));
    }

    @Override
    public Result queryProductList() {
        List<ProductSortListVo> list = productMapper.queryProductList();
        return Result.succ(list);
    }
}
