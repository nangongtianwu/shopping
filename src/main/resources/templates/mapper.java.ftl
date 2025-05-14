package ${package.Mapper};

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ${package.Entity}.${entity};
/**
* <p>
    * ${table.comment!} Mapper接口类
    * </p>
*
* @author ${author}
* @since ${date}
*/
@Mapper
public interface ${entity}Mapper extends BaseMapper<${entity}> {
IPage PageList(IPage<${entity}> page, @Param(Constants.WRAPPER) Wrapper wrapper);
}
