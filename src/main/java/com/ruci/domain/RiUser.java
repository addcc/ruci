package com.ruci.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class RiUser {

    @TableId(type = IdType.INPUT)
    private Integer id;

    private String name;

    //
    private Integer age;
    //
    private String email;
    //
    private String phone;
    //
    private Integer gender;
    //
    private Date createDate;
    //
    private Date updateDate;
    //

}
