package com.universe.marketing.client.service;

import com.github.pagehelper.PageInfo;
import com.universe.marketing.client.pojo.dto.ClientDto;
import com.universe.marketing.client.pojo.entity.ClientEntity;
import com.universe.marketing.client.pojo.vo.Result;


public interface ClientService {
    //添加客户信息
    Result insertClient(ClientDto clientDto);
    //删除客户信息
    Result deleteClient(String clientId);
    //修改客户信息
    Result updateClient(ClientDto clientDto);
    //分页查询
    PageInfo<ClientDto> query(int pageNum, int pageSize);
    //查询客户信息
    ClientEntity selectClient(String clientId);
}
