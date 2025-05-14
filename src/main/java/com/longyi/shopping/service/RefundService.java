package com.longyi.shopping.service;

import com.longyi.shopping.entity.Refund;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
/**
* <p>
    * 退款 服务类
    * </p>
*
* @author 龙毅
* @since 2024-11-14
*/
public interface RefundService extends IService<Refund> {

IPage PageList(IPage<Refund> page, Wrapper wrapper);
}
