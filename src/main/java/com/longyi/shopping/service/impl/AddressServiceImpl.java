package com.longyi.shopping.service.impl;

import com.longyi.shopping.entity.Address;
import com.longyi.shopping.mapper.AddressMapper;
import com.longyi.shopping.service.AddressService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
/**
* AddressServiceImpl 服务实现类
*
* @author 龙毅
* @since 2024-11-14
*/
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService {

@Resource
private AddressMapper addressMapper;

@Override
public IPage PageList(IPage<Address> page, Wrapper wrapper) {
    return addressMapper.PageList(page,wrapper);
    }

}
