package com.longyi.shopping.controller;


import com.longyi.shopping.service.MenuService;
import com.longyi.shopping.entity.Menu;

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
* Menu 前端控制器
*
* @author 龙毅
* @since 2024-11-14
*/
@RestController
@RequestMapping("/menu")
public class MenuController {
    //注入service
    @Autowired
    private  MenuService menuService;
    //统计数量
    @GetMapping("/count")
    public Integer count() {
       return menuService.count();
    }
    //列表
    @GetMapping("/list")
    public List<Menu> list() {
        return menuService.list();
    }
    //新增
    @PostMapping("/save")
    public Result save(@RequestBody Menu menu) {
      return menuService.save(menu) ? Result.suc("新增成功") : Result.fail("新增失败");
    }
    //修改
    @PostMapping("/update")
    public Result update(@RequestBody Menu menu) {
       return menuService.updateById(menu)? Result.suc("修改成功") : Result.fail("修改失败");
    }
    //详情
    @GetMapping("/info/{id}")
    public Result info(@PathVariable("id") Long id) {
        Menu menu = menuService.getById(id);
        return Result.suc(menu);
    }
    //删除
    @Transactional
    @PostMapping("/deleteBatch")
    public Result deleteBatch(@RequestBody Long[] ids) {
        menuService.removeByIds(Arrays.asList(ids));
        return Result.suc("批量删除成功");
    }
    //分页查询
    @PostMapping("/listPage")
    public Result listPage(@RequestBody QueryPageParam query) {
    HashMap param = query.getParam();

        Page <Menu> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());
        LambdaQueryWrapper<Menu> lambdaQueryWrapper = new LambdaQueryWrapper();

        IPage result = menuService.PageList(page, lambdaQueryWrapper);
        return Result.suc( result.getTotal(),result.getRecords());
    }
}
