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
 * 浏览
 * </p>
 *
 * @author 龙毅
 * @since 2024-11-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Look对象", description="浏览")
public class Look implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "点击ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "点击用户")
    private Integer user;

    @ApiModelProperty(value = "点击商品")
    private Integer goods;

    @ApiModelProperty(value = "点击状态，1点击，0已被清理")
    private Integer status;

    @ApiModelProperty(value = "点击时间")
    private LocalDateTime createTime;


}
