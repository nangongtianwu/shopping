package com.longyi.shopping.service;

import com.longyi.shopping.entity.Look;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
/**
* <p>
    * 浏览 服务类
    * </p>
*
* @author 龙毅
* @since 2024-11-14
*/
public interface LookService extends IService<Look> {

IPage PageList(IPage<Look> page, Wrapper wrapper);
}
