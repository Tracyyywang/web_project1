#AI Log  -  Week 01

## Session 1:Undersanding HelloController

### My Prompt
我是第一次学习 Web 编程的初学者。
我刚完成了 Week 01 的第一个 Spring Boot Controller。

代码如下：
@RestController
public class HelloController {
@GetMapping("/hello")
public String hello() {
return "Hello, I Exist.";
}
}

请解释：
1. @RestController 有什么作用？删除它会发生什么？
2. @GetMapping("/hello") 如何与浏览器 URL 对应？
3. 浏览器发送 GET /hello 后，程序内部按什么步骤处理？
4. return 的值如何成为 HTTP 响应？
5. String 返回类型代表什么？未来还可能返回什么？

请按完全没有 Spring Boot 基础的水平解释，每题 3 到 4 句话。

### AI's Pesponse

**1. @RestController 有什么作用？删除它会发生什么？**

`@RestController` 告诉 Spring Boot：“这个类是专门处理 Web 请求的，请把它登记为请求处理器。”Spring Boot 启动时会扫描所有类，只有带这个注解的类才会被当成 Controller 来管理。如果删除它，Spring 就看不到这个类，浏览器访问 `/hello` 时会返回 404，因为没有任何方法负责处理这个路径。类本身不会报错，应用也能启动，只是这个 Controller 不生效。

---

**2. @GetMapping("/hello") 如何与浏览器 URL 对应？**

`@GetMapping("/hello")` 的意思是：“当有人用 GET 方式请求 `/hello` 这个路径时，就调用下面的 `hello()` 方法。”浏览器地址 `http://localhost:8080/hello` 中，`localhost` 是本机，`8080` 是端口，`/hello` 就是路径。Spring Boot 在启动时会把所有 `@GetMapping` 的路径记下来，收到请求后按路径去匹配。路径必须完全一致，大小写也要一致，写错就找不到方法，返回 404。

---

**3. 浏览器发送 GET /hello 后，程序内部按什么步骤处理？**

浏览器先向 `localhost:8080` 发送一个 HTTP 请求，方法是 GET，路径是 `/hello`。Spring Boot 内置的服务器（Tomcat）在 8080 端口收到请求，然后交给 Spring MVC 去查找匹配的处理方法。Spring 发现 `HelloController` 上有 `@GetMapping("/hello")`，就调用它的 `hello()` 方法。方法执行完返回一个字符串，Spring 再把这个字符串包装成 HTTP 响应发回浏览器。

---

**4. return 的值如何成为 HTTP 响应？**

因为类上有 `@RestController`，Spring 会把方法的返回值直接当作 HTTP 响应正文，而不是去跳转某个页面。`return "Hello, I Exist.";` 执行后，这个字符串被放进响应体，同时状态码默认设为 200 OK。浏览器收到响应后，就把响应体里的文字显示在页面上。所以你在浏览器看到的文字，就是 `return` 后面那串内容。

---

**5. String 返回类型代表什么？未来还可能返回什么？**

`String` 表示这个方法返回的是一段纯文本，浏览器会原样显示，不会当成网页或 JSON 解析。Week 01 的 `/hello` 和 `/me` 都是纯文本，所以用 `String` 就够了。以后学了 JSON，可以返回对象，比如 `User`、`Post`，Spring 会自动把它转成 JSON 格式。再往后还可以返回 `ResponseEntity`，这样能自己控制状态码、响应头和响应体。
