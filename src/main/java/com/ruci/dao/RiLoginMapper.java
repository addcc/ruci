package com.ruci.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruci.domain.RiLogin;
import org.apache.ibatis.annotations.Param;

public interface RiLoginMapper extends BaseMapper<RiLogin> {

     RiLogin findByUsername(@Param("login_name") String loginName);

     RiLogin selectByName(@Param("login_name")String loginName);

     void insertBy(RiLogin login);


}
