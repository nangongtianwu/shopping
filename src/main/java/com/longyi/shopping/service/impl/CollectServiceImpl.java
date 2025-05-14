package com.longyi.shopping.service.impl;

import com.longyi.shopping.entity.Collect;
import com.longyi.shopping.mapper.CollectMapper;
import com.longyi.shopping.service.CollectService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
/**
* CollectServiceImpl 服务实现类
*
* @author 龙毅
* @since 2024-11-14
*/
@Service
public class CollectServiceImpl extends ServiceImpl<CollectMapper, Collect> implements CollectService {

@Resource
private CollectMapper collectMapper;

@Override
public IPage PageList(IPage<Collect> page, Wrapper wrapper) {
    return collectMapper.PageList(page,wrapper);
    }

}
