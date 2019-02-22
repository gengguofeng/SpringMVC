package com.learn.controller;


import com.learn.core.annatation.Autowired;
import com.learn.core.annatation.Controllerd;
import com.learn.core.annatation.RequestMapped;
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
