package com.universe.marketing.store.pojo.dto;

import lombok.Data;

@Data
public class PageParamDto {
    private int pageSize = 10;//每页显示条数
    private int pageNum = 0;//每页个数
    private String query;

}
