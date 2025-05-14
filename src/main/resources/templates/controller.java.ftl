package ${package.Controller};


import ${package.Service}.${table.serviceName};
import ${package.Entity}.${entity};

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import ${cfg.parentPackage}.common.QueryPageParam;
import ${cfg.parentPackage}.common.Result;

/**
* ${entity} 前端控制器
*
* @author ${author}
* @since ${date}
*/
@RestController
@RequestMapping("/${table.entityPath}")
public class ${entity}Controller {
    //注入service
    @Autowired
    private  ${table.serviceName} ${table.entityPath}Service;
    //统计数量
    @GetMapping("/count")
    public Integer count() {
       return ${table.entityPath}Service.count();
    }
    //列表
    @GetMapping("/list")
    public List<${entity}> list() {
        return ${table.entityPath}Service.list();
    }
    //新增
    @PostMapping("/save")
    public Result save(@RequestBody ${entity} ${table.entityPath}) {
      return ${table.entityPath}Service.save(${table.entityPath}) ? Result.suc("新增成功") : Result.fail("新增失败");
    }
    //修改
    @PostMapping("/update")
    public Result update(@RequestBody ${entity} ${table.entityPath}) {
       return ${table.entityPath}Service.updateById(${table.entityPath})? Result.suc("修改成功") : Result.fail("修改失败");
    }
    //详情
    @GetMapping("/info/{id}")
    public Result info(@PathVariable("id") Long id) {
        ${entity} ${table.entityPath} = ${table.entityPath}Service.getById(id);
        return Result.suc(${table.entityPath});
    }
    //删除
    @Transactional
    @PostMapping("/deleteBatch")
    public Result deleteBatch(@RequestBody Long[] ids) {
        ${table.entityPath}Service.removeByIds(Arrays.asList(ids));
        return Result.suc("批量删除成功");
    }
    //分页查询
    @PostMapping("/listPage")
    public Result listPage(@RequestBody QueryPageParam query) {
    HashMap param = query.getParam();

        Page <${entity}> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());
        LambdaQueryWrapper<${entity}> lambdaQueryWrapper = new LambdaQueryWrapper();

        IPage result = ${table.entityPath}Service.PageList(page, lambdaQueryWrapper);
        return Result.suc( result.getTotal(),result.getRecords());
    }
}
