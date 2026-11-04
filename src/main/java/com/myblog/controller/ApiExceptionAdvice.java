package com.myblog.controller;

import com.myblog.dto.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class ApiExceptionAdvice {

    @ExceptionHandler(NoSuchElementException.class)
    public Result<Void> notFound(NoSuchElementException ex) {
        return Result.fail(
                404,
                ex.getMessage() == null
                        ? "not found"
                        : ex.getMessage()
        );
    }
}
/*
Service 抛出 NoSuchElementException
→ ApiExceptionAdvice 捕获
→ 返回统一的 JSON 结构

异常处理可以把找不到资源时的错误转换成统一的 JSON 响应，
避免浏览器返回 HTML 错误页或不一致的错误格式。
 */