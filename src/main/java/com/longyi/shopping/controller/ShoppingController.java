package com.longyi.shopping.controller;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.longyi.shopping.common.CodeUtils;
import com.longyi.shopping.entity.Goods;
import com.longyi.shopping.entity.Goodsorder;
import com.longyi.shopping.entity.Shoporder;
import com.longyi.shopping.service.GoodsService;
import com.longyi.shopping.service.GoodsorderService;
import com.longyi.shopping.service.ShoporderService;
import com.longyi.shopping.service.ShoppingService;
import com.longyi.shopping.entity.Shopping;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
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
* Shopping 前端控制器
*
* @author 龙毅
* @since 2024-11-14
*/
@RestController
@RequestMapping("/shopping")
public class ShoppingController {
    //注入service
    @Autowired
    private  ShoppingService shoppingService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private GoodsorderService goodsorderService;
    @Autowired
    private ShoporderService shoporderService;
    @Transactional
    @PostMapping("/order")
    public Result order(@RequestBody Shopping shopping) {
        shopping.setStatus(2);
        shoppingService.save(shopping);
        Goodsorder goodsorder=new Goodsorder();
        String code= CodeUtils.orderCode();
        goodsorder.setCode(code);
        goodsorder.setUser(shopping.getUser());
        goodsorder.setMoney(shopping.getMoney());
        goodsorder.setCreateTime(LocalDateTime.now());
        goodsorderService.save(goodsorder);
        Goodsorder order = goodsorderService.lambdaQuery().eq(Goodsorder::getCode, code).one();
        List<Shopping> list = shoppingService.list();
        Shopping shop=list.get(list.size()-1);
        Shoporder shoporder=new Shoporder();
        shoporder.setShopping(shop.getId());
        shoporder.setOrderId(order.getId());
        shoporderService.save(shoporder);
        return Result.suc(order);


    }
    //统计数量
    @GetMapping("/count")
    public Integer count() {
       return shoppingService.count();
    }
    //列表
    @GetMapping("/list")
    public List<Shopping> list() {
        return shoppingService.list();
    }
    //新增
    @PostMapping("/save")
    public Result save(@RequestBody Shopping shopping) {
      return shoppingService.save(shopping) ? Result.suc("新增成功") : Result.fail("新增失败");
    }
    //修改
    @PostMapping("/update/{id}/{count}")
    public Result update(@PathVariable("id") Long id, @PathVariable("count") Integer count) {
        Shopping shopping = shoppingService.getById(id);
        Goods goods = goodsService.getById(shopping.getGoods());
        shopping.setCount(count);
        shopping.setMoney(count * goods.getPrice());
        return shoppingService.updateById(shopping) ? Result.suc("修改成功") : Result.fail("修改失败");
    }
    //详情
    @GetMapping("/info/{id}")
    public Result info(@PathVariable("id") Long id) {
        Shopping shopping = shoppingService.getById(id);
        return Result.suc(shopping);
    }
    //删除
    @Transactional
    @PostMapping("/deleteBatch")
    public Result deleteBatch(@RequestBody Long[] ids) {
        shoppingService.removeByIds(Arrays.asList(ids));
        return Result.suc("批量删除成功");
    }
    //分页查询
    @PostMapping("/listPage")
    public Result listPage(@RequestBody QueryPageParam query) {
    HashMap param = query.getParam();
        String goods = (String) param.get("goods");
        String user = (String) param.get("user");
        String createTime = (String) param.get("createTime");
        String status = (String) param.get("status");
        Page <Shopping> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());
        LambdaQueryWrapper<Shopping> lambdaQueryWrapper = new LambdaQueryWrapper();
        if (StringUtils.isNotBlank(goods) && !"null".equals(goods)) {
            lambdaQueryWrapper.eq(Shopping::getGoods, goods);
        }

        if (StringUtils.isNotBlank(user) && !"null".equals(user)) {
            lambdaQueryWrapper.eq(Shopping::getUser, user);
        }
        if (StringUtils.isNotBlank(createTime) && !"null".equals(createTime)) {
            lambdaQueryWrapper.like(Shopping::getCreateTime, createTime);
        }
        if (StringUtils.isNotBlank(status) && !"null".equals(status)) {
            lambdaQueryWrapper.eq(Shopping::getStatus, status);
        }
        IPage result = shoppingService.PageList(page, lambdaQueryWrapper);
        return Result.suc( result.getTotal(),result.getRecords());
    }
}
