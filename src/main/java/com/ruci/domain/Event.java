package com.ruci.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Event {

    @TableId(type = IdType.INPUT)
    private Integer itemId;

    private BigDecimal eventPrice;

    private Date startDate;

    private Date endDate;
    private Integer stoct;

}
