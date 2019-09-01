package com.ruci.param;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class EventParam extends ItemParam{

    private Integer id;

    private BigDecimal eventPrice;
    private Date startDate;
    private Date endDate;
    private Integer stoct;

}
