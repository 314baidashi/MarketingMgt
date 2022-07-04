package com.universe.marketing.users.web;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.universe.marketing.users.entity.Users;
import com.universe.marketing.users.entity.Utils.R;
import com.universe.marketing.users.service.UsersService;
import com.universe.marketing.users.vo.UserRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "用户管理", tags = {"用户管理"})
@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    UsersService usersService;

    @ApiOperation(value = "保存用户")
    @PostMapping("/save")
    public R save(@RequestBody Users users) {
        if (StringUtils.isEmpty(users.getId())) {
            Integer integer = (int) (Math.random() * 899999 + 100000);
            String password = integer.toString();
            users.setPassword(password);
            boolean flag = usersService.save(users);
            if (flag) {
                password = users.getPassword();
                String msg = "这是你的账号：" + users.getName() + "!  密码：" + users.getPassword();
                return R.ok(password, msg);
            } else {
                return R.fail("保存失败");
            }
        } else {
            usersService.save(users);
            return R.ok("账户已保存");
        }
    }

    @ApiOperation(value = "获取对应角色的用户")
    @GetMapping("/{role}")
    public R list_role(@PathVariable String role) {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_name", role);
        return R.ok(usersService.list(queryWrapper));
    }

    @ApiOperation(value = "获取该用户的菜单集合")
    @GetMapping("/getmenu")
    public R getUserMenu(@RequestBody UserRequest userRequest) {
        String roleName = userRequest.getRoleName();
        return R.ok(usersService.getUserMenu(roleName));
    }

    //    @ApiOperation(value = "登录")
//    @PostMapping("/login")
//    public R login(@RequestBody UserRequest userRequest) {
//        String name = userRequest.getName();
//        String password = userRequest.getPassword();
//        if (StrUtil.isBlank(name) || StrUtil.isBlank(password)) {
//            throw new BusinessException("用户名或密码为空");
//        }
//        if(usersService.login(userRequest)!=null){
//            return R.ok(usersService.login(userRequest));
//        }else{
//            throw new BusinessException("用户名或密码错误");
//        }
//    }
    @ApiOperation(value = "重置密码")
    @PutMapping("/reset/{userId}")
    public R resetPwd(@PathVariable String userId) {
        return R.ok(usersService.resetPwd(userId), "重置后的密码");
    }


    @ApiOperation(value = "更改密码")
    @PutMapping("/set/{userId}")
    public R setPwd(@PathVariable String userId,@RequestParam String password) {
        boolean flag = usersService.setPwd(userId,password);
        if (flag) {
            return R.ok("更改成功");
        } else {
            return R.fail("更改失败");
        }
    }

    @ApiOperation(value = "删除用户")
    @DeleteMapping("/delete/{id}")
    public R delete(@PathVariable String id) {
        boolean flag = usersService.removeById(id);
        if (flag) {
            return R.ok("删除成功");
        } else {
            return R.fail("删除失败");
        }
    }

    @ApiOperation(value = "批量删除用户")
    @PostMapping("/delbatch")
    public R delete(@RequestBody List<String> ids) {
        boolean flag = usersService.removeByIds(ids);
        if (flag) {
            return R.ok("批量删除成功");
        } else {
            return R.fail("删除失败");
        }
    }


    @ApiOperation(value = "分页查询用户")
    @GetMapping("/query")
    //条件查询
    public IPage<Users> find(
            @RequestParam Integer pageNum,
            @RequestParam Integer pageSize,
            @RequestParam(defaultValue = "") String roleName,
            @RequestParam(defaultValue = "") String telnumber,
            @RequestParam(defaultValue = "") String realname) {
        IPage<Users> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        if (!"".equals(roleName)) {
            queryWrapper.like("role_name", roleName);
        }
        if (!"".equals(realname)) {
            queryWrapper.like("real_name", realname);
        }
        if (!"".equals(telnumber)) {
            queryWrapper.like("tel_number", telnumber);
        }
        return usersService.page(page, queryWrapper);

    }

    @ApiOperation(value = "更新用户")
    @PutMapping("/update")
    public R update(@RequestBody Users user) {
        boolean flag = usersService.updateById(user);
        if (flag) {
            return R.ok("更新成功");
        } else {
            return R.fail("更新失败");
        }
    }

}
