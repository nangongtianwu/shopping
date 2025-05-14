package com.longyi.shopping.controller;


import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.longyi.shopping.entity.Menu;
import com.longyi.shopping.service.MenuService;
import com.longyi.shopping.service.UserService;
import com.longyi.shopping.entity.User;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.longyi.shopping.common.QueryPageParam;
import com.longyi.shopping.common.Result;

/**
 * User 前端控制器
 *
 * @author 龙毅
 * @since 2024-11-14
 */
@RestController
@RequestMapping("/user")
public class UserController {
    //注入service
    @Autowired
    private UserService userService;
    @Autowired
    private MenuService menuService;

    //统计数量
    @GetMapping("/count")
    public Result count() {
        HashMap res = new HashMap<>();
        Integer userCount = userService.lambdaQuery().eq(User::getRole, 3).eq(User::getStatus, 1).count();
        Integer businessCount = userService.lambdaQuery().eq(User::getRole, 2).eq(User::getStatus, 1).count();
        Integer adminCount = userService.lambdaQuery().le(User::getRole, 1).eq(User::getStatus, 1).count();
        res.put("userCount", userCount);
        res.put("businessCount", businessCount);
        res.put("adminCount", adminCount);
        return Result.suc(res);
    }

    //列表
    @GetMapping("/list")
    public List<User> list() {
        return userService.list();
    }

    //获取头像接口
    @GetMapping("/getAvatar/{name}")
    public Result getAvatar(@PathVariable("name") String name) {
//        查询相关用户
        List<User> list = userService.lambdaQuery().eq(User::getUsername, name).list();
        if (list.size() > 0) {
            return Result.suc(list.get(0).getAvatar());
        }
        list = userService.lambdaQuery().eq(User::getName, name).list();
        if (list.size() > 0) {
            return Result.suc(list.get(0).getAvatar());
        }
        list = userService.lambdaQuery().eq(User::getEmail, name).list();
        if (list.size() > 0) {
            return Result.suc(list.get(0).getAvatar());
        }
        list = userService.lambdaQuery().eq(User::getTelephone, name).list();
        if (list.size() > 0) {
            return Result.suc(list.get(0).getAvatar());
        }
        return Result.fail("用户不存在");
    }

    //新增
    @PostMapping("/save")
    public Result save(@RequestBody User user) {
        List<User> list = userService.lambdaQuery().eq(User::getName, user.getName()).list();
        if (list.size() > 0) {
            return Result.fail("账号重复");
        }
        list = userService.lambdaQuery().eq(User::getUsername, user.getUsername()).list();
        if (list.size() > 0) {
            return Result.fail("用户名重复");
        }
        list = userService.lambdaQuery().eq(User::getEmail, user.getEmail()).list();
        if (list.size() > 0) {
            return Result.fail("邮箱重复");
        }
        list = userService.lambdaQuery().eq(User::getTelephone, user.getTelephone()).list();
        if (list.size() > 0) {
            return Result.fail("手机号重复");
        }
        return userService.save(user) ? Result.suc("新增成功") : Result.fail("新增失败");
    }

    //修改
    @PostMapping("/update")
    public Result update(@RequestBody User user) {
        List<User> list = userService.lambdaQuery().eq(User::getName, user.getName()).ne(User::getId, user.getId()).list();
        if (list.size() > 0) {
            return Result.fail("账号重复");
        }
        list = userService.lambdaQuery().eq(User::getUsername, user.getUsername()).ne(User::getId, user.getId()).list();
        if (list.size() > 0) {
            return Result.fail("用户名重复");
        }
        list = userService.lambdaQuery().eq(User::getEmail, user.getEmail()).ne(User::getId, user.getId()).list();
        if (list.size() > 0) {
            return Result.fail("邮箱重复");
        }
        list = userService.lambdaQuery().eq(User::getTelephone, user.getTelephone()).ne(User::getId, user.getId()).list();
        if (list.size() > 0) {
            return Result.fail("手机号重复");
        }
        user.setUpdateTime(LocalDateTime.now());
        return userService.updateById(user) ? Result.suc("修改成功") : Result.fail("修改失败");
    }

    @PostMapping("/modStatus/{id}")
    public Result modStatus(@PathVariable("id") Long id) {
        User user = userService.getById(id);
        if (user.getStatus() == 1) {
            user.setStatus(0);
        } else user.setStatus(1);
        return userService.updateById(user) ? Result.suc("修改成功") : Result.fail("修改失败");

    }
    @PostMapping("/repass/{id}")
    public Result repass(@PathVariable Integer id) {
        User user = userService.getById(id);
        user.setPassword("123456");
        return userService.updateById(user) ? Result.suc() : Result.fail();
    }

    //详情
    @GetMapping("/info/{id}")
    public Result info(@PathVariable("id") Long id) {
        User user = userService.getById(id);
        return Result.suc(user);
    }

    //删除
    @Transactional
    @PostMapping("/deleteBatch")
    public Result deleteBatch(@RequestBody Long[] ids) {
        userService.removeByIds(Arrays.asList(ids));
        return Result.suc("批量删除成功");
    }

    //分页查询
    @PostMapping("/listPage")
    public Result listPage(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();
        String name = (String) param.get("name");
        String username = (String) param.get("username");
        String telephone = (String) param.get("telephone");

        String email = (String) param.get("email");
        String sex = (String) param.get("sex");
        String role = (String) param.get("role");
        String status = (String) param.get("status");
        Page<User> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper();
        if (StringUtils.isNotBlank(name) && !"null".equals(name)) {
            lambdaQueryWrapper.like(User::getName, name);
        }
        if (StringUtils.isNotBlank(username) && !"null".equals(username)) {
            lambdaQueryWrapper.like(User::getUsername, username);
        }
        if (StringUtils.isNotBlank(telephone) && !"null".equals(telephone)) {
            lambdaQueryWrapper.like(User::getTelephone, telephone);
        }
        if (StringUtils.isNotBlank(email) && !"null".equals(email)) {
            lambdaQueryWrapper.like(User::getEmail, email);
        }
        if (StringUtils.isNotBlank(sex) && !"null".equals(sex)) {
            lambdaQueryWrapper.eq(User::getSex, sex);
        }
        if (StringUtils.isNotBlank(role) && !"null".equals(role)) {
            lambdaQueryWrapper.eq(User::getRole, role);
        }
        if (StringUtils.isNotBlank(status) && !"null".equals(status)) {
            lambdaQueryWrapper.eq(User::getStatus, status);
        }
        IPage result = userService.PageList(page, lambdaQueryWrapper);
        return Result.suc(result.getTotal(), result.getRecords());
    }

    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        User user1 = new User();
        if (StpUtil.isLogin()) {

            if (user.getRole() == 3) {
                user1 = userService.lambdaQuery().eq(User::getName, user.getName()).eq(User::getPassword, user.getPassword()).eq(User::getRole, 3).list().get(0);

            } else {
                user1 = userService.lambdaQuery().eq(User::getName, user.getName()).eq(User::getPassword, user.getPassword()).list().get(0);
            }
            StpUtil.login(user1.getId());
            List<Menu> menuList = menuService.lambdaQuery().like(Menu::getMenuright, user1.getRole()).list();
            HashMap res = new HashMap<>();
            res.put("user", user1);
            res.put("menu", menuList);
            return Result.suc(res);
        } else {
            List<User> list;
            if (user.getRole() == 3) {
                list = userService.lambdaQuery().eq(User::getName, user.getName()).eq(User::getPassword, user.getPassword()).eq(User::getRole, 3).list();

            } else {
                list = userService.lambdaQuery().eq(User::getName, user.getName()).eq(User::getPassword, user.getPassword()).list();
            }
            if (list.size() > 0) {
                user1=list.get(0);
                if (user1.getStatus() != 1) {
                    return Result.fail("账号已注销！");
                } else {
                    StpUtil.login(user1.getId());
                    List<Menu> menuList = menuService.lambdaQuery().like(Menu::getMenuright, user1.getRole()).list();
                    HashMap res = new HashMap<>();
                    res.put("user", user1);
                    res.put("menu", menuList);
                    return Result.suc(res);
                }

            }
            if (user.getRole() == 3) {
                list = userService.lambdaQuery().eq(User::getUsername, user.getName()).eq(User::getPassword, user.getPassword()).eq(User::getRole, 3).list();

            } else {
                list = userService.lambdaQuery().eq(User::getUsername, user.getName()).eq(User::getPassword, user.getPassword()).list();
            }
            if (list.size() > 0) {
                user1=list.get(0);
                if (user1.getStatus() != 1) {
                    return Result.fail("账号已注销！");
                } else {
                    StpUtil.login(user1.getId());
                    List<Menu> menuList = menuService.lambdaQuery().like(Menu::getMenuright, user1.getRole()).list();
                    HashMap res = new HashMap<>();
                    res.put("user", user1);
                    res.put("menu", menuList);
                    return Result.suc(res);
                }

            }
            if (user.getRole() == 3) {
                list = userService.lambdaQuery().eq(User::getTelephone, user.getName()).eq(User::getPassword, user.getPassword()).eq(User::getRole, 3).list();

            } else {
                list = userService.lambdaQuery().eq(User::getTelephone, user.getName()).eq(User::getPassword, user.getPassword()).list();
            }
            if (list.size() > 0) {
                user1 = list.get(0);
                if (user1.getStatus() != 1) {
                    return Result.fail("账号已注销！");
                } else {
                    StpUtil.login(user1.getId());
                    List<Menu> menuList = menuService.lambdaQuery().like(Menu::getMenuright, user1.getRole()).list();
                    HashMap res = new HashMap<>();
                    res.put("user", user1);
                    res.put("menu", menuList);
                    return Result.suc(res);
                }

            }
            if (user.getRole() == 3) {
                list = userService.lambdaQuery().eq(User::getEmail, user.getName()).eq(User::getPassword, user.getPassword()).eq(User::getRole, 3).list();

            } else {
                list = userService.lambdaQuery().eq(User::getEmail, user.getName()).eq(User::getPassword, user.getPassword()).list();
            }
            if (list.size() > 0) {
                user1 = list.get(0);
                if (user1.getStatus() != 1) {
                    return Result.fail("账号已注销！");
                } else {
                    StpUtil.login(user1.getId());
                    List<Menu> menuList = menuService.lambdaQuery().like(Menu::getMenuright, user1.getRole()).list();
                    HashMap res = new HashMap<>();
                    res.put("user", user1);
                    res.put("menu", menuList);
                    return Result.suc(res);
                }

            }

        }
        return Result.fail("账号不存在！");
    }

    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        List<User> list = userService.lambdaQuery().eq(User::getName, user.getName()).list();
        if (list.size() > 0) {
            return Result.fail("账号重复");
        }
        list = userService.lambdaQuery().eq(User::getUsername, user.getUsername()).list();
        if (list.size() > 0) {
            return Result.fail("用户名重复");
        }
        list = userService.lambdaQuery().eq(User::getEmail, user.getEmail()).list();
        if (list.size() > 0) {
            return Result.fail("邮箱重复");
        }
        list = userService.lambdaQuery().eq(User::getTelephone, user.getTelephone()).list();
        if (list.size() > 0) {
            return Result.fail("手机号重复");
        }
        return userService.save(user) ? Result.suc("注册成功") : Result.fail("注册失败");

    }

    @GetMapping("/modAvatar")
    public Result modAvatar(@RequestParam Integer id, String avatar) {
        User user = userService.getById(id);
        user.setAvatar(avatar);
        userService.updateById(user);
        return Result.suc(userService.getById(id));
    }

    @Transactional
    @GetMapping("/modName")
    public Result modName(@RequestParam Integer id, String name) {
        User user = userService.getById(id);
        user.setName(name);
        List list =
                userService.lambdaQuery().eq(User::getName, name).ne(User::getId, id).list();
        if (list.size() > 0) return Result.fail("账号已被注册，请换一个名字");
        else {
            userService.updateById(user);
            return Result.suc(userService.getById(id));
        }
    }

    @GetMapping("/modPwd")
    public Result modPwd(@RequestParam Integer id, String password) {
        User user = userService.getById(id);
        user.setPassword(password);
        userService.updateById(user);
        return Result.suc(userService.getById(id));
    }

    @GetMapping("/logout/{id}")
    public SaResult logout(@PathVariable Integer id) {
        StpUtil.logout(id);
        return SaResult.ok();
    }

    @GetMapping("/delUser/{id}")
    public SaResult delUser(@PathVariable Integer id) {
        User user = userService.getById(id);
        user.setStatus(0);
        userService.updateById(user);
        StpUtil.logout(id);
        return SaResult.ok();
    }
}
