package com.universe.marketing.users.web;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.universe.marketing.users.entity.Roles;
import com.universe.marketing.users.entity.Users;
import com.universe.marketing.users.entity.Utils.R;
import com.universe.marketing.users.service.RoleMenuService;
import com.universe.marketing.users.service.RolesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(value = "角色管理", tags = {"角色权限"})
@RestController
@RequestMapping("/roles")
public class RolesController {

    @Resource
    RolesService rolesService;
    @Resource
    private RoleMenuService roleMenuService;

    @ApiOperation(value = "分页查询角色")
    @GetMapping("/query")
    public IPage<Roles> find(
            @RequestParam Integer pageNum,
            @RequestParam Integer pageSize
    ) {
        IPage<Roles> page = new Page<>(pageNum, pageSize);
        return rolesService.page(page);
    }

    @ApiOperation(value = "保存角色")
    @PostMapping("/save")
    public R save(@RequestBody Roles roles) {
        boolean flag = rolesService.save(roles);
        if (flag) {
            return R.ok("添加成功");
        } else {
            return R.fail("添加失败");
        }
    }

    @ApiOperation(value = "删除角色")
    @DeleteMapping("/delete/{id}")
    public R delete(@PathVariable String id) {
        boolean flag = rolesService.removeById(id);
        if (flag) {
            return R.ok("删除成功");
        } else {
            return R.fail("删除失败");
        }
    }

    @ApiOperation(value = "获取角色菜单ID")
    @GetMapping("/roleMenu/{roleId}")
    public R getRoleMenu(@PathVariable String roleId) {
        List<String> list = roleMenuService.getRoleMenu(roleId);
        return R.ok(list);
    }

    @ApiOperation(value = "更新角色")
    @PutMapping("/update")
    public R update(@RequestBody Roles role) {
        boolean flag = rolesService.updateById(role);
        if (flag) {
            return R.ok("更新成功");
        } else {
            return R.fail("更新失败");
        }
    }

    @ApiOperation(value = "设置角色菜单ID")
    @PutMapping("/roleMenu/{roleId}")
    public R roleMenu(@PathVariable String roleId, @RequestBody List<String> menuIds) {
        boolean flag = roleMenuService.setRoleMenu(roleId, menuIds);
        if (flag) {
            return R.ok("设置成功");
        } else {
            return R.fail("设置失败");
        }
    }

    @ApiOperation(value = "批量删除菜单")
    @PostMapping("/delbatch")
    public R delete(@RequestBody List<String> ids) {
        boolean flag = rolesService.removeByIds(ids);
        if (flag) {
            return R.ok("批量删除成功");
        } else {
            return R.fail("删除失败");
        }
    }


}
