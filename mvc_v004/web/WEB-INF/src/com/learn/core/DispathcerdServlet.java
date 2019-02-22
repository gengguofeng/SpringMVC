package com.learn.core;


import com.learn.core.bean.Model;
import com.learn.core.handler.IOChandler;
import com.learn.core.handler.RequestMappingHandler;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class DispathcerdServlet extends HttpServlet {


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

        Model model = new Model();

        RequestMappingHandler requestMappingHandler = new RequestMappingHandler(req,resp,model);
        String forward_path = requestMappingHandler.reauestMapping();


        Iterator<Map.Entry<String, Object>> iterator = model.getEntrySet();
        while (iterator.hasNext()){
            Map.Entry<String, Object> entry = iterator.next();
            String key = entry.getKey();
            String value = (String)entry.getValue();
            req.setAttribute(key,value);
        }

        if(forward_path!=null)
             req.getRequestDispatcher(getPageUrl(forward_path)).forward(req,resp);

    }

    public  String getPageUrl(String path){
        if(!path.startsWith("/"))
            path = "/"+path ;
        path = path + ".jsp" ;
        return path ;
    }

}
