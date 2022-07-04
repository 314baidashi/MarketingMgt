package com.universe.marketing.users.service;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.universe.marketing.users.entity.Menu;
import com.universe.marketing.users.entity.Roles;

import com.universe.marketing.users.mapper.MenuMapper;
import com.universe.marketing.users.mapper.RoleMenuMapper;
import com.universe.marketing.users.mapper.RolesMapper;
import com.universe.marketing.users.service.Impl.RoleServiceImpl;
import com.universe.utils.UUIDUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Service
public class RolesService extends ServiceImpl<RolesMapper,Roles> implements RoleServiceImpl {

    @Autowired
    RolesMapper rolesMapper;
    @Resource
    private RoleMenuMapper roleMenuMapper;
    @Resource
    private MenuService menuService;

    @Transactional(rollbackFor = Exception.class)
    public boolean save(Roles roles) {
        if (StringUtils.isEmpty(roles.getId())) {
            roles.setId(UUIDUtils.generateId());
            rolesMapper.insert(roles);
            return true;
        } else {
            rolesMapper.updateById(roles);
            return true;
        }
    }
    /**
     * 获取当前菜单列表
     * @param keyname 角色唯一标识
     *
     */
    public List<Menu> getRoleMenu(String keyname){
        String roleId = rolesMapper.selectByRoleName(keyname);
        // 当前角色的所有菜单id集合
        List<String> menuIds = roleMenuMapper.selectByRoleId(roleId);

        // 查出系统所有的菜单(树形)
        List<Menu> menus = menuService.findMeus("");
        // new一个最后筛选完成之后的list
        List<Menu> roleMenus = new ArrayList<>();
        // 筛选当前用户角色的菜单
        for (Menu menu : menus) {
            if (menuIds.contains(menu.getId())) {
                roleMenus.add(menu);
            }
            List<Menu> children = menu.getChildren();
            // removeIf()  移除 children 里面不在 menuIds集合中的 元素
            children.removeIf(child -> !menuIds.contains(child.getId()));
        }
        return roleMenus;
    }
}
