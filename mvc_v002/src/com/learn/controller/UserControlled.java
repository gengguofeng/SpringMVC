package com.learn.controller;

import com.learn.annatation.Autowired;
import com.learn.annatation.Controllerd;
import com.learn.annatation.RequestMapped;
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

        String re_url = req.getRequestURI();
        Enumeration<String> paramnames =  req.getParameterNames();
        while (paramnames.hasMoreElements()){
            String paramname = paramnames.nextElement();
            String paramvalue = req.getParameter(paramname);
            System.out.println(paramname+":"+paramvalue);
        }

        System.out.println("url:"+re_url);
        System.out.println("##############################");
    }


    @RequestMapped("/run")
    public void getUser(HttpServletRequest req, HttpServletResponse resp){

        userService.run();
    }


}
