package com.myblog.mapper;

//让程序能够对数据库里的 message 表进行增删改查，而不需要写任何 SQL 语句。

import com.baomidou.mybatisplus.core.mapper.BaseMapper; //声明这个接口所在的路径
import com.myblog.model.Message; //引入 MyBatis-Plus 提供的核心基础接口 BaseMapper。这是不写 SQL 的关键
import org.apache.ibatis.annotations.Mapper; //引入你之前建好的实体类 Message

@Mapper
public interface MessageMapper extends BaseMapper<Message> {
}