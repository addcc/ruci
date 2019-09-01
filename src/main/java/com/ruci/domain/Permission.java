package com.ruci.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class Permission {

    @TableId(type = IdType.AUTO)
    private Integer pid;
    private String name;
    //创建时间
    private Date createDate;
    //描述
    private String description;
    //父级id
    private Integer parentId;
    //排序
    private String level;
    //
    private Integer sort;
}
