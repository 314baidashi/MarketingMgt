package com.universe.marketing.product.pojo.dto;

import lombok.Data;

@Data
public class PageParamDto {
    private int pageSize = 1;//每页显示条数
    private int pageNum = 10;//每页个数
    private String query;

}
