package com.longyi.shopping.service;

import com.longyi.shopping.entity.Shoporder;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
/**
* <p>
    * 购物订单 服务类
    * </p>
*
* @author 龙毅
* @since 2024-11-14
*/
public interface ShoporderService extends IService<Shoporder> {

IPage PageList(IPage<Shoporder> page, Wrapper wrapper);
}
