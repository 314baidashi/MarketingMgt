package com.universe.marketing.users.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.universe.marketing.users.entity.Menu;
import com.universe.marketing.users.entity.Roles;
import com.universe.marketing.users.mapper.MenuMapper;
import com.universe.marketing.users.service.Impl.MenuServiceImpl;
import com.universe.utils.UUIDUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuService extends ServiceImpl<MenuMapper,Menu> implements MenuServiceImpl {
    @Resource
    private MenuService menuService;
    @Resource
    MenuMapper menuMapper;
    @Transactional(rollbackFor = Exception.class)
    public boolean save(Menu menu) {
        if (StringUtils.isEmpty(menu.getId())) {
            menu.setId(UUIDUtils.generateId());
            menuMapper.insert(menu);
            return true;
        } else {
             menuMapper.updateById(menu);
            return true;
        }
    }


    public List<Menu> findMeus(String name) {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(name)){
            queryWrapper.like("name",name);
        }

        List<Menu> list = list(queryWrapper);
        //找出pid一级菜单

        List<Menu> parentNodes = list.stream().filter(menu -> menu.getPid() == null).collect(Collectors.toList());
        //一级菜单的子菜单
        for(Menu menu : parentNodes){
            menu.setChildren(list.stream().filter(m -> menu.getId().equals(m.getPid())).collect(Collectors.toList()));
        }
        return parentNodes;
    }

}
