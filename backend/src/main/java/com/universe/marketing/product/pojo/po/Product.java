package com.universe.marketing.product.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "product_manage")
public class Product {
    @TableId(type = IdType.ASSIGN_UUID)
    @TableField("product_id")
    private String productId;

    @TableField("product_name")
    private String productName;

    @TableField("product_describe")
    private String productDescribe;

    @TableField("product_price_purchase")
    private Double productPricePurchase; //进价

    @TableField("product_price_shipment")
    private Double productPriceShipment;//出货价

    @TableField("product_price_suggest")
    private Double productPriceSuggest;//建议零售价

    @TableField("product_format")
    private String productFormat;

    @TableField("state")
    private Integer state;

    @TableField("create_datetime")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDatetime;

    @TableField("update_datetime")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateDatetime;

    @TableField("person_id")
    private String personId;

    @TableField("sort_id")
    private String sortId;
}
