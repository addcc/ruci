package com.ruci.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruci.domain.RiUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RiUserMapper extends BaseMapper<RiUser> {


    @Select("SELECT * FROM ri_user")
    public List<RiUser> selectFromList();
}
