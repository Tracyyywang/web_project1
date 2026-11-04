package com.myblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper; //引入mybatis-plus框架，包含基础的增删改查（CRUD）代码
import com.myblog.model.Article; //引入你自己写好的article实体类，告诉程序接下来要操作的数据长什么样
import org.apache.ibatis.annotations.Mapper; //引入Mapper标签

// 告诉Spring:启动项目的时候，自动把这个接口变成能用的对象，后续拿它去操作数据库。
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

}
/*
你声明了一个叫 ArticleMapper 的接口，并让它继承了“万能工具箱（BaseMapper）”，且专门负责 <Article>（文章）业务。
（因为师傅已经把最常用的“增删改查”全教给它了，所以大括号 { } 里面什么都不用写，它就已经会操作数据库了。）
 */