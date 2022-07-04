package com.universe.marketing.users.service;

import cn.hutool.core.collection.CollUtil;
import com.universe.marketing.users.entity.Menu;
import com.universe.marketing.users.entity.RoleMenu;
import com.universe.marketing.users.mapper.MenuMapper;
import com.universe.marketing.users.mapper.RoleMenuMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoleMenuService {
    @Resource
    RoleMenuMapper roleMenuMapper;
    @Resource
    MenuMapper menuMapper;


    @Transactional
    public boolean setRoleMenu(String roleId, List<String> menuIds) {
        roleMenuMapper.deleteByRoleId(roleId);
        List<String> menuIdsCopy = CollUtil.newArrayList(menuIds);
        for(String menuId : menuIds){
            Menu menu = menuMapper.selectById(menuId);
            if(menu.getPid()!=null&&!menuIdsCopy.contains(menu.getPid())){
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.setRoleId(roleId);
                //要传menu的子ID 不能传主键
                roleMenu.setMenuId(menu.getPid());
                roleMenuMapper.insert(roleMenu);
                menuIdsCopy.add(menu.getPid());
            }
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRoleId(roleId);
            roleMenu.setMenuId(menuId);
            roleMenuMapper.insert(roleMenu);
        }
        return true;
    }



    public List<String> getRoleMenu(String roleId) {
        return roleMenuMapper.selectByRoleId(roleId);
    }

}
