package com.longyi.shopping.service.impl;

import com.longyi.shopping.entity.Menu;
import com.longyi.shopping.mapper.MenuMapper;
import com.longyi.shopping.service.MenuService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
/**
* MenuServiceImpl 服务实现类
*
* @author 龙毅
* @since 2024-11-14
*/
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

@Resource
private MenuMapper menuMapper;

@Override
public IPage PageList(IPage<Menu> page, Wrapper wrapper) {
    return menuMapper.PageList(page,wrapper);
    }

}
