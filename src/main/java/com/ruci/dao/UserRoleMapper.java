package com.ruci.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruci.domain.UserRole;

public interface UserRoleMapper extends BaseMapper<UserRole> {

    void deleteByRid(Integer rid);

    void deleteByUid(Integer uid);
}
