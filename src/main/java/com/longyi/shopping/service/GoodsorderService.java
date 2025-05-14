package com.longyi.shopping.service;

import com.longyi.shopping.entity.ChartPie;
import com.longyi.shopping.entity.Goodsorder;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* <p>
    * 商品订单 服务类
    * </p>
*
* @author 龙毅
* @since 2024-11-14
*/
public interface GoodsorderService extends IService<Goodsorder> {

IPage PageList(IPage<Goodsorder> page, Wrapper wrapper);
    List<ChartPie> weekMapCount();

    List<ChartPie> monthMapCount();

    List<ChartPie> yearMapCount();

    List<ChartPie> allMapCount();

    List<ChartPie> weekMapMoney();

    List<ChartPie> monthMapMoney();

    List<ChartPie> yearMapMoney();

    List<ChartPie> allMapMoney();
    List<ChartPie> allMoney();

    List<ChartPie> yearMoney();

    List<ChartPie> monthMoney();

    List<ChartPie> weekMoney();

    List<ChartPie> allCount();

    List<ChartPie> yearCount();

    List<ChartPie> monthCount();

    List<ChartPie> weekCount();
}
