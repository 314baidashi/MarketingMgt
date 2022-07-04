package com.universe.marketing.store.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.universe.marketing.purchase.pojo.po.PurchaseSon;
import com.universe.marketing.store.pojo.po.ProductStore;
import com.universe.marketing.store.pojo.vo.StoreVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductStoreMapper extends BaseMapper<ProductStore> {
    List<StoreVo> queryPageStoreMSG(int pageSize, int pageNum);
    //分页模糊查询库存的信息列表
    List<StoreVo> queryPageLikeStoreMSG(String query, int pageSize, int pageNum);
    //查询所有
    List<StoreVo> queryStoreMsg();
    //查询total总数
    int queryTotalStore(String query);
    //更新库存的数量
    int updateStoreByPurchaseSon(String pId, Integer count);
    //查询商品总数
    int queryStoreCount(String productId);
}
