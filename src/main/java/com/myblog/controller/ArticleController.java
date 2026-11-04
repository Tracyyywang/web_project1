package com.myblog.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.myblog.dto.Result;
import com.myblog.model.Article;
import com.myblog.service.ArticleService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController // 告诉系统：这是一个专门接待 Web 请求的前台，并且返回的是 JSON 格式
@RequestMapping("/articles") // 告诉系统：所有关于“文章”的请求，路径都要以 /articles 开头
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    //发布文章
    @PostMapping
    public Result<Article> create(@RequestBody Article article) {
        return Result.ok(articleService.create(article));
        /*
        前端发来一篇新文章（@RequestBody 把前端发来的 JSON 变成 Java 的 Article 对象）。
         */
    }

    //按id读取单篇文章
    @GetMapping("/{id}")
    public Result<Article> getById(@PathVariable Long id) {
        return Result.ok(articleService.getById(id));
    }

    //搜索与分页
    @GetMapping
    public Result<Map<String, Object>> search(
            /*
            前端在搜索框输入了关键字，并翻到了第几页。
            @RequestParam 负责从网址的 ? 后面抓取参数。如果前端没传，就默认搜空字符串（全查），第 1 页，每页 5 条。
             */
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "1") long page,
            @RequestParam(defaultValue = "5") long size) {

        Page<Article> result =
                articleService.search(keyword, page, size);

        Map<String, Object> data = new HashMap<>();
        data.put("items", result.getRecords()); // 当前页的文章列表
        data.put("total", result.getTotal()); // 数据库里总共有多少篇
        data.put("page", result.getCurrent()); //当前是第几页
        data.put("size", result.getSize()); //每页几条

        return Result.ok(data);
    }

    //修改文章
    @PutMapping("/{id}")
    public Result<Article> update(
            @PathVariable Long id,
            @RequestBody Article article) {

        return Result.ok(articleService.update(id, article));
    }

    //删除文章
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        articleService.delete(id);
        return Result.ok(null);
    }
}
/*
整个 Controller 里没有任何复杂的 if-else 判断逻辑，也没有 try-catch。
为什么？因为那些脏活累活（比如判断文章在不在、标题空不空）全都在上一层的 ArticleService 和全局异常处理器里做完了。

Controller 做到了极致的“薄”（Thin Controller）：
迎接请求。
把数据丢给 Service。
拿回结果，塞进 Result 盒子，送客。
 */