package com.universe.marketing.purchase.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.universe.marketing.product.pojo.vo.Result;
import com.universe.marketing.purchase.mapper.PurchaseMapper;
import com.universe.marketing.purchase.mapper.PurchaseSonMapper;
import com.universe.marketing.purchase.mapper.PurchaseSwapMapper;
import com.universe.marketing.purchase.pojo.dto.PurchaseDto;
import com.universe.marketing.purchase.pojo.dto.PurchaseSonDto;
import com.universe.marketing.purchase.pojo.dto.QueryPurchasePageDto;
import com.universe.marketing.purchase.pojo.dto.QueryPurchaseSonListDto;
import com.universe.marketing.purchase.pojo.po.Purchase;
import com.universe.marketing.purchase.pojo.po.PurchaseSon;
import com.universe.marketing.purchase.pojo.po.PurchaseSwap;
import com.universe.marketing.purchase.pojo.vo.PurchaseListVo;
import com.universe.marketing.purchase.pojo.vo.PurchaseSonPageVo;
import com.universe.marketing.purchase.pojo.vo.PurchaseSonVo;
import com.universe.marketing.purchase.service.PurchaseService;
import com.universe.marketing.store.mapper.ProductStoreMapper;
import com.universe.utils.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    PurchaseMapper purchaseMapper;
    @Autowired
    PurchaseSonMapper purchaseSonMapper;
    @Autowired
    PurchaseSwapMapper purchaseSwapMapper;
    @Autowired
    ProductStoreMapper productStoreMapper;
    @Override
    public Result insertPurchaseSonMsg(PurchaseSonDto purchaseSonDto) {
        PurchaseSon purchaseSon = new PurchaseSon();
        BeanUtil.copyNotNullBean(purchaseSonDto, purchaseSon);
        purchaseSon.setId(UUID.randomUUID().toString());
        purchaseSon.setPurchaseName(purchaseSonDto.getProductName());
        if(purchaseSonMapper.insert(purchaseSon)>0){
            return Result.succ("进货分表添加成功！", purchaseSon.getId());
        }
        return Result.fail("进货分表添加失败！");
    }

    @Override
    @Transactional
    public Result insertPurchaseMsg(PurchaseDto purchaseDto) {
        double total = 0;
        Purchase purchase = new Purchase();
        BeanUtil.copyNotNullBean(purchaseDto, purchase);
        purchase.setPurchaseId(UUID.randomUUID().toString());
        //获取子项的id列表
        List<String> purchaseSonList = purchaseDto.getPurchaseSonList();


        //取得当前上传中的进货子项列表product_id, purchase_count, total_price
        List<PurchaseSon> list = purchaseSonMapper.queryNowPurchaseSonList(purchaseSonList);


        //将其加入库存中，修改库存
        for(PurchaseSon data:list){
            total+=data.getTotalPrice();
            //获取当前库存product_id, purchase_count
            int count = productStoreMapper.queryStoreCount(data.getProductId());
            if(productStoreMapper.updateStoreByPurchaseSon(data.getProductId(), data.getPurchaseCount()+count)<1){
                return Result.fail("修改库存失败！");
            }
        }
        //添加总表价格
        purchase.setTotalPrice(total);
        if(purchaseMapper.insert(purchase)==0){
            return Result.fail("插入进货主表失败！");
        }
        if(insertPurchaseSwap(purchase.getPurchaseId(), purchaseDto.getPurchaseSonList())==0){
            return Result.fail("绑定进货主分表信息失败！");
        }
        return Result.succ("添加进货成功！");
    }

    @Override
    public int insertPurchaseSwap(String purchaseId, List<String> purchaseSonList) {
        return purchaseSwapMapper.insertSwap(purchaseId, purchaseSonList);
    }

    @Override
    public Result queryPurchaseSonById(QueryPurchaseSonListDto queryDto) {
        List<String> purchaseIds = new ArrayList<>();
        //查询该主表下分表的id列表
        LambdaQueryWrapper<PurchaseSwap> swapWrapper = new LambdaQueryWrapper<>();
        swapWrapper.select(PurchaseSwap::getId);
        swapWrapper.eq(PurchaseSwap::getPurchaseId, queryDto.getPurchaseId());
        List<PurchaseSwap> purchaseSwaps = purchaseSwapMapper.selectList(swapWrapper);
        //取出分表id列表
        for(PurchaseSwap data:purchaseSwaps){
            purchaseIds.add(data.getId().trim());
        }
        //根据分表id查询数据
        List<PurchaseSonVo> list = purchaseSwapMapper.queryPurchaseSonById(queryDto.getPurchaseId(),
                purchaseIds,
                queryDto.getPageNum(),
                queryDto.getPageNum()*(queryDto.getPageSize()-1));

        int total = purchaseSwapMapper.queryPurchaseSonCount(purchaseIds);
        return Result.succ(new PurchaseSonPageVo(total, list));
    }

    @Override
    public Result queryPurchasePage(QueryPurchasePageDto queryDto) throws ParseException {
        String time1 = queryDto.getBeginTime();
        /*String time="";
        Long time1=null;
        if(!"".equals(queryDto.getBeginTime())){
            time = queryDto.getBeginTime();
            Date parse = new SimpleDateFormat("yyyy-MM-dd").parse(time);
            time1 = parse.getTime();
        }*/
        System.out.println(time1);
        List<Purchase> list = purchaseMapper.queryPageList(queryDto.getPageNum(),
                queryDto.getPageNum()*(queryDto.getPageSize()-1), queryDto.getPerson(), time1);
        int total = purchaseMapper.queryPageListCount(queryDto.getPerson(), time1);
        return Result.succ(new PurchaseListVo(total, list));
    }

}
