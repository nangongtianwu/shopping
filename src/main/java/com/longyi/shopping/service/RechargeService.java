package com.longyi.shopping.service;

import com.longyi.shopping.entity.Recharge;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
/**
* <p>
    * 充值 服务类
    * </p>
*
* @author 龙毅
* @since 2024-11-14
*/
public interface RechargeService extends IService<Recharge> {

IPage PageList(IPage<Recharge> page, Wrapper wrapper);
}
