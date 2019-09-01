package com.ruci.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class RiDept {
    @TableId(type = IdType.AUTO)
    private Integer id;

    //部门名称
    private String deptName;
    //部门排序
    private Integer sort;
    //上级id
    private Integer parentId;
    //描述
    private String description;
    //创建时间
    private Date createDate;
    //更新时间
    private Date updateDate;
    //递归关系
    private String leven;
}
