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
 * 商品订单
 * </p>
 *
 * @author 龙毅
 * @since 2024-11-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Goodsorder对象", description="商品订单")
public class Goodsorder implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "订单编号")
    private String code;

    @ApiModelProperty(value = "下单用户")
    private Integer user;

    @ApiModelProperty(value = "下单金额")
    private Integer money;

    @ApiModelProperty(value = "订单状态，0待提交，1已支付，2未支付，3.已取消")
    private Integer status;

    @ApiModelProperty(value = "下单时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "支付时间")
    private LocalDateTime payTime;

    @ApiModelProperty(value = "订单地址")
    private Integer address;


}
