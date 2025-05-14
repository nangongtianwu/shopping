package com.longyi.shopping.controller;


import com.longyi.shopping.entity.User;
import com.longyi.shopping.service.RechargeService;
import com.longyi.shopping.entity.Recharge;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.longyi.shopping.service.UserService;
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
* Recharge 前端控制器
*
* @author 龙毅
* @since 2024-11-14
*/
@RestController
@RequestMapping("/recharge")
public class RechargeController {
    //注入service
    @Autowired
    private  RechargeService rechargeService;
    @Autowired
    private UserService userService;

    //统计数量
    @GetMapping("/count")
    public Integer count() {
       return rechargeService.count();
    }
    //列表
    @GetMapping("/list")
    public List<Recharge> list() {
        return rechargeService.list();
    }
    //新增

    @Transactional
    @PostMapping("/save")
    public Result save(@RequestBody Recharge recharge) {
        User user = userService.getById(recharge.getUser());
        user.setAmount(user.getAmount() + recharge.getMoney());
        user.setBalance(user.getBalance() + recharge.getMoney());
        user.setUpdateTime(LocalDateTime.now());
        userService.updateById(user);
        return rechargeService.save(recharge) ? Result.suc("新增成功") : Result.fail("新增失败");

    }
    //修改
    @PostMapping("/update")
    public Result update(@RequestBody Recharge recharge) {
       return rechargeService.updateById(recharge)? Result.suc("修改成功") : Result.fail("修改失败");
    }
    //详情
    @GetMapping("/info/{id}")
    public Result info(@PathVariable("id") Long id) {
        Recharge recharge = rechargeService.getById(id);
        return Result.suc(recharge);
    }
    //删除
    @Transactional
    @PostMapping("/deleteBatch")
    public Result deleteBatch(@RequestBody Long[] ids) {
        rechargeService.removeByIds(Arrays.asList(ids));
        return Result.suc("批量删除成功");
    }
    //分页查询
    @PostMapping("/listPage")
    public Result listPage(@RequestBody QueryPageParam query) {
    HashMap param = query.getParam();

        String user = (String) param.get("user");
        String createTime = (String) param.get("createTime");
        Page <Recharge> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());
        LambdaQueryWrapper<Recharge> lambdaQueryWrapper = new LambdaQueryWrapper();
        if (StringUtils.isNotBlank(user) && !"null".equals(user)) {
            lambdaQueryWrapper.eq(Recharge::getUser, user);
        }
        //desc降序  asc升序
      lambdaQueryWrapper.orderByDesc(Recharge::getCreateTime);
        IPage result = rechargeService.PageList(page, lambdaQueryWrapper);
        return Result.suc( result.getTotal(),result.getRecords());
    }
}
