package com.longyi.shopping.service.impl;

import com.longyi.shopping.entity.ChartPie;
import com.longyi.shopping.entity.User;
import com.longyi.shopping.mapper.UserMapper;
import com.longyi.shopping.service.UserService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* UserServiceImpl 服务实现类
*
* @author 龙毅
* @since 2024-11-14
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

@Resource
private UserMapper userMapper;

@Override
public IPage PageList(IPage<User> page, Wrapper wrapper) {
    return userMapper.PageList(page,wrapper);
    }

    @Override
    public List<ChartPie> usertype() {
        return userMapper.usertype();
    }

}
