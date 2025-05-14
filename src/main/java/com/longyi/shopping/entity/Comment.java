package com.longyi.shopping.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 评论
 * </p>
 *
 * @author 龙毅
 * @since 2024-11-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Comment对象", description="评论")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品评论ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "父级ID")
    private Integer pid;

    @ApiModelProperty(value = "用户ID")
    private Integer user;

    @ApiModelProperty(value = "商品ID")
    private Integer relation;

    @ApiModelProperty(value = "评价")
    private String content;

    @ApiModelProperty(value = "评分")
    private Integer rating;

    @ApiModelProperty(value = "评论日期")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "删除状态，正常为1，已删除为0")
    private Integer status;

    @ApiModelProperty(value = "模块")
    private String modules;
    @TableField(exist = false)
    private List<Comment> children;


}
