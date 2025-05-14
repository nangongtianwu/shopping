package com.longyi.shopping.service.impl;

import com.longyi.shopping.entity.Type;
import com.longyi.shopping.mapper.TypeMapper;
import com.longyi.shopping.service.TypeService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
/**
* TypeServiceImpl 服务实现类
*
* @author 龙毅
* @since 2024-11-14
*/
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements TypeService {

@Resource
private TypeMapper typeMapper;

@Override
public IPage PageList(IPage<Type> page, Wrapper wrapper) {
    return typeMapper.PageList(page,wrapper);
    }

}
