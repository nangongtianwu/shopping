package com.longyi.shopping.service;

import com.longyi.shopping.entity.Comment;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
/**
* <p>
    * 评论 服务类
    * </p>
*
* @author 龙毅
* @since 2024-11-14
*/
public interface CommentService extends IService<Comment> {

IPage PageList(IPage<Comment> page, Wrapper wrapper);
}
