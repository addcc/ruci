package com.ruci.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruci.domain.OrderInif;

import java.util.List;

public interface OrderMapper extends BaseMapper<OrderInif> {

    void insertSelective(OrderInif order);

    List<OrderInif> selectByTid(Integer tid);
}
