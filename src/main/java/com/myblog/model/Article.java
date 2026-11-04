package com.myblog.model;

import com.baomidou.mybatisplus.annotation.TableField; // 贴在类名的头上。用来告诉程序：“这个Java类对应数据库里的哪张表”。
import com.baomidou.mybatisplus.annotation.TableName;  //贴在类的属性（变量）上。用来告诉程序：“这个变量对应数据库表里的哪个具体列”。

import java.time.LocalDateTime;

@TableName("article")
public class Article {
    private Long id;
    private String title;
    private String content;

    @TableField("created_at")
    private LocalDateTime createdAt;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getContent(){
        return content;
    }

    public void setContent(String content){
        this.content = content;
    }

    public LocalDateTime getCreatedAt(){
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt){
        this.createdAt = createdAt;
    }
}
