package com.ruci.param;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ItemParam {

    private Integer tid;

    private Integer uid;

    private String title;

    private BigDecimal price;

    private String descrption;

    private Integer sales;

    private String imgUrl;

    private Date createDate;



}
