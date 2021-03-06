# 淘金商城
**技术架构**：mysql+mybatis+spring+springmvc+redis+solr+httpclient

**项目描述**
淘金网上商城是一个综合性的B2C平台，类似京东商城、天猫商城。会员可以在商城浏览商品、下订单。淘金商城采用分布式系统架构，子系统之间都是调用服务来实现系统之间的通信，使用http协议传递json数据方式实现。这样降低了系统之间的耦合度，提高了系统的扩展性。为了提高系统的性能使用redis做系统缓存，并使用redis实现session共享。为了保证redis的性能使用redis的集群。

**系统模块**
+ 后台管理系统：管理商品、订单、类目、商品规格属性、用户管理以及内容发布等功能。
+ 前台系统：用户可以在前台系统中进行注册、登录、浏览商品、首页、下单等操作。
+ 订单系统：提供下单、查询订单、修改订单状态。
+ 搜索系统：提供商品的搜索功能。
+ 单点登录系统：为多个系统之间提供用户登录凭证以及查询登录用户的信息。

**系统架构**
> 整个系统架构分为四层，表现层，SOA业务服务层，系统服务层，数据层。
+ 表现层即为用户服务的，包含系统首页、商品展示、商品搜索、购物车、订单等。
+ SOA（面向服务架构）业务服务层则通过http协议为表现层提供相应服务，例如商品搜索、商品详情查询、订单服务、单点登陆功能
+ 系统服务层搭建了reids缓存服务集群和搜索服务集群，提高了商品的查询速度并且减轻了数据库压力
+ 数据层则是用mysql数据库对系统各项数据进行存储。
