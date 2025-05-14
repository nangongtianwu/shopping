package com.longyi.shopping.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.longyi.shopping.entity.ChartPie;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.longyi.shopping.entity.Goods;

import java.util.List;

/**
* <p>
    * 商品 Mapper接口类
    * </p>
*
* @author 龙毅
* @since 2024-11-14
*/
@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {
IPage PageList(IPage<Goods> page, @Param(Constants.WRAPPER) Wrapper wrapper);

    List<ChartPie> goodstype();
}
