package com.example.demo.controller;

//import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Author: Eden
 * @Date: 2019/4/17 17:01
 * @Version 1.0
 */
@Slf4j
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/ulist")
    public String findAll(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "3") Integer ps, Model model){
        PageInfo<User> pageInfo = userService.findAll(page, ps);
        model.addAttribute("page",pageInfo);
        model.addAttribute("ulist",pageInfo.getList());
//        pageInfo.getList().forEach(user -> System.out.println(user));
        return "userList";
    }

    @RequestMapping("/tologin")
    public String tologin(){
        return "login";
    }

    @RequestMapping(value = "/login")
    public String login(User users, HttpSession session, Model model) {
        System.out.println("是否自动装配对象========"+users.toString());
        users = userService.login(users.getLogname(),users.getPassword());
        if (!Objects.isNull(users)) {
            session.setAttribute("users", users);
            model.addAttribute("user",users);
            return "index";
        } else {
            session.setAttribute("errormsg", "账号或密码错误!请重新输入");
        }
        return "login";
    }

    @RequestMapping("/toadd")
    public String toadd(){
        return "register";
    }

    @RequestMapping(value = "/addone")
    public String addOne(User user,Model model){
        int i = userService.addOne(user);
        if (i < 0){
            model.addAttribute("errormsg","添加失败！请核对信息");
            return "register";
        }
        return "redirect:/ulist";
    }

    @RequestMapping(value = "/delete/{uid}",method = RequestMethod.POST)
    @ResponseBody
    public Object deleteOne(@PathVariable("uid") int uid){
        Map map=new HashMap();
       if(userService.deleteOne(uid)){
           map.put("msg",true);
       }else {
           map.put("msg",false);
       }
       return JSONArray.toJSON(map);
    }

    @RequestMapping("/findlike")
    public String findLike(@RequestParam String username,Model model){
        List<User> users = userService.findLike(username);
        model.addAttribute("somes",users);
        return "findlike";
    }

    @RequestMapping(value = "/modifyone", method = {RequestMethod.POST})
    @ResponseBody
    public Object modifyOne(User user){
        log.info("_____对象_____"+user.toString());
        Map<String,Integer> map = new HashMap<>();
        int i = 0;
        if (userService.modifyOne(user)){
            i = 1;
            map.put("result",i);
            System.out.println(map.get("result"));
        }
            map.put("result",i);
            return JSONArray.toJSON(map);

    }
}
