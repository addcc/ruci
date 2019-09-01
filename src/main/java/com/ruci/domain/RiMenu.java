package com.ruci.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class RiMenu {

    @TableId(type = IdType.AUTO)
    private Integer id;

    //菜单名称
    private String menuName;
    //上级id
    private Integer parentId;
    //排序
    private Integer sort;
    //递归关系
    private String leven;
    //创建时间
    private Date createDate;
    //更新时间
    private Date updateDate;
}
