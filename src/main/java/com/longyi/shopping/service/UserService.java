package com.longyi.shopping.service;

import com.longyi.shopping.entity.ChartPie;
import com.longyi.shopping.entity.User;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* <p>
    * 用户 服务类
    * </p>
*
* @author 龙毅
* @since 2024-11-14
*/
public interface UserService extends IService<User> {

IPage PageList(IPage<User> page, Wrapper wrapper);

    List<ChartPie> usertype();
}
