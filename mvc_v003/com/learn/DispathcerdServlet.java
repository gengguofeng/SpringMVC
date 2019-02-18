package com.learn;


import com.learn.mvc.IOChandler;
import com.learn.mvc.RequestMappingHandler;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DispathcerdServlet extends HttpServlet {


//    public   HashMap<String,Object> IOC = new HashMap<String,Object>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        try {

            super.init(config);
            IOChandler ioChandler = new IOChandler(config);
            ioChandler.init("com.learn.controller,com.learn.service");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            dispacherd(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    private void dispacherd(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        RequestMappingHandler.reauestMapping(req,resp);
    }



}
