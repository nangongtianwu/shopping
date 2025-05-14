package com.longyi.shopping.service.impl;

import com.longyi.shopping.entity.ChartPie;
import com.longyi.shopping.entity.Goodsorder;
import com.longyi.shopping.mapper.GoodsorderMapper;
import com.longyi.shopping.service.GoodsorderService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* GoodsorderServiceImpl 服务实现类
*
* @author 龙毅
* @since 2024-11-14
*/
@Service
public class GoodsorderServiceImpl extends ServiceImpl<GoodsorderMapper, Goodsorder> implements GoodsorderService {

@Resource
private GoodsorderMapper goodsorderMapper;

@Override
public IPage PageList(IPage<Goodsorder> page, Wrapper wrapper) {
    return goodsorderMapper.PageList(page,wrapper);
    }

    @Override
    public List<ChartPie> weekMapCount() {
        return goodsorderMapper.weekMapCount();
    }

    @Override
    public List<ChartPie> monthMapCount() {
        return goodsorderMapper.monthMapCount();
    }

    @Override
    public List<ChartPie> yearMapCount() {
        return goodsorderMapper.yearMapCount();
    }

    @Override
    public List<ChartPie> allMapCount() {
        return goodsorderMapper.allMapCount();
    }

    @Override
    public List<ChartPie> weekMapMoney() {
        return goodsorderMapper.weekMapMoney();
    }

    @Override
    public List<ChartPie> monthMapMoney() {
        return goodsorderMapper.monthMapMoney();
    }

    @Override
    public List<ChartPie> yearMapMoney() {
        return goodsorderMapper.yearMapMoney();
    }

    @Override
    public List<ChartPie> allMapMoney() {
        return goodsorderMapper.allMapMoney();
    }

    @Override
    public List<ChartPie> allMoney() {
        return goodsorderMapper.allMoney();
    }

    @Override
    public List<ChartPie> yearMoney() {
        return goodsorderMapper.yearMoney();
    }

    @Override
    public List<ChartPie> monthMoney() {
        return goodsorderMapper.monthMoney();
    }

    @Override
    public List<ChartPie> weekMoney() {
        return goodsorderMapper.weekMoney();
    }

    @Override
    public List<ChartPie> allCount() {
        return goodsorderMapper.allCount();
    }

    @Override
    public List<ChartPie> yearCount() {
        return goodsorderMapper.yearCount();
    }

    @Override
    public List<ChartPie> monthCount() {
        return goodsorderMapper.monthCount();
    }

    @Override
    public List<ChartPie> weekCount() {
        return goodsorderMapper.weekCount();
    }

}
