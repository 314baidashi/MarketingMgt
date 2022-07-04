package com.universe.marketing.users.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.universe.handler.BusinessException;
import com.universe.marketing.users.entity.Menu;
import com.universe.marketing.users.entity.Users;
import com.universe.marketing.users.mapper.RoleMenuMapper;
import com.universe.marketing.users.mapper.RolesMapper;
import com.universe.marketing.users.mapper.UsersMapper;
import com.universe.marketing.users.service.Impl.UserServiceImpl;
import com.universe.utils.UUIDUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yuanjs
 * @description:
 * @date 2020-02-05 7:23
 */
@Service
public class UsersService extends ServiceImpl<UsersMapper,Users> implements UserServiceImpl {
    @Autowired
    UsersMapper usersMapper;
    @Resource
    RolesService rolesService;

    @Transactional(rollbackFor = Exception.class)
    public boolean save(Users users) {
        Users old = usersMapper.selectByUsername(users.getName());
        if(old!=null){
            if(!old.getId().equals(users.getId())){
                throw new BusinessException("用户名已被占用");
            }
        }
        if (StrUtil.isEmpty(users.getId())) {
            users.setId(UUIDUtils.generateId());
            usersMapper.insert(users);
        } else {
            users.setPassword(old.getPassword());
            usersMapper.updateById(users);
        }
        return true;
    }


    public String resetPwd(String userId){
        Users user = usersMapper.selectById(userId);

        Integer integer=(int) (Math.random()*899999+100000);
        String password=integer.toString();
        user.setPassword(password);
        usersMapper.updatepwd(user.getName(),password);
        return password;
    }
    //获取用户菜单
    public List<Menu> getUserMenu(String roleName) {
        return rolesService.getRoleMenu(roleName);
    }


    //更改密码
    @Transactional(rollbackFor = Exception.class)
    public Boolean setPwd(String userId, String password) {
        Users user = usersMapper.selectById(userId);
        if (user != null) {
            usersMapper.updatepwd(user.getName(),password);
            return true;
        }
       return false;
    }


//    public UserRequest login(UserRequest userRequest) {
//        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("name",userRequest.getName());
//        queryWrapper.eq("password",userRequest.getPassword());
//        try{
//            Users a = getOne(queryWrapper);
//            if(a!=null){
//                BeanUtil.copyProperties(a,userRequest,true);
//                String token = UUIDUtils.generateId();
//                userRequest.setToken(token);
//                //获取role的字符串
//                String role = a.getRoleName();
//                //设置菜单列表
//                List<Menu> roleMenu = getRoleMenu(role);
//                userRequest.setMenus(roleMenu);
//                return userRequest;
//            }
//            else{
//                throw new BusinessException("用户名或密码错误");
//            }
//        }catch (Exception e){
//            return null;
//        }
//    }




}
