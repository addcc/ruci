package com.ruci.controller;

import com.ruci.domain.RiRole;
import com.ruci.param.PageInfo;
import com.ruci.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/system/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/rolelist/{pageNum}")
    public String rolelist(Model model, @PathVariable("pageNum")long pageNum){
        PageInfo<RiRole> roles=roleService.rolelist(pageNum);

        model.addAttribute("roles",roles);
        return "system/rolelist";
    }

    @RequestMapping("/roledelete/{rid}")
    public String roledelete(@PathVariable("rid")Integer rid){
        roleService.delete(rid);

        return "/system/role/rolelist/1";
    }
}
