# 数据库设计文档

**数据库名：** shopping

**文档版本：** 1.0.0

**文档描述：** 数据库设计文档生成

| 表名                  | 说明       |
| :---: | :---: |
| [address](#address) | 地址 |
| [collect](#collect) | 收藏 |
| [comment](#comment) | 评论 |
| [goods](#goods) | 商品 |
| [goodsorder](#goodsorder) | 商品订单 |
| [look](#look) | 浏览 |
| [menu](#menu) | 菜单 |
| [recharge](#recharge) | 充值 |
| [refund](#refund) | 退款 |
| [shoporder](#shoporder) | 购物订单 |
| [shopping](#shopping) | 购物 |
| [type](#type) | 商品类型 |
| [user](#user) | 用户 |

**表名：** <a id="address">address</a>

**说明：** 地址

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   int   | 10 |   0    |    N     |  Y   |       | 用户地址ID  |
|  2   | user |   int   | 10 |   0    |    N     |  N   |       | 用户ID  |
|  3   | address |   varchar   | 255 |   0    |    N     |  N   |       | 地址  |
|  4   | province |   varchar   | 255 |   0    |    N     |  N   |       | 省份  |
|  5   | city |   varchar   | 255 |   0    |    Y     |  N   |       | 城市  |
|  6   | district |   varchar   | 255 |   0    |    Y     |  N   |       | 小区  |
|  7   | status |   int   | 10 |   0    |    N     |  N   |   1    | 地址状态，1默认，0，删除，2常用  |
|  8   | name |   varchar   | 255 |   0    |    N     |  N   |       | 收货姓名  |
|  9   | telephone |   varchar   | 18 |   0    |    N     |  N   |       | 联系方式  |
|  10   | lon |   decimal   | 11 |   7    |    Y     |  N   |       | 经度  |
|  11   | lat |   decimal   | 11 |   7    |    Y     |  N   |       | 纬度  |

**表名：** <a id="collect">collect</a>

**说明：** 收藏

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   int   | 10 |   0    |    N     |  Y   |       | 收藏ID  |
|  2   | user |   int   | 10 |   0    |    N     |  N   |       | 收藏用户  |
|  3   | relation |   int   | 10 |   0    |    N     |  N   |       | 收藏商品  |
|  4   | status |   int   | 10 |   0    |    N     |  N   |   1    | 收藏状态，1收藏，0取消收藏  |
|  5   | create_time |   datetime   | 19 |   0    |    N     |  N   |   CURRENT_TIMESTAMP    | 收藏时间  |
|  6   | modules |   varchar   | 255 |   0    |    Y     |  N   |   商品    | 模块  |

**表名：** <a id="comment">comment</a>

**说明：** 评论

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   int   | 10 |   0    |    N     |  Y   |       | 商品评论ID  |
|  2   | pid |   int   | 10 |   0    |    Y     |  N   |   0    | 父级ID  |
|  3   | user |   int   | 10 |   0    |    N     |  N   |       | 用户ID  |
|  4   | relation |   int   | 10 |   0    |    N     |  N   |       | 商品ID  |
|  5   | content |   varchar   | 2000 |   0    |    N     |  N   |       | 评价  |
|  6   | rating |   int   | 10 |   0    |    N     |  N   |       | 评分  |
|  7   | create_time |   datetime   | 19 |   0    |    N     |  N   |   CURRENT_TIMESTAMP    | 评论日期  |
|  8   | status |   int   | 10 |   0    |    N     |  N   |   1    | 删除状态，正常为1，已删除为0  |
|  9   | modules |   varchar   | 255 |   0    |    Y     |  N   |   商品    | 模块  |

**表名：** <a id="goods">goods</a>

**说明：** 商品

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   int   | 10 |   0    |    N     |  Y   |       | 主键  |
|  2   | name |   varchar   | 100 |   0    |    N     |  N   |       | 商品名称  |
|  3   | business |   int   | 10 |   0    |    N     |  N   |       | 商家  |
|  4   | type |   int   | 10 |   0    |    N     |  N   |       | 商品分类  |
|  5   | image |   varchar   | 255 |   0    |    Y     |  N   |       | 商品图片  |
|  6   | price |   int   | 10 |   0    |    N     |  N   |       | 商品价格  |
|  7   | remark |   varchar   | 1000 |   0    |    Y     |  N   |       | 商品介绍  |
|  8   | count |   int   | 10 |   0    |    N     |  N   |       | 商品库存  |
|  9   | unit |   varchar   | 255 |   0    |    N     |  N   |       | 计量单位  |
|  10   | sale |   int   | 10 |   0    |    N     |  N   |   0    | 已售数量  |
|  11   | create_time |   datetime   | 19 |   0    |    N     |  N   |   CURRENT_TIMESTAMP    | 创建时间  |
|  12   | click |   int   | 10 |   0    |    N     |  N   |   0    | 商品点击数  |
|  13   | collect |   int   | 10 |   0    |    N     |  N   |   0    | 商品收藏数  |
|  14   | comment |   int   | 10 |   0    |    N     |  N   |   0    | 商品评论数  |
|  15   | good |   int   | 10 |   0    |    N     |  N   |   0    | 五星好评数  |

**表名：** <a id="goodsorder">goodsorder</a>

**说明：** 商品订单

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   int   | 10 |   0    |    N     |  Y   |       | 订单ID  |
|  2   | code |   varchar   | 255 |   0    |    N     |  N   |       | 订单编号  |
|  3   | user |   int   | 10 |   0    |    N     |  N   |       | 下单用户  |
|  4   | money |   int   | 10 |   0    |    N     |  N   |       | 下单金额  |
|  5   | status |   int   | 10 |   0    |    N     |  N   |   0    | 订单状态，0待提交，1已支付，2未支付，3.已取消  |
|  6   | create_time |   datetime   | 19 |   0    |    N     |  N   |   CURRENT_TIMESTAMP    | 下单时间  |
|  7   | pay_time |   datetime   | 19 |   0    |    Y     |  N   |       | 支付时间  |
|  8   | address |   int   | 10 |   0    |    Y     |  N   |       | 订单地址  |

**表名：** <a id="look">look</a>

**说明：** 浏览

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   int   | 10 |   0    |    N     |  Y   |       | 点击ID  |
|  2   | user |   int   | 10 |   0    |    N     |  N   |       | 点击用户  |
|  3   | goods |   int   | 10 |   0    |    N     |  N   |       | 点击商品  |
|  4   | status |   int   | 10 |   0    |    N     |  N   |   1    | 点击状态，1点击，0已被清理  |
|  5   | create_time |   datetime   | 19 |   0    |    N     |  N   |   CURRENT_TIMESTAMP    | 点击时间  |

**表名：** <a id="menu">menu</a>

**说明：** 菜单

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   int   | 10 |   0    |    N     |  Y   |       |   |
|  2   | menuCode |   varchar   | 8 |   0    |    Y     |  N   |       | 菜单编码  |
|  3   | menuName |   varchar   | 16 |   0    |    Y     |  N   |       | 菜单名字  |
|  4   | menuLevel |   varchar   | 2 |   0    |    Y     |  N   |       | 菜单级别  |
|  5   | menuParentCode |   varchar   | 8 |   0    |    Y     |  N   |       | 菜单的父code  |
|  6   | menuClick |   varchar   | 16 |   0    |    Y     |  N   |       | 点击触发的函数  |
|  7   | menuRight |   varchar   | 100 |   0    |    Y     |  N   |       | 权限0超级管理员，1表示管理员，2表示商家，3表示用户，可以用逗号组合使用  |
|  8   | menuComponent |   varchar   | 200 |   0    |    Y     |  N   |       |   |
|  9   | menuIcon |   varchar   | 100 |   0    |    Y     |  N   |       |   |

**表名：** <a id="recharge">recharge</a>

**说明：** 充值

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   int   | 10 |   0    |    N     |  Y   |       | 充值记录ID  |
|  2   | user |   int   | 10 |   0    |    N     |  N   |       | 充值用户  |
|  3   | money |   int   | 10 |   0    |    N     |  N   |       | 充值金额  |
|  4   | create_time |   datetime   | 19 |   0    |    N     |  N   |   CURRENT_TIMESTAMP    | 充值时间  |

**表名：** <a id="refund">refund</a>

**说明：** 退款

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   int   | 10 |   0    |    N     |  Y   |       | 退款ID  |
|  2   | user |   int   | 10 |   0    |    Y     |  N   |       | 退款用户  |
|  3   | goods |   int   | 10 |   0    |    Y     |  N   |       | 退款商品  |
|  4   | business |   int   | 10 |   0    |    Y     |  N   |       | 退款商家  |
|  5   | order_id |   int   | 10 |   0    |    Y     |  N   |       | 退款订单  |
|  6   | money |   int   | 10 |   0    |    Y     |  N   |       | 退款金额  |
|  7   | create_time |   datetime   | 19 |   0    |    Y     |  N   |   CURRENT_TIMESTAMP    | 退款时间  |
|  8   | status |   int   | 10 |   0    |    Y     |  N   |   0    | 退款状态0待确认，2退款失败，1退款成功  |
|  9   | shop_id |   int   | 10 |   0    |    Y     |  N   |       | 退款订单  |

**表名：** <a id="shoporder">shoporder</a>

**说明：** 购物订单

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   int   | 10 |   0    |    N     |  Y   |       | 订单详情ID  |
|  2   | shopping |   int   | 10 |   0    |    N     |  N   |       | 购物ID  |
|  3   | order_id |   int   | 10 |   0    |    N     |  N   |       | 订单ID  |
|  4   | status |   int   | 10 |   0    |    N     |  N   |   1    | 订单状态，1正常，0申请退款  |

**表名：** <a id="shopping">shopping</a>

**说明：** 购物

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   int   | 10 |   0    |    N     |  Y   |       | 购物ID  |
|  2   | goods |   int   | 10 |   0    |    N     |  N   |       | 购物商品  |
|  3   | count |   int   | 10 |   0    |    N     |  N   |       | 购物数量  |
|  4   | money |   int   | 10 |   0    |    N     |  N   |       | 购物金额  |
|  5   | user |   int   | 10 |   0    |    N     |  N   |       | 购物用户  |
|  6   | create_time |   datetime   | 19 |   0    |    N     |  N   |   CURRENT_TIMESTAMP    | 购物时间  |
|  7   | status |   int   | 10 |   0    |    N     |  N   |   1    | 购物状态1正常，2已消费，0已删除或取消  |

**表名：** <a id="type">type</a>

**说明：** 商品类型

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   int   | 10 |   0    |    N     |  Y   |       | 主键  |
|  2   | name |   varchar   | 100 |   0    |    N     |  N   |       | 商品分类  |
|  3   | status |   int   | 10 |   0    |    N     |  N   |   1    | 审核状态，0待审核，1正常，2审核拒绝  |
|  4   | create_time |   datetime   | 19 |   0    |    N     |  N   |   CURRENT_TIMESTAMP    | 创建时间  |

**表名：** <a id="user">user</a>

**说明：** 用户

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   int   | 10 |   0    |    N     |  Y   |       | 主键  |
|  2   | username |   varchar   | 255 |   0    |    Y     |  N   |       | 用户名  |
|  3   | name |   varchar   | 100 |   0    |    N     |  N   |       | 账号  |
|  4   | password |   varchar   | 20 |   0    |    N     |  N   |       | 密码  |
|  5   | telephone |   varchar   | 20 |   0    |    Y     |  N   |       | 电话  |
|  6   | email |   varchar   | 255 |   0    |    Y     |  N   |       | 邮箱  |
|  7   | age |   int   | 10 |   0    |    Y     |  N   |       | 年龄  |
|  8   | sex |   int   | 10 |   0    |    N     |  N   |   1    | 性别  |
|  9   | role |   int   | 10 |   0    |    N     |  N   |   3    | 角色0超级管理员，1管理员，2商家，3用户  |
|  10   | status |   int   | 10 |   0    |    N     |  N   |   1    | 是否有效，1正常，0注销  |
|  11   | avatar |   varchar   | 255 |   0    |    N     |  N   |   avatar    | 头像  |
|  12   | create_time |   datetime   | 19 |   0    |    N     |  N   |   CURRENT_TIMESTAMP    | 注册时间  |
|  13   | update_time |   datetime   | 19 |   0    |    Y     |  N   |       | 修改时间  |
|  14   | amount |   int   | 10 |   0    |    N     |  N   |   0    | 累充金额  |
|  15   | balance |   int   | 10 |   0    |    N     |  N   |   0    | 账户余额  |
