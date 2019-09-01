package com.ruci.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
public class RiRole {
    @TableId(type = IdType.AUTO)
    private Integer rid;

    //角色名称
    private String roleName;
    //排序
    private Integer sort;
    //创建时间
    private Date createDate;
    //更新时间
    private Date updateDate;
    //描述
    private String description;

    @TableField(exist = false)
    private Set<RiLogin> logins=new HashSet<>();
    @TableField(exist = false)
    private Set<Permission> permissions=new HashSet<>();
}
