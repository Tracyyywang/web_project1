package com.myblog.repository;
import com.myblog.model.Message;

import java.util.List;
import java.util.Optional;

/**
 * The boundary the Service talks to.
 * The Service only knows "somewhere that can save and find messages".
 * It does NOT know (or care) whether the resident is
 * an in-memory list (Week05) or MySQL (Week06).
 */
// 五、仓库接口，规定了仓库存储必须支持保存信息、查询全部信息、根据id查询信息
public interface MessageRepository {

    /** Store a message; the implementation assigns the id. */
    Message save(Message message);

    /** All stored messages, in insertion order. */
    List<Message> findAll();

    /** Find one message by id; empty box if not found. */
    Optional<Message> findById(Long id);
}

