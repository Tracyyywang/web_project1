package com.myblog.dto; //专门放“用于和前端/外部交互的数据结构”
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;

//得到一个干净的、只包含前端真正需要的字段的对象
public record PageResult<T>(List<T> items, long total, long page, long size) {
    public static <T> PageResult<T> of(Page<T> p) {
        return new PageResult<>(p.getRecords(), p.getTotal(), p.getCurrent(), p.getSize()); //把 MyBatis-Plus 的 Page 转换成干净的 PageResult。
    }
}

/* PageResult 是一个用 record 写的分页结果 DTO，只有 items / total / page / size 四个字段，
通过静态方法 of() 把 MyBatis-Plus 的 Page 对象“瘦身”成前端真正需要的数据，避免把一堆内部字段暴露出去。*/