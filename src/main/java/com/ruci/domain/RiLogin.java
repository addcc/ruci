package com.ruci.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;


@Data
public class RiLogin {

    @TableId(type = IdType.AUTO)
    private Integer uid;
    private String loginName;
    private String password;

    @TableField(exist = false)
    private Set<RiRole> riRoles=new HashSet<>();

}
