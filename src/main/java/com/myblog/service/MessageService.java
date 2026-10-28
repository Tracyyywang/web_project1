package com.myblog.service;

import com.myblog.model.Message;
import com.myblog.model.MessageRequest;
import com.myblog.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The brain of MyBlog.
 * Responsibilities:
 *   1. Make business decisions (validate, translate request -> message)
 *   2. Delegate storage to the repository boundary
 * It knows NOTHING about HTTP (no annotations like @RequestBody here),
 * and NOTHING about where data actually lives (List? MySQL? doesn't care).
 */
//四、业务层
@Service
public class MessageService {

    // Constructor injection + final: the dependency is assigned once
    // at construction time and locked for life.
    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    /**
     * Accept a visitor's application form, produce a real message.
     * (Input shape: MessageRequest  ->  Output shape: Message)
     */
    //发送信息
    public Message create(MessageRequest request) {
        // Business rule: reject empty content
        if (request.content == null || request.content.isBlank()) { //检查内容是否为空
            throw new IllegalArgumentException("content must not be empty");
        }

        //把请求转化成完整的对象
        Message message = new Message();
        message.setContent(request.content);
        // id and createdAt are granted by the repository, not the visitor
        return messageRepository.save(message);
    }

    //查询信息，交给仓库保存
    public List<Message> list() {
        return messageRepository.findAll();
    }

    /** Honest return type: the message may or may not exist. */
    public Optional<Message> findById(Long id) {
        return messageRepository.findById(id);
    }

    //返回分页后结果到前端
    public com.baomidou.mybatisplus.extension.plugins.pagination.Page<Message> search(
            String keyword, long page, long size) {
        return messageRepository.search(keyword, page, size);
    }
}
