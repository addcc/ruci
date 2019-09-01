package com.ruci.controller;

import com.ruci.domain.OrderInif;
import com.ruci.param.OrderParam;
import com.ruci.param.PageInfo;
import com.ruci.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;




    @RequestMapping("/sava/{tid}")
    //创建商品订单
    public String savaItem(Model model,@PathVariable("tid")Integer tid){

//        //获取当前用户信息
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//
//        RiLogin login= (RiLogin) request.getSession().getAttribute("login");

        OrderParam order=orderService.sava(tid);

        model.addAttribute("order",order);

        return "shopping/order";
    }

    //返回当前用户的订单集合
    @RequestMapping("list/{pageNum}")
    public String list(Model model,@PathVariable("pageNum")long pageNum){

        PageInfo<OrderInif> orderlist= orderService.list(pageNum);
        model.addAttribute("orders",orderlist);
        return "shopping/orderlist";
    }

    @RequestMapping("/get/{oid}")
    //订单详情
    public String getOrder(Model model,@PathVariable("oid")Integer oid){
        OrderParam order=orderService.getOrder(oid);

        model.addAttribute("order",order);

        return "shopping/order";
    }

    @RequestMapping("/orderlist/{pageNum}")
    //商家订单
    public String orderList(Model model,@PathVariable("pageNum")long pageNum){
        PageInfo<OrderInif> orderlist= orderService.lists(pageNum);
        model.addAttribute("orders",orderlist);
        return "item/orderlist";
    }


}
