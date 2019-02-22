package com.learn.test;

public class method_test {

    public static void main(String[] args){


        String pageUrl = getPageUrl("/user/list");
        System.out.println(pageUrl);

    }


    public static String getPageUrl(String path){
        if(!path.startsWith("/"))
            path = "/"+path ;
        path = path + ".jsp" ;
        return path ;
    }


}
