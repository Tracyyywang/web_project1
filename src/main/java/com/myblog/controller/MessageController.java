package com.myblog.controller;

import com.myblog.model.Message;
import com.myblog.model.MessageRequest;
import com.myblog.service.MessageService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//一、入口
@RestController
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService){
        this.messageService = messageService;
    }

    //发送信息，接受post请求，由requestbody把请求体中的 JSON 转成 Java 对象。
    @PostMapping("/messages")
    public Message create(@RequestBody MessageRequest request){
        return messageService.create(request);
    }

    //查询信息
//    分页前查询信息
//    @GetMapping("/messages")
//    public List<Message> list(@RequestParam(required = false) String keyword){
//
//        List<Message> all = messageService.list();
//        if (keyword == null){
//            return all;
//        }
//
//        List<Message> result = new ArrayList<>();
//        for (int i = 0;i < all.size();i++){
//            Message m = all.get(i);
//            if(m.getContent().contains(keyword)){
//                result.add(m);
//            }
//        }
//        return result;
//    }

//    新增分页方法查询信息
    @GetMapping("/messages")
    public com.myblog.dto.PageResult<Message> list(
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "1") long page,
            @RequestParam(defaultValue = "5") long size) {
        return com.myblog.dto.PageResult.of(messageService.search(keyword, page, size));
    }
    @GetMapping("/messages/{id}")
    public Optional<Message> findById(@PathVariable Long id) {
        return messageService.findById(id);
    }

}
