package com.longyi.shopping.controller;


import com.longyi.shopping.entity.Goods;
import com.longyi.shopping.service.CommentService;
import com.longyi.shopping.entity.Comment;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.longyi.shopping.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.longyi.shopping.common.QueryPageParam;
import com.longyi.shopping.common.Result;

/**
 * Comment 前端控制器
 *
 * @author 龙毅
 * @since 2024-11-14
 */
@RestController
@RequestMapping("/comment")
public class CommentController {
    //注入service
    @Autowired
    private CommentService commentService;
    @Autowired
    private GoodsService goodsService;
    //统计数量
    @GetMapping("/count")
    public Integer count() {
        return commentService.count();
    }

    //列表
    @GetMapping("/list")
    public List<Comment> list() {
        return commentService.list();
    }

    //新增
    @PostMapping("/save")
    @Transactional
    public Result save(@RequestBody Comment comment) {
        Goods goods = goodsService.getById(comment.getRelation());
        goods.setComment(goods.getComment()+1);
        if(comment.getRating()==5)
            goods.setGood(goods.getGood()+1);
        goodsService.updateById(goods);

        return commentService.save(comment) ? Result.suc("新增成功") : Result.fail("新增失败");
    }

    //修改
    @PostMapping("/update")
    public Result update(@RequestBody Comment comment) {
        return commentService.updateById(comment) ? Result.suc("修改成功") : Result.fail("修改失败");
    }

    //详情
    @GetMapping("/info/{id}")
    public Result info(@PathVariable("id") Long id) {
        Comment comment = commentService.getById(id);
        return Result.suc(comment);
    }

    //删除
    @Transactional
    @PostMapping("/deleteBatch")
    public Result deleteBatch(@RequestBody Long[] ids) {
        commentService.removeByIds(Arrays.asList(ids));
        return Result.suc("批量删除成功");
    }

    //分页查询
    @PostMapping("/listPage")
    public Result listPage(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();

        String user = (String) param.get("user");
        String relation = (String) param.get("relation");
        String createTime = (String) param.get("createTime");
        String status = (String) param.get("status");
        String modules = (String) param.get("modules");
        Page<Comment> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());
        LambdaQueryWrapper<Comment> lambdaQueryWrapper = new LambdaQueryWrapper();
        if (StringUtils.isNotBlank(user) && !"null".equals(user)) {
            lambdaQueryWrapper.eq(Comment::getUser, user);
        }
        if (StringUtils.isNotBlank(relation) && !"null".equals(relation)) {
            lambdaQueryWrapper.eq(Comment::getRelation, relation);
        }
        if (StringUtils.isNotBlank(createTime) && !"null".equals(createTime)) {
            lambdaQueryWrapper.like(Comment::getCreateTime, createTime);
        }
        if (StringUtils.isNotBlank(status) && !"null".equals(status)) {
            lambdaQueryWrapper.eq(Comment::getStatus, status);
        }
        if (StringUtils.isNotBlank(modules) && !"null".equals(modules)) {
            lambdaQueryWrapper.like(Comment::getModules, modules);
        }
        IPage result = commentService.PageList(page, lambdaQueryWrapper);
        List<Comment> list = result.getRecords();
        List<Comment> root = list.stream().filter(comment -> comment.getPid() == 0).collect(Collectors.toList());
        for (Comment comment:root){
            Integer rootId = comment.getId();
            List<Comment> children=list.stream().filter(comment1 -> rootId.equals(comment1.getPid())).collect(Collectors.toList());
            comment.setChildren(children);
        }
        return Result.suc((long) root.size(),root);
    }
}
