package com.longyi.shopping.service;

import com.longyi.shopping.entity.Address;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
/**
* <p>
    * 地址 服务类
    * </p>
*
* @author 龙毅
* @since 2024-11-14
*/
public interface AddressService extends IService<Address> {

IPage PageList(IPage<Address> page, Wrapper wrapper);
}
