package com.universe.marketing.purchase.pojo.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("purchase_swap")
public class PurchaseSwap {
    @TableId
    @TableField("id")
    private String id;
    @TableField("purchase_id")
    private String purchaseId;
}
