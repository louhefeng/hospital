package com.hospi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hospi.model.User;
import com.hospi.model.UserEntity;
import com.hospi.server.UserService;

@Controller
@RequestMapping("/test")//Contoller下所有接口统一入口
public class IndexController {

	
	@Autowired
	private UserService userService;
	
	//映射一个action
    @RequestMapping("/user")
    @ResponseBody//表示直接输出返回内容，不进行jsp或html跳转，本例是为了写接口，这里直接返回json
    public UserEntity getUser() {
    //创建一个UserEntity，直接返回，之前在web.xml中配置的jackson会将user对象转为json输出
        UserEntity user = new UserEntity("jack", "123456");
        return user;
    }
    
    
    
    @RequestMapping("/saveUser")
    @ResponseBody
    public String savePerson(User user){
    	long count=userService.saveUser(user);
    	System.out.println(count);
        return "success";
    }
    
    
    
    @RequestMapping("/search")
    public String search(String  name,String phone,Model model){	
    	List<User> users = userService.search(name, phone);
    	 model.addAttribute("users",users);
        return "search";
    }
    
}
