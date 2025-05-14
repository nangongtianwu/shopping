package com.longyi.shopping.service;

import com.longyi.shopping.entity.Collect;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
/**
* <p>
    * 收藏 服务类
    * </p>
*
* @author 龙毅
* @since 2024-11-14
*/
public interface CollectService extends IService<Collect> {

IPage PageList(IPage<Collect> page, Wrapper wrapper);
}
