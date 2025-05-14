package com.longyi.shopping.service.impl;

import com.longyi.shopping.entity.Shopping;
import com.longyi.shopping.mapper.ShoppingMapper;
import com.longyi.shopping.service.ShoppingService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
/**
* ShoppingServiceImpl 服务实现类
*
* @author 龙毅
* @since 2024-11-14
*/
@Service
public class ShoppingServiceImpl extends ServiceImpl<ShoppingMapper, Shopping> implements ShoppingService {

@Resource
private ShoppingMapper shoppingMapper;

@Override
public IPage PageList(IPage<Shopping> page, Wrapper wrapper) {
    return shoppingMapper.PageList(page,wrapper);
    }

}
