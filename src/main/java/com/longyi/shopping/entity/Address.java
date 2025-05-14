package com.longyi.shopping.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 地址
 * </p>
 *
 * @author 龙毅
 * @since 2024-11-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Address对象", description="地址")
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户地址ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户ID")
    private Integer user;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "省份")
    private String province;

    @ApiModelProperty(value = "城市")
    private String city;

    @ApiModelProperty(value = "小区")
    private String district;

    @ApiModelProperty(value = "地址状态，1默认，0，删除，2常用")
    private Integer status;

    @ApiModelProperty(value = "收货姓名")
    private String name;

    @ApiModelProperty(value = "联系方式")
    private String telephone;

    @ApiModelProperty(value = "经度")
    private BigDecimal lon;

    @ApiModelProperty(value = "纬度")
    private BigDecimal lat;


}
