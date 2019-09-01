package com.ruci.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruci.domain.Item;

import java.util.List;

public interface ItemMapper extends BaseMapper<Item> {

    List<Item> selectByIdFormList();

    void updateByTId(Integer tid);

    List<Integer> selectByUidFormList(Integer uid);
}
