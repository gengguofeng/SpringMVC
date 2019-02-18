package com.learn.controller;

import com.learn.annatation.Autowired;
import com.learn.annatation.Controllerd;
import com.learn.annatation.RequestMapped;
import com.learn.dao.User;
import com.learn.service.UserService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

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
    public void getUser1(User user){

        System.out.println("dao: "+user.getName()+"  "+user.getAge());
        userService.run();
    }

}
