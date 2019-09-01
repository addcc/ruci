package com.ruci.controller;

import com.ruci.common.JsonData;
import com.ruci.dto.MenuDto;
import com.ruci.service.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/home")
public class MeunController {

    @Autowired
    private TreeService treeService;

    @RequestMapping("/index")
    @ResponseBody
    public JsonData index(Model model){
        List<MenuDto> dto=treeService.aclModuleTree();

        model.addAttribute("dto",dto);
        return JsonData.success(200,dto);
    }

}
