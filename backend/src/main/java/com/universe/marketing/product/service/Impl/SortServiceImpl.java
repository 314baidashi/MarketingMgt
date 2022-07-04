package com.universe.marketing.product.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.universe.marketing.product.mapper.SortMapper;
import com.universe.marketing.product.pojo.po.Sort;
import com.universe.marketing.product.pojo.vo.Result;
import com.universe.marketing.product.service.SortService;
import com.universe.utils.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SortServiceImpl implements SortService {

    @Autowired
    private SortMapper sortMapper;

    @Override
    public Result addSort(String sortName) {

        Sort sort = new Sort(UuidUtil.getUuid(), sortName);
        System.out.println(sort);
        if(sortMapper.insert(sort)>0){
            return Result.succ("添加分类成功！");
        }
        return Result.fail("添加分类失败！");
    }

    @Override
    public Result delSort(String sortId) {
        LambdaQueryWrapper<Sort> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Sort::getSortId, sortId);
        if(sortMapper.delete(wrapper)>0){
            return Result.succ("删除分类成功！");
        }
        return Result.fail("删除分类失败！");
    }

    @Override
    public Result queryAllSort() {
        return Result.succ(sortMapper.selectList(null));
    }
}
