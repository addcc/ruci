package com.ruci.controller;

import com.ruci.common.JsonData;
import com.ruci.common.LoginSession;
import com.ruci.domain.RiUser;
import com.ruci.param.PageInfo;
import com.ruci.param.UserParam;
import com.ruci.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/system/user")
@CrossOrigin //处理ajax跨区错误的问题
public class UserController {

    @Autowired
    private UserService userService;

//    private static final String CONTENT_TYPE_FORMEN="application/x-www-form-urlencoded";


    @RequestMapping(value = "/userlist/{pageNum}")
    //返回所有用户列表
    public String pageList(Model model,@PathVariable("pageNum")Integer pageNum){

        PageInfo<RiUser> pageInfo =userService.selectPage(pageNum);

        model.addAttribute("users",pageInfo);

        return "system/userlist";
    }


    @RequestMapping("/user/{id}")
    //用户修改页
    public String user(Model model,@PathVariable("id")Integer id){
        UserParam userParam=userService.selectById(id);
        model.addAttribute("user",userParam);
        return "system/userupdate";
    }

    @RequestMapping("/set")
    //主页的用户修改页
    public String setuser(Model model){
        Integer id=LoginSession.getLoginId();

        UserParam userParam=userService.selectById(id);
        model.addAttribute("user",userParam);
        return "system/setuser";
    }


    @RequestMapping("/get/{id}")
    //用户列表的用户详情页
    public String get(Model model,@PathVariable("id")Integer id){
        UserParam userParam=userService.selectById(id);
        model.addAttribute("user",userParam);
        return "system/user";
    }

    @RequestMapping("/getuser")
    //左侧菜单栏的用户详情页
    public String getUser(Model model){
        Integer id= LoginSession.getLoginId();
        UserParam userParam=userService.selectById(id);
        model.addAttribute("user",userParam);
        return "system/getuser";
    }


    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    //更新用户
    public JsonData update(@RequestParam("name")String name, @RequestParam("email")String email,
                           @RequestParam("phone")String phone, @RequestParam("age")Integer age,
                           @RequestParam("gender")Integer gender,@RequestParam("id")Integer id,
                           @RequestParam("userName")String userName,@RequestParam("password")String password){

        UserParam user=new UserParam();
        user.setId(id);
        user.setName(name);
        user.setPhone(phone);
        user.setEmail(email);
        user.setGender(gender);
        user.setAge(age);
        user.setUserName(userName);
        user.setPassword(password);

        userService.update(user);

        return JsonData.success();
    }


    @RequestMapping("/delete/{id}")
    //删除用户
    public String delete(@PathVariable("id")Integer id){
        userService.delete(id);

        return "redirect:/system/user/userlist/1";
    }


}
