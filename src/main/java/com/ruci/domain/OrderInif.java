package com.ruci.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
public class OrderInif {

    @TableId(type = IdType.AUTO)
    private Integer oid;

    @TableField("uid")
    private Integer uid;

    @TableField("tid")
    private Integer tid;

    @TableField("title")
    private String title;

    @TableField("count")
    private Integer count;

    @TableField("create_date")
    private Date createDate;

    private Integer ownerId;

    @TableField("price")
    private BigDecimal price;

    @TableField("img_url")
    private String imgUrl;

}
