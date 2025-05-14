package com.longyi.shopping.controller;


import com.longyi.shopping.entity.Goods;
import com.longyi.shopping.service.CollectService;
import com.longyi.shopping.entity.Collect;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.longyi.shopping.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.longyi.shopping.common.QueryPageParam;
import com.longyi.shopping.common.Result;

/**
 * Collect 前端控制器
 *
 * @author 龙毅
 * @since 2024-11-14
 */
@RestController
@RequestMapping("/collect")
public class CollectController {
    //注入service
    @Autowired
    private CollectService collectService;

    @Autowired
    private GoodsService goodsService;

    //统计数量
    @GetMapping("/count")
    public Integer count() {
        return collectService.count();
    }

    //列表
    @GetMapping("/list")
    public List<Collect> list() {
        return collectService.list();
    }

    @GetMapping("/isCollect/{id}/{user}")
    public Result isCollectt(@PathVariable Long id, @PathVariable Long user) {
        List<Collect> collectList = collectService.lambdaQuery().eq(Collect::getRelation, id).eq(Collect::getUser, user).eq(Collect::getStatus, 1).list();
        if (collectList.size() > 0) {
            return Result.suc(collectList.get(0));
        } else return Result.fail();
    }

    //新增
    @Transactional
    @PostMapping("/save")
    public Result save(@RequestBody Collect collect) {
        Goods goods = goodsService.getById(collect.getRelation());
        goods.setCollect(goods.getCollect() + 1);
        goodsService.updateById(goods);

        return collectService.save(collect) ? Result.suc("新增成功") : Result.fail("新增失败");
    }

    //修改
    @Transactional
    @PostMapping("/update")
    public Result update(@RequestBody Collect collect) {
        Goods goods = goodsService.getById(collect.getRelation());
        goods.setCollect(goods.getCollect() -1);
        goodsService.updateById(goods);
        return collectService.updateById(collect) ? Result.suc("修改成功") : Result.fail("修改失败");
    }

    //详情
    @GetMapping("/info/{id}")
    public Result info(@PathVariable("id") Long id) {
        Collect collect = collectService.getById(id);
        return Result.suc(collect);
    }

    //删除
    @Transactional
    @PostMapping("/deleteBatch")
    public Result deleteBatch(@RequestBody Long[] ids) {
        collectService.removeByIds(Arrays.asList(ids));
        return Result.suc("批量删除成功");
    }

    //分页查询
    @PostMapping("/listPage")
    public Result listPage(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();

        Page<Collect> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());
        LambdaQueryWrapper<Collect> lambdaQueryWrapper = new LambdaQueryWrapper();

        IPage result = collectService.PageList(page, lambdaQueryWrapper);
        return Result.suc(result.getTotal(), result.getRecords());
    }
}
