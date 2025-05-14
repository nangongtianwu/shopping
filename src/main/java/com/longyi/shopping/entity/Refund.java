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
 * 退款
 * </p>
 *
 * @author 龙毅
 * @since 2024-11-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Refund对象", description="退款")
public class Refund implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "退款ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "退款用户")
    private Integer user;

    @ApiModelProperty(value = "退款商品")
    private Integer goods;

    @ApiModelProperty(value = "退款商家")
    private Integer business;

    @ApiModelProperty(value = "退款订单")
    private Integer orderId;

    @ApiModelProperty(value = "退款金额")
    private Integer money;

    @ApiModelProperty(value = "退款时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "退款状态0待确认，2退款失败，1退款成功")
    private Integer status;

    @ApiModelProperty(value = "退款订单")
    private Integer shopId;


}
