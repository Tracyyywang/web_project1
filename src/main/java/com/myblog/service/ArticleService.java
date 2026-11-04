package com.myblog.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.myblog.mapper.ArticleMapper;
import com.myblog.model.Article;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

//Service 负责做校验、处理逻辑、调用 Mapper 去操作数据库。
@Service
public class ArticleService {

    private final ArticleMapper articleMapper;

    /*
    构造器注入，是强制依赖，管家一旦上任，必然配齐了传菜员才能干活。
     */
    public ArticleService(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    //发布文章
    public Article create(Article article) {
        //前端要是传了个空标题或空内容，直接扔出一个异常，这个异常会被@RestControllerAdvice拦截器捕获，变成标准格式返回给前端。
        if (article.getTitle() == null || article.getTitle().isBlank()) {
            throw new IllegalArgumentException("title 不能为空");
        }

        if (article.getContent() == null || article.getContent().isBlank()) {
            throw new IllegalArgumentException("content 不能为空");
        }

        //前端不用传时间。如果没传时间，系统自动抓取当前时间填进去。
        if (article.getCreatedAt() == null) {
            article.setCreatedAt(LocalDateTime.now());
        }

        //调用 insert 把数据写进数据库
        articleMapper.insert(article);
        return article;
    }

    //查询单篇文章
    public Article getById(Long id) {
        return Optional.ofNullable(articleMapper.selectById(id))
                .orElseThrow(() ->
                        new NoSuchElementException("article not found"));
        /*
        如果找到了，就返回这篇 Article。
        如果没找到（数据库返回 null），直接抛出 NoSuchElementException("article not found") 报错。
         */
    }

    //修改文章
    public Article update(Long id, Article patch) {
        // 1. 先看看原文章在不在（不在会直接抛错）
        Article existing = getById(id);

        // 2. 局部更新：只有传了新值才覆盖
        if (patch.getTitle() != null) {
            existing.setTitle(patch.getTitle());
        }
        if (patch.getContent() != null) {
            existing.setContent(patch.getContent());
        }

        // 3. 保存修改
        articleMapper.updateById(existing);
        return existing;
    }

    //删除文章
    public void delete(Long id) {
        int rows = articleMapper.deleteById(id);

        // deleteById 会返回一个整数，代表“删除了几行数据”。如果返回 0，说明数据库里根本没这个 ID，管家直接报错。
        if (rows == 0) {
            throw new NoSuchElementException("article not found");
        }
    }

    //带分页和关键字的搜索
    public Page<Article> search(String keyword, long page, long size) {
        // 1. 准备查询条件“大箩筐”
        LambdaQueryWrapper<Article> wrapper =
                new LambdaQueryWrapper<>();

        // 2. 如果有关键字，查标题 或 查内容
        if (keyword != null && !keyword.isBlank()) {
            wrapper.like(Article::getTitle, keyword)
                    .or()
                    .like(Article::getContent, keyword);
        }

        // 3. 按时间倒序（最新的在最前面）
        wrapper.orderByDesc(Article::getCreatedAt);

        // 4. 调用分页查询
        return articleMapper.selectPage(
                new Page<>(page, size),
                wrapper
        );
    }
}
/*
Service 层负责业务判断、查询条件拼装和找不到数据时抛出异常。
它不直接处理 HTTP，也不负责生成 JSON。
 */