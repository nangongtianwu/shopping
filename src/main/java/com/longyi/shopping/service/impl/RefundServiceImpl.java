package com.longyi.shopping.service.impl;

import com.longyi.shopping.entity.Refund;
import com.longyi.shopping.mapper.RefundMapper;
import com.longyi.shopping.service.RefundService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
/**
* RefundServiceImpl 服务实现类
*
* @author 龙毅
* @since 2024-11-14
*/
@Service
public class RefundServiceImpl extends ServiceImpl<RefundMapper, Refund> implements RefundService {

@Resource
private RefundMapper refundMapper;

@Override
public IPage PageList(IPage<Refund> page, Wrapper wrapper) {
    return refundMapper.PageList(page,wrapper);
    }

}
