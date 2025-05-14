package com.longyi.shopping.controller;


import com.longyi.shopping.common.RecommendationUtils;
import com.longyi.shopping.entity.Goods;
import com.longyi.shopping.service.GoodsService;
import com.longyi.shopping.service.LookService;
import com.longyi.shopping.entity.Look;

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
* Look 前端控制器
*
* @author 龙毅
* @since 2024-11-14
*/
@RestController
@RequestMapping("/look")
public class LookController {
    //注入service
    @Autowired
    private  LookService lookService;
    @Autowired
    private GoodsService goodsService;
    //统计数量
    @GetMapping("/count")
    public Integer count() {
       return lookService.count();
    }
    //列表
    @GetMapping("/list")
    public List<Look> list() {
        return lookService.list();
    }


    //新增
    @Transactional
    @PostMapping("/save")
    public Result save(@RequestBody Look look) {
        List<Look> lookList = lookService.lambdaQuery().eq(Look::getUser, look.getUser()).eq(Look::getGoods, look.getGoods()).eq(Look::getStatus, 1).list();
       if(lookList.size()>0)
           return Result.suc("已浏览");
       else{
           Goods goods = goodsService.getById(look.getGoods());
           goods.setClick(goods.getClick()+1);
           goodsService.updateById(goods);

           return lookService.save(look) ? Result.suc("新增成功") : Result.fail("新增失败");
       }
    }
    //修改
    @PostMapping("/update")
    public Result update(@RequestBody Look look) {
       return lookService.updateById(look)? Result.suc("修改成功") : Result.fail("修改失败");
    }
    //详情
    @GetMapping("/info/{id}")
    public Result info(@PathVariable("id") Long id) {
        Look look = lookService.getById(id);
        return Result.suc(look);
    }
    //删除
    @Transactional
    @PostMapping("/deleteBatch")
    public Result deleteBatch(@RequestBody Long[] ids) {
        lookService.removeByIds(Arrays.asList(ids));
        return Result.suc("批量删除成功");
    }
    //分页查询
    @PostMapping("/listPage")
    public Result listPage(@RequestBody QueryPageParam query) {
    HashMap param = query.getParam();

        Page <Look> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());
        LambdaQueryWrapper<Look> lambdaQueryWrapper = new LambdaQueryWrapper();

        IPage result = lookService.PageList(page, lambdaQueryWrapper);
        return Result.suc( result.getTotal(),result.getRecords());
    }
}
