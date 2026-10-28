package com.myblog.repository;

//把底层的 MyBatis-Plus 数据库操作，包装成业务层能直接调用的方法。
import com.myblog.mapper.MessageMapper;
import com.myblog.model.Message;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
//这是一个操作数据库的组件，把它交给 Spring 容器管理。
public class DBMessageRepository implements MessageRepository {
    private final MessageMapper messageMapper;

    public DBMessageRepository(MessageMapper messageMapper) {
        this.messageMapper = messageMapper; //构造器注入
    /*
    构造器注入就是：你要什么零件，就在构造器（大门的入口）那里写上，Spring 大管家会在创建你的时候，自动把零件塞给你，你不需要自己去 new。
     */
    }

    @Override
    public Message save(Message message) {
        messageMapper.insert(message);
        /*
        调用 MyBatis-Plus 自带的 insert 方法，把 message 对象的数据插进 MySQL 的 message 表里。
         */
        return message;
    }

    @Override
    public List<Message> findAll() {
        return messageMapper.selectList(null);
        /*
        调用 MyBatis-Plus 自带的查询列表方法，传 null 表示不带任何查询条件（也就是查出全表数据）。
         */
    }

    @Override
    public Optional<Message> findById(Long id) {
        return Optional.ofNullable(messageMapper.selectById(id));
        /*
        去数据库里找这个 ID 的留言。如果找不到，它会返回 null。
        Optional.ofNullable(...)：把可能为 null 的结果装进 Optional 盒子里。
         */
    }

    @Override
    public com.baomidou.mybatisplus.extension.plugins.pagination.Page<Message> search(
            String keyword, long page, long size) {
        //创建查询条件容器
        var query = new com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper<Message>();
        //LambdaQueryWrapper 是 MyBatis-Plus 提供的条件构造器。你可以把它理解成“一个用来拼 WHERE 条件的盒子”

        //按照关键字模糊查找
        if (keyword != null && !keyword.isBlank()) { //防止空指针和空格
            query.like(Message::getContent, keyword);
        }
        //排序：先按创建时间倒序（最新的在前）；时间相同的话，再按 id 倒序（保证顺序稳定）= MySQL语法: ORDER BY created_at DESC,id DESC
        query.orderByDesc(Message::getCreatedAt).orderByDesc(Message::getId);
        //执行分页查询
        return messageMapper.selectPage( //messageMapper.selectPage(分页对象, 条件)，接收两个参数：分页对象（决定查第几页第几条）；条件构造器（决定WHERE和ORDER BY）
                new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(page, size), query); //拦截器介入
    }
}