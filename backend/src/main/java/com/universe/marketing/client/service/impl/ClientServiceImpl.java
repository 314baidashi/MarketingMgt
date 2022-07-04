package com.universe.marketing.client.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.universe.marketing.client.mapper.ClientMapper;
import com.universe.marketing.client.pojo.dto.ClientDto;
import com.universe.marketing.client.pojo.entity.ClientEntity;
import com.universe.marketing.client.pojo.vo.Result;
import com.universe.marketing.client.service.ClientService;
import com.universe.utils.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientMapper clientMapper;

    @Override
    public Result insertClient(ClientDto clientDto) {
        ClientEntity clientEntity = new ClientEntity();
        BeanUtils.copyProperties(clientDto,clientEntity);
        clientEntity.setClientId(UUIDUtils.generateId());
        if(clientMapper.insert(clientEntity)>0){
            return Result.success("客户信息添加成功");
        }return  Result.fail("客户信息添加失败");
    }

    @Override
    public Result deleteClient(String clientId) {
        LambdaQueryWrapper<ClientEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ClientEntity::getClientId,clientId);
        int id = clientMapper.delete(wrapper);
        if(id>0){
            return Result.success("客户信息删除成功");
        }return  Result.fail("客户信息删除失败");
    }

    @Override
    public Result updateClient(ClientDto clientDto) {

        String id = clientDto.getClientId();
        ClientEntity client = new ClientEntity();
        client.setClientId(id);
        BeanUtils.copyProperties(clientDto,client);
        LambdaQueryWrapper<ClientEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ClientEntity::getClientId, id);

        if(clientMapper.update(client, wrapper)>0){
            return Result.success("客户信息修改成功");
        }return  Result.fail("客户信息修改失败");
    }


    @Override
    public PageInfo<ClientDto> query(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo(clientMapper.selectList(null));
    }

    @Override
    public ClientEntity selectClient(String clientId) {
        LambdaQueryWrapper<ClientEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ClientEntity::getClientId,clientId);
        return clientMapper.selectOne(wrapper);
    }

}


