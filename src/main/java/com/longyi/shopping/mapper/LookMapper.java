package com.longyi.shopping.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.longyi.shopping.entity.Look;
/**
* <p>
    * 浏览 Mapper接口类
    * </p>
*
* @author 龙毅
* @since 2024-11-14
*/
@Mapper
public interface LookMapper extends BaseMapper<Look> {
IPage PageList(IPage<Look> page, @Param(Constants.WRAPPER) Wrapper wrapper);
}
