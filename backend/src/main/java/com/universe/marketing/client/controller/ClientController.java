package com.universe.marketing.client.controller;

import com.github.pagehelper.PageInfo;
import com.universe.marketing.client.pojo.dto.ClientDto;
import com.universe.marketing.client.pojo.entity.ClientEntity;
import com.universe.marketing.client.pojo.vo.Result;
import com.universe.marketing.client.service.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Api(value = "客户信息管理",tags = {"客户信息管理"})
@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private ClientService clientService;
    @ApiOperation(value = "分页查询客户信息")
    @PostMapping("/select")
    public PageInfo<ClientDto> query(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "#{T(java.lang.Integer).MAX_VALUE}") int pageSize) {
        return clientService.query(pageNum, pageSize);
    }

    @ApiOperation(value = "添加客户信息")
    @PostMapping("/add")
    public Result insertClient(ClientDto clientDto){
        return clientService.insertClient(clientDto);
    }
    @ApiOperation(value = "修改客户信息")
    @PostMapping("/update")
    public Result updateClient(ClientDto clientDto){
        Result flag = clientService.updateClient(clientDto);
        return flag;
    }
    @ApiOperation(value = "删除客户信息")
    @PostMapping("/delete")
    public Result deleteClient(String clientId){
        Result flag = clientService.deleteClient(clientId);
        return flag;
    }
    @ApiOperation(value = "查询客户信息")
    @GetMapping("/query")
    public ClientEntity selectClient(String clientId){
         return clientService.selectClient(clientId);
    }
}