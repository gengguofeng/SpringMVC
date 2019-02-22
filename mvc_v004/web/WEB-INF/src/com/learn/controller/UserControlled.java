package com.learn.controller;

import com.learn.core.annatation.Autowired;
import com.learn.core.annatation.Controllerd;
import com.learn.core.annatation.RequestMapped;
import com.learn.core.bean.Model;
import com.learn.dao.User;
import com.learn.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controllerd("/user")
public class UserControlled {

    @Autowired
    UserService userService ;
    String aa = "222";



    @RequestMapped("/list")
    public void getlist(HttpServletRequest req, HttpServletResponse resp){

//        String re_url = req.getRequestURI();
//        Enumeration<String> paramnames =  req.getParameterNames();
//        while (paramnames.hasMoreElements()){
//            String paramname = paramnames.nextElement();
//            String paramvalue = req.getParameter(paramname);
//            System.out.println(paramname+":"+paramvalue);
//        }
//
//        System.out.println("url:"+re_url);
        System.out.println("##############################");
    }


    @RequestMapped("/run")
    public void getUser(String name,String age,HttpServletRequest req, HttpServletResponse resp,User user){

        System.out.println("name : " + name);
        System.out.println("age : "+age);
        System.out.println("request name and age : "+req.getParameter("name")+" "+req.getParameter("age"));
        System.out.println("response : "+ resp);

        System.out.println("dao: "+user.getName()+"  "+user.getAge());
        userService.run();
    }

    @RequestMapped("/run1")
    public String  getUser1(User user, Model model){

        model.setAttribute("message","hello!!");
        model.setAttribute("name","gengguofeng");
        return "info";
    }

}
