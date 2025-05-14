package com.longyi.shopping.service.impl;

import com.longyi.shopping.entity.Look;
import com.longyi.shopping.mapper.LookMapper;
import com.longyi.shopping.service.LookService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
/**
* LookServiceImpl 服务实现类
*
* @author 龙毅
* @since 2024-11-14
*/
@Service
public class LookServiceImpl extends ServiceImpl<LookMapper, Look> implements LookService {

@Resource
private LookMapper lookMapper;

@Override
public IPage PageList(IPage<Look> page, Wrapper wrapper) {
    return lookMapper.PageList(page,wrapper);
    }

}
