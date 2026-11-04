package com.myblog.dto;

/*
创建 “统一结果包装器”
不管后端里面发生什么，前端收到的永远是这个形状的盒子，里面固定有三个隔层：状态码 code、message、data。
 */
public class Result<T> {
    //<T>：是泛型。相当于盒子上的“可变标签”。你今天想送一篇文章（Article），T 就是 Article；明天想送一堆留言（List<Message>），T 就是那个列表。

    public int code;
    public String message;
    public T data;

    //打包成功
    public static <T> Result<T> ok(T data) {
        Result<T> result = new Result<>();
        result.code = 0;
        result.message = "ok";
        result.data = data;
        return result;
    }

    //打包失败
    public static <T> Result<T> fail(int code, String message) {
        Result<T> result = new Result<>();
        result.code = code;
        result.message = message;
        result.data = null;
        return result;
    }
    /*失败例子:
      "code": 404,
      "message": "article not found",
      "data": null
     */
}
