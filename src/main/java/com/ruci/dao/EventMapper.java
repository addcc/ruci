package com.ruci.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruci.domain.Event;

public interface EventMapper extends BaseMapper<Event> {

    void updateByItermId(Integer itemId);
}
