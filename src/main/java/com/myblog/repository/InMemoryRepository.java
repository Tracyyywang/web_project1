package com.myblog.repository;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.myblog.model.Message;
//import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * In-memory implementation: the data lives in this JVM process.
 * Restarting the application wipes everything —
 * this is INTENTIONAL for Week05. Week06 will replace this class
 * with a MySQL-backed one, without touching Service or Controller.
 */
//六、实际存储
//@Repository
public class InMemoryRepository implements MessageRepository {

    // final locks the REFERENCE, not the CONTENT:
    // add/remove are fine; re-pointing to a new list is forbidden.
    //内存数据库，程序重启之后消息会消失
    private final List<Message> messages = new ArrayList<>();

    private long nextId = 1;

    //发送信息
    @Override
    public Message save(Message message) {
        message.setId(nextId++);
        message.setCreatedAt(java.time.LocalDateTime.now());
        messages.add(message);
        return message;
    }

    //查询信息
    @Override
    public List<Message> findAll() {
        return messages;
        //return new ArrayList<>(messages);  // defensive copy: caller can't corrupt our list
        // ^ Actually Week05 tutorial said use messages directly; keep simple
    }

    @Override
    public Optional<Message> findById(Long id) {
        return messages.stream()
                .filter(m -> m.getId().equals(id))
                .findFirst();
    }

    //接口里有的方法，实现类必须全部实现，所以需要在这里补个同样签名的方法。
    @Override
    public Page<Message> search(String keyword, long page, long size){
        throw new UnsupportedOperationException("Week07 uses MySQL");
        //只要有人调用 InMemoryRepository.search(...)，就会立刻抛异常,表示内存版不支持搜索，Week07 起请用 MySQL 实现。
    }
}
