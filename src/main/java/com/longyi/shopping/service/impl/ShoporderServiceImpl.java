package com.longyi.shopping.service.impl;

import com.longyi.shopping.entity.Shoporder;
import com.longyi.shopping.mapper.ShoporderMapper;
import com.longyi.shopping.service.ShoporderService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
/**
* ShoporderServiceImpl 服务实现类
*
* @author 龙毅
* @since 2024-11-14
*/
@Service
public class ShoporderServiceImpl extends ServiceImpl<ShoporderMapper, Shoporder> implements ShoporderService {

@Resource
private ShoporderMapper shoporderMapper;

@Override
public IPage PageList(IPage<Shoporder> page, Wrapper wrapper) {
    return shoporderMapper.PageList(page,wrapper);
    }

}
