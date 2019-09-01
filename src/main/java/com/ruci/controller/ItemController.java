package com.ruci.controller;

import com.ruci.domain.Item;
import com.ruci.param.EventParam;
import com.ruci.param.ItemParam;
import com.ruci.param.LoginParam;
import com.ruci.param.PageInfo;
import com.ruci.service.EventService;
import com.ruci.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/shopping")
public class ItemController {

    @Autowired
    private ItemService itemService;
    @Autowired
    private EventService eventService;

    @RequestMapping("/list/{pageNum}")
    //商品页
    public String home(Model model,@PathVariable("pageNum")long pageNum){
        PageInfo<Item> info =itemService.list(pageNum);
        model.addAttribute("items",info);
        return "shopping/goods";
    }

    @RequestMapping("/itemName/{pageNum}")
    //根据名称查询
    public String homeByName(String name,@PathVariable("pageNum")long pageNum,Model model){
        PageInfo<Item> info =itemService.selectByName(pageNum,name);
        model.addAttribute("name",name);
        model.addAttribute("items",info);
        return "shopping/goods";
    }


    @RequestMapping("/item/{id}")
    //商品详情页
    public String item(Model model,@PathVariable("id")int id){




        EventParam eventParam=eventService.selectById(id);
        model.addAttribute("item",eventParam);

        long startAt = eventParam.getStartDate().getTime();
        long endAt = eventParam.getEndDate().getTime();
        long now = System.currentTimeMillis();

        int miaoshaStatus = 0;
        int remainSeconds = 0;
        if(now < startAt ) {//秒杀还没开始，倒计时
            miaoshaStatus = 0;
            remainSeconds = (int)((startAt - now )/1000);
        }else  if(now > endAt){//秒杀已经结束
            miaoshaStatus = 2;
            remainSeconds = -1;
        }else {//秒杀进行中
            miaoshaStatus = 1;
            remainSeconds = 0;
        }
        model.addAttribute("miaoshaStatus", miaoshaStatus);
        model.addAttribute("remainSeconds", remainSeconds);

        return "shopping/item";
    }


    @RequestMapping("/listById/{pageNum}")
    //根据id获取个人商品
    public String listById(Model model,@PathVariable("pageNum")long pageNum){
        PageInfo<Item> itemPageInfo=itemService.listByName(pageNum);

        model.addAttribute("items",itemPageInfo);

        return "item/itemlist";
    }

    @RequestMapping("/sava")
    public String sava(Model model,ItemParam param,@RequestParam("file") MultipartFile file){
        model.addAttribute("item",param);

        return null;
    }



}
