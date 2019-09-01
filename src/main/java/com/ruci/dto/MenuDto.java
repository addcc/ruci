package com.ruci.dto;

import com.google.common.collect.Lists;
import com.ruci.domain.RiMenu;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Data
public class MenuDto extends RiMenu{

    private List<MenuDto> aclModuleList = Lists.newArrayList();


    public static MenuDto adapt(RiMenu aclModule) {
        MenuDto dto = new MenuDto();
        BeanUtils.copyProperties(aclModule, dto);
        return dto;
    }
}
