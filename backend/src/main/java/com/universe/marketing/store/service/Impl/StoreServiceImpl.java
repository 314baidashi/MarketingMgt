package com.universe.marketing.store.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.universe.marketing.product.pojo.vo.Result;
import com.universe.marketing.store.mapper.ProductStoreMapper;
import com.universe.marketing.store.pojo.dto.PageParamDto;
import com.universe.marketing.store.pojo.dto.UpdateStoreDto;
import com.universe.marketing.store.pojo.po.ProductStore;
import com.universe.marketing.store.pojo.vo.StoreAllVo;
import com.universe.marketing.store.service.StoreService;
import com.universe.utils.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    ProductStoreMapper storeMapper;

    @Override
    public Result queryPageStoreMSG(PageParamDto pageParamDto) {
        StoreAllVo res = new StoreAllVo();
        //获取返回的列表
        res.setList(storeMapper.queryPageLikeStoreMSG(pageParamDto.getQuery(),
                (pageParamDto.getPageSize()-1)*pageParamDto.getPageNum(), pageParamDto.getPageNum()));
        //获取分页查询的total
        res.setTotal(storeMapper.queryTotalStore(pageParamDto.getQuery()));
        return Result.succ(res);
    }

    @Override
    public Result updateStoreMSG(UpdateStoreDto updateStoreDto) {
        ProductStore productStore = new ProductStore();
        BeanUtil.copyNotNullBean(updateStoreDto, productStore);
        //添加修改时间属性
        productStore.setUpdateDatetime(new Date());
        LambdaQueryWrapper<ProductStore> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ProductStore::getStoreId, updateStoreDto.getStoreId());
        //修改
        if(storeMapper.update(productStore, wrapper)>0){
            return Result.succ("修改库存成功！");
        }
        return Result.fail("修改库存失败！");
    }


}
