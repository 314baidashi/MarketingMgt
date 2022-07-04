package com.universe.marketing.users.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.universe.marketing.users.entity.Menu;

import com.universe.marketing.users.entity.Utils.R;
import com.universe.marketing.users.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;


@Api(value = "菜单管理", tags = {"菜单管理"})
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Resource
    MenuService menuService;

   /* @ApiOperation(value = "获取父子信息")
    @GetMapping("/list")
    public R list(@RequestParam(defaultValue = "") String name){
        return R.ok(menuService.findMeus(name));
    }*/
    @ApiOperation(value = "获取角色的全部菜单ID")
    @GetMapping("/ids")
    public R MenuIds() {
        return R.ok(menuService.list().stream().map(Menu::getId));
    }

    @ApiOperation(value = "保存菜单")
    @PostMapping("/save")
    public R save(@RequestBody Menu menu) {
        boolean flag = menuService.save(menu);
        if (flag) {
            return R.ok("添加成功");
        } else {
            return R.fail("添加失败");
        }
    }

    @ApiOperation(value = "更新菜单")
    @PutMapping("/update")
    public R update(@RequestBody Menu menu) {
        boolean flag = menuService.updateById(menu);
        if (flag) {
            return R.ok("更新成功");
        } else {
            return R.fail("更新失败");
        }
    }

    @ApiOperation(value = "删除菜单")
    @DeleteMapping("/delete/{id}")
    public R delete(@PathVariable String id) {
        boolean flag = menuService.removeById(id);
        if (flag) {
            return R.ok("删除成功");
        } else {
            return R.fail("删除失败");
        }
    }
/*    @GetMapping("/lists")
    public R List() {
        return R.ok(menuService.list(null));
    }*/
    @ApiOperation(value = "分页查询菜单接口")
    @GetMapping("/query")
    public IPage<Menu> find(
            @RequestParam Integer pageNum,
            @RequestParam Integer pageSize,
            @RequestParam(defaultValue = "") String name
    ){
        IPage<Menu> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        if(!"".equals(name)){
            queryWrapper.like("name",name);
        }
        return menuService.page(page, queryWrapper);

    }
}
