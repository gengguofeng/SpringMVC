package com.learn.service;

import com.learn.annatation.Controllerd;
import com.learn.annatation.RequestMapped;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controllerd
public class UserService {

    @RequestMapped("/usersay")
    public void say(HttpServletRequest req, HttpServletResponse resp){
        System.out.println("usersayusersayusersayusersayusersayusersay");
    }


}
