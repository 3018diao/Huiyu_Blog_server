package com.huiyu.blog.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaginationResult<T> {
    private long total;
    private long pageNo;
    private long pageSize;
    private long pages;
    private List<T> list;
}
