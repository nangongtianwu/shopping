package com.longyi.shopping.common;
import lombok.Data;

import java.util.HashMap;

@Data
public class QueryPageParam {
    // 默认每页数量
    private static int PAGE_SIZE=20;
    // 默认页码
    private static int PAGE_NUM=1;

    // 每页数量
    private int pageSize=PAGE_SIZE;
    // 页码
    private int pageNum=PAGE_NUM;

    // 查询参数
    private HashMap<String, Object> param = new HashMap<>();
}