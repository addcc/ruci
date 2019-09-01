package com.ruci.service;

import com.ruci.dao.RiMenuMapper;
import com.ruci.domain.RiMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    @Autowired
    private RiMenuMapper riMenuMapper;

    public List<RiMenu> selectList(){
        List<RiMenu> riMenus=riMenuMapper.selectFormList();
        return riMenus;
    }
}
