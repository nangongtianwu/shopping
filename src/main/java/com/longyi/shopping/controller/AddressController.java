package com.longyi.shopping.controller;


import com.longyi.shopping.entity.Province;
import com.longyi.shopping.service.AddressService;
import com.longyi.shopping.entity.Address;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import com.longyi.shopping.common.QueryPageParam;
import com.longyi.shopping.common.Result;

/**
 * Address 前端控制器
 *
 * @author 龙毅
 * @since 2024-11-14
 */
@RestController
@RequestMapping("/address")
public class AddressController {
    //注入service
    @Autowired
    private AddressService addressService;

    @GetMapping("/provinceList")
    public List<Province> provinceList() {
        List<Address> list = addressService.list();
        HashSet<String> provinces = new HashSet<>();
        for (Address address : list) {
            provinces.add(address.getProvince());

        }
        List<Province> provinceList = new ArrayList<>();
        int id = 0;
        for (String name : provinces) {
            Province province = new Province();
            province.setId(id++);
            province.setName(name);
            provinceList.add(province);

        }
        return provinceList;

    }

    //统计数量
    @GetMapping("/count")
    public Integer count() {
        return addressService.count();
    }

    //列表
    @GetMapping("/list")
    public List<Address> list() {
        return addressService.list();
    }

    //新增
    @PostMapping("/save")
    public Result save(@RequestBody Address address) { List<Address> list = addressService.lambdaQuery().eq(Address::getUser, address.getUser()).list();
        if (list.size() > 10) {
            return Result.fail("每人最多添加10个地址");
        }
        if (list.size() > 0) {
            list.forEach(item -> {
                if (item.getStatus() == 2) {
                    item.setStatus(1);
                    addressService.updateById(item);
                }

            });
        }
        return addressService.save(address) ? Result.suc("新增成功") : Result.fail("新增失败");
    }

    //修改
    @PostMapping("/update")
    public Result update(@RequestBody Address address) {
        return addressService.updateById(address) ? Result.suc("修改成功") : Result.fail("修改失败");
    }

    //详情
    @GetMapping("/info/{id}")
    public Result info(@PathVariable("id") Long id) {
        Address address = addressService.getById(id);
        return Result.suc(address);
    }

    //删除
    @Transactional
    @PostMapping("/deleteBatch")
    public Result deleteBatch(@RequestBody Long[] ids) {
        for (Long id : ids) {
            Address address = addressService.getById(id);
            address.setStatus(0);
            addressService.updateById(address);
        }
        return Result.suc("批量删除成功");
    }
    @PostMapping("/setDefault/{id}")
    public Result setDefault(@PathVariable("id") Long id) {
        Address address = addressService.getById(id);
        List<Address> list = addressService.lambdaQuery().eq(Address::getUser,address.getUser()).list();
        list.forEach(item -> {
            if (item.getStatus() == 2) {
                item.setStatus(1);
                addressService.updateById(item);
            }
        });
        address.setStatus(2);

        return addressService.updateById(address) ? Result.suc("修改成功") : Result.fail("修改失败");

    }
    //分页查询
    @PostMapping("/listPage")
    public Result listPage(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();
        String user = (String) param.get("user");
        String address = (String) param.get("address");
        String province = (String) param.get("province");
        String status = (String) param.get("status");
        String name = (String) param.get("name");
        String telephone = (String) param.get("telephone");
        String role = (String) param.get("role");
        Page<Address> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());
        LambdaQueryWrapper<Address> lambdaQueryWrapper = new LambdaQueryWrapper();

        if (StringUtils.isNotBlank(user) && !"null".equals(user)) {
            lambdaQueryWrapper.eq(Address::getUser, user);
        }
        if (StringUtils.isNotBlank(address) && !"null".equals(address)) {
            lambdaQueryWrapper.like(Address::getAddress, address);
        }
        if (StringUtils.isNotBlank(province) && !"null".equals(province)) {
            lambdaQueryWrapper.like(Address::getProvince, province);
        }

        if (StringUtils.isNotBlank(status) && !"null".equals(status)) {
            lambdaQueryWrapper.eq(Address::getStatus, status);
        }
        if (StringUtils.isNotBlank(name) && !"null".equals(name)) {
            lambdaQueryWrapper.like(Address::getName, name);
        }
        if (StringUtils.isNotBlank(telephone) && !"null".equals(telephone)) {
            lambdaQueryWrapper.like(Address::getTelephone, telephone);
        }
        if (role.equals("3")) {
            lambdaQueryWrapper.ne(Address::getStatus, 0);
        }
        lambdaQueryWrapper.orderByDesc(Address::getStatus);
        IPage result = addressService.PageList(page, lambdaQueryWrapper);
        return Result.suc(result.getTotal(), result.getRecords());
    }
}
