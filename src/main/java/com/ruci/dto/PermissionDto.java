package com.ruci.dto;

import com.google.common.collect.Lists;
import com.ruci.domain.Permission;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Data
public class PermissionDto extends Permission {

    private List<PermissionDto> permissionDtoList= Lists.newArrayList();

    //传参
    public static PermissionDto adapt(Permission permission) {
        PermissionDto dto=new PermissionDto();
        BeanUtils.copyProperties(permission, dto);
        return dto;
    }
}
