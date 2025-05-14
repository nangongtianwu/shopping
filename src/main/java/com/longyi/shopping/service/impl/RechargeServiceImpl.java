package com.longyi.shopping.service.impl;

import com.longyi.shopping.entity.Recharge;
import com.longyi.shopping.mapper.RechargeMapper;
import com.longyi.shopping.service.RechargeService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
/**
* RechargeServiceImpl 服务实现类
*
* @author 龙毅
* @since 2024-11-14
*/
@Service
public class RechargeServiceImpl extends ServiceImpl<RechargeMapper, Recharge> implements RechargeService {

@Resource
private RechargeMapper rechargeMapper;

@Override
public IPage PageList(IPage<Recharge> page, Wrapper wrapper) {
    return rechargeMapper.PageList(page,wrapper);
    }

}
