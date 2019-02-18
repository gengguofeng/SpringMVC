package com.learn.controller;


import com.learn.annatation.Autowired;
import com.learn.annatation.Controllerd;
import com.learn.annatation.RequestMapped;
import com.learn.service.DogService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controllerd
public class DogControlled {

    @Autowired
    DogService dogService;

    @RequestMapped("/cry")
    public void say(HttpServletRequest req, HttpServletResponse resp){
        dogService.run();
    }

}
