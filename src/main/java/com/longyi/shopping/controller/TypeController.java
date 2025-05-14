package com.longyi.shopping.controller;


import com.longyi.shopping.entity.Goods;
import com.longyi.shopping.service.GoodsService;
import com.longyi.shopping.service.TypeService;
import com.longyi.shopping.entity.Type;

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
* Type 前端控制器
*
* @author 龙毅
* @since 2024-11-14
*/
@RestController
@RequestMapping("/type")
public class TypeController {
    //注入service
    @Autowired
    private  TypeService typeService;
    @Autowired
    private GoodsService goodsService;
    //统计数量
    @GetMapping("/count")
    public Integer count() {
       return typeService.count();
    }
    //列表
    @GetMapping("/list")
    public List<Type> list() {
        return typeService.list();
    }
    //新增
    @PostMapping("/save")
    public Result save(@RequestBody Type type) {
        List<Type> list = typeService.lambdaQuery().eq(Type::getName, type.getName()).list();
        if (list.size() > 0) {
            return Result.fail("分类名重复");
        }
      return typeService.save(type) ? Result.suc("新增成功") : Result.fail("新增失败");
    }
    //修改
    @PostMapping("/update")
    public Result update(@RequestBody Type type) {
        List<Type> list = typeService.lambdaQuery().eq(Type::getName, type.getName()).ne(Type::getId, type.getId()).list();
        if (list.size() > 0) {
            return Result.fail("分类名重复");
        }
       return typeService.updateById(type)? Result.suc("修改成功") : Result.fail("修改失败");
    }
    //详情
    @GetMapping("/info/{id}")
    public Result info(@PathVariable("id") Long id) {
        Type type = typeService.getById(id);
        return Result.suc(type);
    }
    //删除
    @Transactional
    @PostMapping("/deleteBatch")
    public Result deleteBatch(@RequestBody Long[] ids) {
        for (Long id : ids) {
            if (goodsService.lambdaQuery().eq(Goods::getType, id).list().size() > 0) {
                return Result.fail("该分类下还有商品，无法删除！");
            }
        }
        typeService.removeByIds(Arrays.asList(ids));
        return Result.suc("批量删除成功");
    }
    @GetMapping("/pass/{id}")
    public Result pass(@PathVariable Integer id){
        Type type = typeService.getById(id);
        type.setStatus(1);
        return typeService.updateById(type)? Result.suc("修改成功") : Result.fail("修改失败");
    }
    @GetMapping("/refuse/{id}")
    public Result refuse(@PathVariable Integer id){
        Type type = typeService.getById(id);
        type.setStatus(2);
        return typeService.updateById(type)? Result.suc("修改成功") : Result.fail("修改失败");
    }
    //分页查询
    @PostMapping("/listPage")
    public Result listPage(@RequestBody QueryPageParam query) {
    HashMap param = query.getParam();

        String name = (String) param.get("name");
        String status = (String) param.get("status");
        Page <Type> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());
        LambdaQueryWrapper<Type> lambdaQueryWrapper = new LambdaQueryWrapper();

        if (StringUtils.isNotBlank(name) && !"null".equals(name)) {
            lambdaQueryWrapper.like(Type::getName, name);
        }
        if (StringUtils.isNotBlank(status) && !"null".equals(status)) {
            lambdaQueryWrapper.eq(Type::getStatus, status);
        }
        IPage result = typeService.PageList(page, lambdaQueryWrapper);
        return Result.suc( result.getTotal(),result.getRecords());
    }
}
