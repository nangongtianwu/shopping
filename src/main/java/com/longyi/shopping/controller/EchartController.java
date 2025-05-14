package com.longyi.shopping.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.longyi.shopping.common.QueryPageParam;
import com.longyi.shopping.common.Result;
import com.longyi.shopping.entity.ChartPie;
import com.longyi.shopping.entity.Goods;
import com.longyi.shopping.service.GoodsService;
import com.longyi.shopping.service.GoodsorderService;
import com.longyi.shopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/echart")
public class EchartController {
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private UserService userService;
    @Autowired
    private GoodsorderService goodsorderService;
    @GetMapping("/pie1")
    public Result usertype() {
        List<ChartPie> data = userService.usertype();
        return Result.suc(data);
    }
    @GetMapping("/pie2")
    public Result goodstype() {
        List<ChartPie> data = goodsService.goodstype();
        return Result.suc(data);
    }
    @PostMapping("/list")
    public Result list(@RequestBody QueryPageParam query) {

        HashMap param = query.getParam();
        String business = (String) param.get("business");
        String type = (String) param.get("type");
        Page<Goods> page = new Page();
        page.setCurrent(1);
        page.setSize(goodsService.list().size());
        LambdaQueryWrapper<Goods> lambdaQueryWrapper = new LambdaQueryWrapper();

        if (StringUtils.isNotBlank(business) && !"null".equals(business)&&!"0".equals(business)) {
            lambdaQueryWrapper.eq(Goods::getBusiness, business);
        }
        if (StringUtils.isNotBlank(type) && !"null".equals(type)&&!"0".equals(type)) {
            lambdaQueryWrapper.eq(Goods::getType, type);
        }

        IPage result = goodsService.PageList(page, lambdaQueryWrapper);
        return Result.suc( result.getTotal(),result.getRecords());
    }
    @PostMapping("/mapchart")
    public Result mapchart(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();
        String time = (String) param.get("time");
        String record = (String) param.get("record");
        List<ChartPie> chart = new ArrayList<>();
        if (record.equals("1")) {
            if (time.equals("0")) {
                chart = goodsorderService.allMapMoney();
            }
            if (time.equals("1")) {
                chart = goodsorderService.yearMapMoney();
            }
            if (time.equals("2")) {
                chart = goodsorderService.monthMapMoney();

            }
            if (time.equals("3")) {
                chart = goodsorderService.weekMapMoney();
            }

        } else  {
            if (time.equals("0")) {
                chart = goodsorderService.allMapCount();
            }
            if (time.equals("1")) {
                chart = goodsorderService.yearMapCount();
            }
            if (time.equals("2")) {
                chart = goodsorderService.monthMapCount();
            }
            if (time.equals("3")) {
                chart = goodsorderService.weekMapCount();
            }
        }
        System.out.println(chart);
        return Result.suc(chart);
    }
    @PostMapping("/chart")
    public Result chart(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();
        String time = (String) param.get("time");
        String record = (String) param.get("record");
        List<ChartPie> chart = new ArrayList<>();
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
