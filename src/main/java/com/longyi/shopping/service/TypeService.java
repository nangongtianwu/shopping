package com.longyi.shopping.service;

import com.longyi.shopping.entity.Type;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
/**
* <p>
    * 商品类型 服务类
    * </p>
*
* @author 龙毅
* @since 2024-11-14
*/
public interface TypeService extends IService<Type> {

IPage PageList(IPage<Type> page, Wrapper wrapper);
}
