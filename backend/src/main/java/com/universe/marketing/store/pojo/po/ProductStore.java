package com.universe.marketing.store.pojo.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("product_store")
public class ProductStore {
    @TableId
    @TableField("store_id")
    private String storeId;
    @TableField("product_id")
    String productId;
    @TableField("product_name")
    private String productName;
    @TableField("total")
    Integer total;
    @TableField("update_datetime")
    private Date updateDatetime;
    @TableField("state")
    private Integer state;
    @TableField("create_datetime")
    private Date create_datetime;
    @TableField("sort_id")
    private String sortId;
}
