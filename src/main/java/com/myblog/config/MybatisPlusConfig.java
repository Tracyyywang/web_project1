package com.myblog.config;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor; //MyBatis-Plus 的总拦截器，可以往里塞很多“内部拦截器”。
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor; //专门负责分页的内部拦截器。
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//告诉Mybatis，怎么用分页，后续写分页查询就不用自己拼LIMIT和COUNT了
//告诉 Spring“这是一个配置类”。
@Configuration
public class MybatisPlusConfig {
    //告诉 Spring“这个方法返回的对象，请帮我管理起来”。
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor(); //创建总拦截器
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL)); //创建一个分页拦截器，并告诉它“数据库是 MySQL”,把它塞进总拦截器里
        return interceptor;
    }
}