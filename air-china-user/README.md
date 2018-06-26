## air-china-user
用户中心微服务
>（Air-China机票预订Web App的权限验证及授权应用）

#### 分层
* air-china-user-cache 缓存层
* air-china-user-common 公共通用层（所有项目）
* air-china-user-model 模型层
* air-china-user-mongo NoSQL支持层（MongoDB）
* air-china-user-repository 持久化层
* air-china-user-rest-common-web Web公共通用层
* air-china-user-rest-web Web层
* air-china-user-rest-spi 依赖服务层（此微服务依赖其他服务的Feign接口声明）
* air-china-user-service 业务层
* air-china-user-task 定时任务层
