package com.longyi.shopping.controller;


import com.longyi.shopping.common.RecommendationUtils;
import com.longyi.shopping.entity.*;
import com.longyi.shopping.service.*;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import com.longyi.shopping.common.QueryPageParam;
import com.longyi.shopping.common.Result;

/**
* Goods 前端控制器
*
* @author 龙毅
* @since 2024-11-14
*/
@RestController
@RequestMapping("/goods")
public class GoodsController {
    //注入service
    @Autowired
    private  GoodsService goodsService;
    @Autowired
    private CollectService collectService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private LookService lookService;
    @Autowired
    private ShoppingService shoppingService;
    //统计数量
    @GetMapping("/count")
    public Integer count() {
        Integer count=0;
        List<Goods> list = goodsService.list();
        for (Goods goods:list){
            count+=goods.getCount();
        }

        return count;
    }
    //列表
    @GetMapping("/list")
    public List<Goods> list() {
        return goodsService.list();
    }
    @GetMapping("/recommendRandom")
    public Result recommendRandom() {
        List<Goods> list = goodsService.list();
        return Result.suc(RecommendationUtils.random(list,8));

    }
    @GetMapping("/recommendAction/{id}")
    public Result recommendAction(@PathVariable Integer id) {
        List<Goods> list = goodsService.list();
        List<Collect> collectList = collectService.lambdaQuery().eq(Collect::getUser, id).eq(Collect::getStatus, 1).list();
        List<Comment> commentList = commentService.lambdaQuery().eq(Comment::getUser, id).eq(Comment::getStatus, 1).list();
        List<Look> lookList = lookService.lambdaQuery().eq(Look::getUser, id).eq(Look::getStatus, 1).list();
        List<Shopping> shoppingList = shoppingService.lambdaQuery().eq(Shopping::getUser, id).eq(Shopping::getStatus, 2).list();
        return Result.suc(RecommendationUtils.UBCF(list,collectList,commentList,lookList,shoppingList));
    }
    @GetMapping("/recommendGoods/{id}")
    public Result recommendGods(@PathVariable Integer id) {
        List<Goods> list = goodsService.list();
        Goods goods = goodsService.getById(id);
        return Result.suc(RecommendationUtils.IBCF(list,goods));
    }
    //新增
    @PostMapping("/save")
    public Result save(@RequestBody Goods goods) {
      return goodsService.save(goods) ? Result.suc("新增成功") : Result.fail("新增失败");
    }
    //修改
    @PostMapping("/update")
    public Result update(@RequestBody Goods goods) {
       return goodsService.updateById(goods)? Result.suc("修改成功") : Result.fail("修改失败");
    }
    //详情
    @GetMapping("/info/{id}")
    public Result info(@PathVariable("id") Long id) {
        Goods goods = goodsService.getById(id);
        return Result.suc(goods);
    }
    //删除
    @Transactional
    @PostMapping("/deleteBatch")
    public Result deleteBatch(@RequestBody Long[] ids) {
        goodsService.removeByIds(Arrays.asList(ids));
        return Result.suc("批量删除成功");
    }
    @GetMapping("/replenish")
    public Result replenish(@RequestParam Long id, Integer count) {
        Goods goods = goodsService.getById(id);
        goods.setCount(goods.getCount() + count);
        return goodsService.updateById(goods) ? Result.suc("修改成功") : Result.fail("修改失败");
    }
    //分页查询
    @PostMapping("/listPage")
    public Result listPage(@RequestBody QueryPageParam query) {
    HashMap param = query.getParam();
        String name = (String) param.get("name");
        String business = (String) param.get("business");
        String type = (String) param.get("type");
        Page <Goods> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());
        LambdaQueryWrapper<Goods> lambdaQueryWrapper = new LambdaQueryWrapper();

        if (StringUtils.isNotBlank(name) && !"null".equals(name)) {
            lambdaQueryWrapper.like(Goods::getName, name);
        }
        if (StringUtils.isNotBlank(business) && !"null".equals(business)) {
            lambdaQueryWrapper.eq(Goods::getBusiness, business);
        }
        if (StringUtils.isNotBlank(type) && !"null".equals(type)) {
            lambdaQueryWrapper.eq(Goods::getType, type);
        }

        IPage result = goodsService.PageList(page, lambdaQueryWrapper);
        return Result.suc( result.getTotal(),result.getRecords());
    }
    @PostMapping("/listData")
    public Result listData(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();
        String name = (String) param.get("name");
        String createTime = (String) param.get("createTime");
        String comment = (String) param.get("comment");
        String price = (String) param.get("price");
        String type = (String) param.get("type");
        Page <Goods> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());
        LambdaQueryWrapper<Goods> lambdaQueryWrapper = new LambdaQueryWrapper();

        if (StringUtils.isNotBlank(name) && !"null".equals(name)) {
            lambdaQueryWrapper.like(Goods::getName, name);
        }

        if (StringUtils.isNotBlank(type) && !"null".equals(type)&&!"0".equals(type)) {
            lambdaQueryWrapper.eq(Goods::getType, type);
        }
        if (StringUtils.isNotBlank(price) && !"null".equals(price) && "0".equals(price)) {
            lambdaQueryWrapper.orderByDesc(Goods::getPrice);
        } else {
            lambdaQueryWrapper.orderByAsc(Goods::getPrice);
        }
        if (StringUtils.isNotBlank(createTime) && !"null".equals(createTime) && !"0".equals(createTime)) {
            lambdaQueryWrapper.orderByDesc(Goods::getCreateTime);
        }

        if (StringUtils.isNotBlank(comment) && !"null".equals(comment) && !"0".equals(comment)) {
            lambdaQueryWrapper.orderByDesc(Goods::getGood);
        }

        IPage result = goodsService.PageList(page, lambdaQueryWrapper);
        return Result.suc( result.getTotal(),result.getRecords());
    }
}
