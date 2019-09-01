package com.ruci.param;

import lombok.Data;

import java.util.List;

@Data
public class PageInfo<T> {

    // 当前页
    private long pageNum ;
    // 每页的数量
    private long pageSize ;

    // 总记录数
    private long total;
    // 总页数
    private int pages;
    // 结果集
    private List<T> list;
    //按名字查询的名字
    private String tName;

}
