# springboot-safeapi
springboot-多数据源

mysql实现-多数据源

sqlSessionFactory，得多了解一下mybatis。才好掌握多数据源。
今天回去看下mybatis的课程

创建两个数据库demo1[book]、demo2[user]

```mysql
create table Book
(
    id          int auto_increment
        primary key,
    author      varchar(64)  not null comment '作者信息',
    name        varchar(64)  not null comment '书籍名称',
    price       decimal      not null comment '价格',
    createTime  datetime     null comment '上架时间',
    description varchar(128) null comment '书籍描述'
);
-- insert data
INSERT INTO demo1.Book (id, author, name, price, createTime, description) VALUES (1, '金庸', '笑傲江湖', 13, '2020-12-19 15:26:51', '武侠小说');
INSERT INTO demo1.Book (id, author, name, price, createTime, description) VALUES (2, '罗贯中', '三国演义', 14, '2020-12-19 15:28:36', '历史小说');
create table User
(
    id       int auto_increment
        primary key,
    name     varchar(32) null comment '用户名称',
    birthday date        null comment '出生日期'
);

-- insert data
INSERT INTO demo2.User (id, name, birthday) VALUES (1, '金庸', '1924-03-10');
INSERT INTO demo2.User (id, name, birthday) VALUES (2, '罗贯中', '1330-01-10');
```

##### 开启springboot的端点监控
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```
浏览器访问 http://localhost:9000/actuator  

##### 添加mybatis的#和$测试

