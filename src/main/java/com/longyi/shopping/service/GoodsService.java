package com.longyi.shopping.service;

import com.longyi.shopping.entity.ChartPie;
import com.longyi.shopping.entity.Goods;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* <p>
    * 商品 服务类
    * </p>
*
* @author 龙毅
* @since 2024-11-14
*/
public interface GoodsService extends IService<Goods> {

IPage PageList(IPage<Goods> page, Wrapper wrapper);

    List<ChartPie> goodstype();
}
