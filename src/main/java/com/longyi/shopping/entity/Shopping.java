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
 * 购物
 * </p>
 *
 * @author 龙毅
 * @since 2024-11-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Shopping对象", description="购物")
public class Shopping implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "购物ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "购物商品")
    private Integer goods;

    @ApiModelProperty(value = "购物数量")
    private Integer count;

    @ApiModelProperty(value = "购物金额")
    private Integer money;

    @ApiModelProperty(value = "购物用户")
    private Integer user;

    @ApiModelProperty(value = "购物时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "购物状态1正常，2已消费，0已删除或取消")
    private Integer status;


}
