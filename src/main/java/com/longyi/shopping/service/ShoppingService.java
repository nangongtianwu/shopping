package com.longyi.shopping.service;

import com.longyi.shopping.entity.Shopping;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
/**
* <p>
    * 购物 服务类
    * </p>
*
* @author 龙毅
* @since 2024-11-14
*/
public interface ShoppingService extends IService<Shopping> {

IPage PageList(IPage<Shopping> page, Wrapper wrapper);
}
