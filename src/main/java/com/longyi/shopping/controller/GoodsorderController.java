package com.longyi.shopping.controller;


import com.longyi.shopping.common.CodeUtils;
import com.longyi.shopping.entity.*;
import com.longyi.shopping.service.*;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.longyi.shopping.common.QueryPageParam;
import com.longyi.shopping.common.Result;

/**
 * Goodsorder 前端控制器
 *
 * @author 龙毅
 * @since 2024-11-14
 */
@RestController
@RequestMapping("/goodsorder")
public class GoodsorderController {
    //注入service
    @Autowired
    private GoodsorderService goodsorderService;
    @Autowired
    private ShoppingService shoppingService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private UserService userService;
    @Autowired
    private ShoporderService shoporderService;

    //统计数量
    @GetMapping("/count/{id}")
    public Integer count(@PathVariable Integer id) {
        List<Goodsorder> list;
        if (id == 0) {
            list = goodsorderService.list();
        } else list = goodsorderService.lambdaQuery().eq(Goodsorder::getUser, id).list();

        Integer count = 0;
        for (Goodsorder goodsorder : list) {
            count += goodsorder.getMoney();
        }
        return count;
    }

    //列表
    @GetMapping("/list")
    public List<Goodsorder> list() {
        return goodsorderService.list();
    }

    //提交订单
    @PostMapping("/submit")
    @Transactional
    public Result submit(@RequestBody Goodsorder goodsorder) {
        goodsorder.setStatus(2);
        List<Shoporder> list = shoporderService.lambdaQuery().eq(Shoporder::getOrderId, goodsorder.getId()).list();
        for (Shoporder shoporder : list) {
            Shopping shopping = shoppingService.getById(shoporder.getShopping());
            Goods goods = goodsService.getById(shopping.getGoods());
            if (goods.getCount() - shopping.getCount() < 0) {
                return Result.fail("商品库存不足，订单提交失败");
            }
            goods.setCount(goods.getCount() - shopping.getCount());
            goodsService.updateById(goods);
        }
        return goodsorderService.updateById(goodsorder) ? Result.suc("修改成功") : Result.fail("修改失败");

    }

    @PostMapping("/pay")
    @Transactional
    public Result pay(@RequestBody Goodsorder goodsorder){
        User user = userService.getById(goodsorder.getUser());
        if(user.getBalance()-goodsorder.getMoney()<0)
            return Result.fail("余额不足，请充值！");
        user.setBalance(user.getBalance()-goodsorder.getMoney());
        userService.updateById(user);
        goodsorder.setStatus(1);
        goodsorder.setPayTime(LocalDateTime.now());
        return goodsorderService.updateById(goodsorder) ? Result.suc("修改成功") : Result.fail("修改失败");

    }
    @PostMapping("/cancel")
    @Transactional
    public Result cancel(@RequestBody Goodsorder goodsorder){

        goodsorder.setStatus(3);
        return goodsorderService.updateById(goodsorder) ? Result.suc("修改成功") : Result.fail("修改失败");

    }
    //新增
    @PostMapping("/save/{ids}/{user}/{money}")
    public Result save(@PathVariable Integer[] ids, @PathVariable Integer user, @PathVariable Integer money) {

        Goodsorder goodsorder = new Goodsorder();
        String code = CodeUtils.orderCode();
        goodsorder.setCode(code);
        goodsorder.setUser(user);
        goodsorder.setMoney(money);
        goodsorder.setCreateTime(LocalDateTime.now());
        goodsorderService.save(goodsorder);
        Goodsorder order = goodsorderService.lambdaQuery().eq(Goodsorder::getCode, code).one();

        for (Integer id : ids) {
            Shopping shopping = shoppingService.getById(id);
            shopping.setStatus(2);
            shoppingService.updateById(shopping);
            Shoporder shoporder = new Shoporder();
            shoporder.setShopping(id);
            shoporder.setOrderId(order.getId());
            shoporderService.save(shoporder);

        }
        return Result.suc(order);
    }

    //修改
    @PostMapping("/update")
    public Result update(@RequestBody Goodsorder goodsorder) {
        return goodsorderService.updateById(goodsorder) ? Result.suc("修改成功") : Result.fail("修改失败");
    }

    //详情
    @GetMapping("/info/{id}")
    public Result info(@PathVariable("id") Long id) {
        Goodsorder goodsorder = goodsorderService.getById(id);
        return Result.suc(goodsorder);
    }

    //删除
    @Transactional
    @PostMapping("/deleteBatch")
    public Result deleteBatch(@RequestBody Long[] ids) {
        goodsorderService.removeByIds(Arrays.asList(ids));
        return Result.suc("批量删除成功");
    }

    //分页查询
    @PostMapping("/listPage")
    public Result listPage(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();
        String code = (String) param.get("code");
        String user = (String) param.get("user");
        String status = (String) param.get("status");
        String createTime = (String) param.get("createTime");
        String payTime = (String) param.get("payTime");
        Page<Goodsorder> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());
        LambdaQueryWrapper<Goodsorder> lambdaQueryWrapper = new LambdaQueryWrapper();
        if (StringUtils.isNotBlank(code) && !"null".equals(code)) {
            lambdaQueryWrapper.like(Goodsorder::getCode, code);
        }
        if (StringUtils.isNotBlank(user) && !"null".equals(user)) {
            lambdaQueryWrapper.eq(Goodsorder::getUser, user);
        }
        if (StringUtils.isNotBlank(status) && !"null".equals(status)) {
            lambdaQueryWrapper.eq(Goodsorder::getStatus, status);
        }
        if (StringUtils.isNotBlank(createTime) && !"null".equals(createTime)) {
            lambdaQueryWrapper.like(Goodsorder::getCreateTime, createTime);
        }
        if (StringUtils.isNotBlank(payTime) && !"null".equals(payTime)) {
            lambdaQueryWrapper.like(Goodsorder::getPayTime, payTime);
        }
        IPage result = goodsorderService.PageList(page, lambdaQueryWrapper);
        return Result.suc(result.getTotal(), result.getRecords());
    }
    @PostMapping("/chart")
    public Result chart(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();
        String time = (String) param.get("time");
        String record = (String) param.get("record");
        List<ChartPie> chart =new ArrayList<>();
        if (record.equals("1")) {
            if (time.equals("0")) {
                chart = goodsorderService.allMoney();
            }
            if (time.equals("1")) {
                chart = goodsorderService.yearMoney();
            }
            if (time.equals("2")) {
                chart = goodsorderService.monthMoney();

            }
            if (time.equals("3")) {
                chart = goodsorderService.weekMoney();
            }

        } else  {
            if (time.equals("0")) {
                chart = goodsorderService.allCount();
            }
            if (time.equals("1")) {
                chart = goodsorderService.yearCount();
            }
            if (time.equals("2")) {
                chart = goodsorderService.monthCount();
            }
            if (time.equals("3")) {
                chart = goodsorderService.weekCount();
            }
        }
        System.out.println(chart);
        return Result.suc(chart);
    }
}
