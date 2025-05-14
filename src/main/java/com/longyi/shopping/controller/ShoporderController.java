package com.longyi.shopping.controller;


import com.longyi.shopping.service.ShoporderService;
import com.longyi.shopping.entity.Shoporder;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.longyi.shopping.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import com.longyi.shopping.common.QueryPageParam;
import com.longyi.shopping.common.Result;

/**
* Shoporder 前端控制器
*
* @author 龙毅
* @since 2024-11-14
*/
@RestController
@RequestMapping("/shoporder")
public class ShoporderController {
    //注入service
    @Autowired
    private  ShoporderService shoporderService;
    @Autowired
    private ShoppingService shoppingService;
    //统计数量
    @GetMapping("/count")
    public Integer count() {
       return shoporderService.count();
    }
    //列表
    @GetMapping("/list")
    public List<Shoporder> list() {
        return shoporderService.list();
    }
    //新增
    @PostMapping("/save")
    public Result save(@RequestBody Shoporder shoporder) {
      return shoporderService.save(shoporder) ? Result.suc("新增成功") : Result.fail("新增失败");
    }
    //修改
    @PostMapping("/update")
    public Result update(@RequestBody Shoporder shoporder) {
       return shoporderService.updateById(shoporder)? Result.suc("修改成功") : Result.fail("修改失败");
    }
    //详情
    @GetMapping("/info/{id}")
    public Result info(@PathVariable("id") Long id) {
        Shoporder shoporder = shoporderService.getById(id);
        return Result.suc(shoporder);
    }
    //删除
    @Transactional
    @PostMapping("/deleteBatch")
    public Result deleteBatch(@RequestBody Long[] ids) {
        shoporderService.removeByIds(Arrays.asList(ids));
        return Result.suc("批量删除成功");
    }
    @GetMapping("/listOrder/{id}")
    public Result listOrder(@PathVariable Long id) {
        List<Shoporder> list = shoporderService.lambdaQuery().eq(Shoporder::getOrderId, id).list();
        for (Shoporder shoporder : list) {
            shoporder.setShop(shoppingService.getById(shoporder.getShopping()));
        }
        return Result.suc(list);

    }

    //分页查询
    @PostMapping("/listPage")
    public Result listPage(@RequestBody QueryPageParam query) {
    HashMap param = query.getParam();
        String shopping = (String) param.get("shopping");
        String orderId = (String) param.get("orderId");
        String status = (String) param.get("status");
        Page <Shoporder> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());
        LambdaQueryWrapper<Shoporder> lambdaQueryWrapper = new LambdaQueryWrapper();
        if (StringUtils.isNotBlank(shopping) && !"null".equals(shopping)) {
            lambdaQueryWrapper.eq(Shoporder::getShopping, shopping);
        }
        if (StringUtils.isNotBlank(orderId) && !"null".equals(orderId)) {
            lambdaQueryWrapper.eq(Shoporder::getOrderId, orderId);
        }
        if (StringUtils.isNotBlank(status) && !"null".equals(status)) {
            lambdaQueryWrapper.eq(Shoporder::getStatus, status);
        }
        IPage result = shoporderService.PageList(page, lambdaQueryWrapper);
        return Result.suc( result.getTotal(),result.getRecords());
    }
}
