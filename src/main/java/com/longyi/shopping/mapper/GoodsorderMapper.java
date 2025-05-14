package com.longyi.shopping.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.longyi.shopping.entity.ChartPie;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.longyi.shopping.entity.Goodsorder;

import java.util.List;

/**
* <p>
    * 商品订单 Mapper接口类
    * </p>
*
* @author 龙毅
* @since 2024-11-14
*/
@Mapper
public interface GoodsorderMapper extends BaseMapper<Goodsorder> {
IPage PageList(IPage<Goodsorder> page, @Param(Constants.WRAPPER) Wrapper wrapper);
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
