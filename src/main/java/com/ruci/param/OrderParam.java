package com.ruci.param;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderParam {

    private Integer oid;

    private Integer uid;
    private Integer tid;
    private String title;
    private Integer count;
    private String imgUrl;
    private BigDecimal price;
    private Date createDate;
    private Integer ownerId;


}
