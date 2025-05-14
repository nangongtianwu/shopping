package com.longyi.shopping.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 商品
 * </p>
 *
 * @author 龙毅
 * @since 2024-11-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Goods对象", description="商品")
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "商品名称")
    private String name;

    @ApiModelProperty(value = "商家")
    private Integer business;

    @ApiModelProperty(value = "商品分类")
    private Integer type;

    @ApiModelProperty(value = "商品图片")
    private String image;

    @ApiModelProperty(value = "商品价格")
    private Integer price;

    @ApiModelProperty(value = "商品介绍")
    private String remark;

    @ApiModelProperty(value = "商品库存")
    private Integer count;

    @ApiModelProperty(value = "计量单位")
    private String unit;

    @ApiModelProperty(value = "已售数量")
    private Integer sale;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "商品点击数")
    private Integer click;

    @ApiModelProperty(value = "商品收藏数")
    private Integer collect;

    @ApiModelProperty(value = "商品评论数")
    private Integer comment;

    @ApiModelProperty(value = "五星好评数")
    private Integer good;


}
