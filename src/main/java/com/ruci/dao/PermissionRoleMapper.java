package com.ruci.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruci.domain.PermissionRole;

public interface PermissionRoleMapper extends BaseMapper<PermissionRole> {

    void deleteByPid(Integer pid);
    void deleteByRid(Integer rid);
}
