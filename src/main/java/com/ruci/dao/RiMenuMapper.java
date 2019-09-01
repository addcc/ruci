package com.ruci.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruci.domain.RiMenu;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RiMenuMapper extends BaseMapper<RiMenu> {

    @Select( "SELECT * FROM ri_menu")
    public List<RiMenu> selectFormList();
}
