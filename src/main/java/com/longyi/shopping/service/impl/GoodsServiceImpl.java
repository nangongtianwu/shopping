package com.longyi.shopping.service.impl;

import com.longyi.shopping.entity.ChartPie;
import com.longyi.shopping.entity.Goods;
import com.longyi.shopping.mapper.GoodsMapper;
import com.longyi.shopping.service.GoodsService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* GoodsServiceImpl 服务实现类
*
* @author 龙毅
* @since 2024-11-14
*/
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

@Resource
private GoodsMapper goodsMapper;

@Override
public IPage PageList(IPage<Goods> page, Wrapper wrapper) {
    return goodsMapper.PageList(page,wrapper);
    }

    @Override
    public List<ChartPie> goodstype() {
        return goodsMapper.goodstype();
    }

}
