package com.ruci.controller;

import com.ruci.dto.PermissionDto;
import com.ruci.service.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/system/permission")
public class PermissionController {

    @Autowired
    private TreeService treeService;

    @RequestMapping("/list")
    public String list(Model model){
        List<PermissionDto> permissionDtos=treeService.permissionTree();
        model.addAttribute("permissionDtos",permissionDtos);

        return "permissionlist";
    }
}
