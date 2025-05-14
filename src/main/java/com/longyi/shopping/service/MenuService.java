package com.longyi.shopping.service;

import com.longyi.shopping.entity.Menu;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
/**
* <p>
    * 菜单 服务类
    * </p>
*
* @author 龙毅
* @since 2024-11-14
*/
public interface MenuService extends IService<Menu> {

IPage PageList(IPage<Menu> page, Wrapper wrapper);
}
