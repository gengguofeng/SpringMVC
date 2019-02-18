package com.learn.controller;


import com.learn.annatation.Controllerd;
import com.learn.annatation.RequestMapped;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controllerd("/game")
public class GameControlled {
    @RequestMapped("/say")
    public void say(HttpServletRequest req, HttpServletResponse resp){
        System.out.println("GameControlled:1111");
    }

}
