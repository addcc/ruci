package com.ruci.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;


@Data
public class Item {

    @TableId(type = IdType.AUTO)
    private Integer tid;

    private Integer uid;

    private String title;

    private BigDecimal price;

    private String descrption;

    private Integer sales;

    private String imgUrl;

    private Date createDate;


}