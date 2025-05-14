package com.longyi.shopping.controller;


import com.longyi.shopping.entity.*;
import com.longyi.shopping.service.*;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import com.longyi.shopping.common.QueryPageParam;
import com.longyi.shopping.common.Result;

/**
* Refund 前端控制器
*
* @author 龙毅
* @since 2024-11-14
*/
@RestController
@RequestMapping("/refund")
public class RefundController {
    //注入service
    @Autowired
    private  RefundService refundService;
    @Autowired
    private ShoppingService shoppingService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private ShoporderService shoporderService;

    @Autowired
    private UserService userService;

    //统计数量
    @GetMapping("/count/{role}/{id}")
    public Integer count(@PathVariable Integer role,@PathVariable Integer id) {
       List<Refund> list=new ArrayList<>();
               if(id!=0){
                   if(role==3){
                       list=refundService.lambdaQuery().eq(Refund::getUser,id).list();
                   }else if(role==2){
                       list=refundService.lambdaQuery().eq(Refund::getBusiness,id).list();
                   }else
                       list=refundService.list();
               }else list=refundService.list();
        Integer count=0;
        for (Refund refund:list){
            count+=refund.getMoney();
        }
        return count;
    }
    //列表
    @GetMapping("/list")
    public List<Refund> list() {
        return refundService.list();
    }
    //新增
    @Transactional
    @PostMapping("/save/{id}")
    public Result save(@PathVariable Integer id) {
        Shoporder shoporder = shoporderService.getById(id);
        Shopping shopping = shoppingService.getById(shoporder.getShopping());
        Goods goods = goodsService.getById(shopping.getGoods());
        Refund refund=new Refund();
        refund.setOrderId(shoporder.getOrderId());
        refund.setUser(shopping.getUser());
        refund.setMoney(shopping.getMoney());
        refund.setGoods(shopping.getGoods());
        refund.setShopId(shoporder.getId());
        refund.setBusiness(goods.getBusiness());
        shoporder.setStatus(0);
        shoporderService.updateById(shoporder);

        return refundService.save(refund) ? Result.suc("新增成功") : Result.fail("新增失败");
    }
    //修改
    @PostMapping("/update")
    public Result update(@RequestBody Refund refund) {
       return refundService.updateById(refund)? Result.suc("修改成功") : Result.fail("修改失败");
    }
    //详情
    @GetMapping("/info/{id}")
    public Result info(@PathVariable("id") Long id) {
        Refund refund = refundService.getById(id);
        return Result.suc(refund);
    }
    //删除
    @Transactional
    @PostMapping("/deleteBatch")
    public Result deleteBatch(@RequestBody Long[] ids) {
        refundService.removeByIds(Arrays.asList(ids));
        return Result.suc("批量删除成功");
    }
    @Transactional
    @GetMapping("/pass/{id}")
    public Result pass(@PathVariable("id") Long id) {
        Refund refund = refundService.getById(id);
        refund.setStatus(1);
        User user = userService.getById(refund.getUser());
        user.setBalance(user.getBalance() + refund.getMoney());
        userService.updateById(user);
        return refundService.updateById(refund) ? Result.suc("修改成功") : Result.fail("修改失败");
    }

    @GetMapping("/refuse/{id}")
    public Result refuse(@PathVariable("id") Long id) {
        Refund refund = refundService.getById(id);
        refund.setStatus(2);
        return refundService.updateById(refund) ? Result.suc("修改成功") : Result.fail("修改失败");
    }
    //分页查询
    @PostMapping("/listPage")
    public Result listPage(@RequestBody QueryPageParam query) {
    HashMap param = query.getParam();
        String user = (String) param.get("user");
        String goods = (String) param.get("goods");
        String business = (String) param.get("business");
        String orderId = (String) param.get("orderId");
        String money = (String) param.get("money");
        String createTime = (String) param.get("createTime");
        String status = (String) param.get("status");
        Page <Refund> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());
        LambdaQueryWrapper<Refund> lambdaQueryWrapper = new LambdaQueryWrapper();
        if (StringUtils.isNotBlank(user) && !"null".equals(user)) {
            lambdaQueryWrapper.eq(Refund::getUser, user);
        }
        if (StringUtils.isNotBlank(goods) && !"null".equals(goods)) {
            lambdaQueryWrapper.eq(Refund::getGoods, goods);
        }
        if (StringUtils.isNotBlank(business) && !"null".equals(business)) {
            lambdaQueryWrapper.eq(Refund::getBusiness, business);
        }
        if (StringUtils.isNotBlank(orderId) && !"null".equals(orderId)) {
            lambdaQueryWrapper.eq(Refund::getOrderId, orderId);
        }
        if (StringUtils.isNotBlank(money) && !"null".equals(money)) {
            lambdaQueryWrapper.eq(Refund::getMoney, money);
        }
        if (StringUtils.isNotBlank(createTime) && !"null".equals(createTime)) {
            lambdaQueryWrapper.like(Refund::getCreateTime, createTime);
        }
        if (StringUtils.isNotBlank(status) && !"null".equals(status)) {
            lambdaQueryWrapper.eq(Refund::getStatus, status);
        }
        IPage result = refundService.PageList(page, lambdaQueryWrapper);
        return Result.suc( result.getTotal(),result.getRecords());
    }
}
