package com.longyi.shopping.service.impl;

import com.longyi.shopping.entity.Comment;
import com.longyi.shopping.mapper.CommentMapper;
import com.longyi.shopping.service.CommentService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
/**
* CommentServiceImpl 服务实现类
*
* @author 龙毅
* @since 2024-11-14
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

@Resource
private CommentMapper commentMapper;

@Override
public IPage PageList(IPage<Comment> page, Wrapper wrapper) {
    return commentMapper.PageList(page,wrapper);
    }

}
