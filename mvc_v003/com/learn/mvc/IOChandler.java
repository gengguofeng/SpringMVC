package com.learn.mvc;

import com.learn.annatation.Autowired;
import com.learn.annatation.Controllerd;
import com.learn.annatation.RequestMapped;
import com.learn.annatation.Serviced;

import javax.servlet.ServletConfig;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;


public class IOChandler  {

    private ServletConfig config ;

    public  IOChandler (ServletConfig config)  {
        this.config = config ;
        HashMap<String,Object> IOCmap = new HashMap<String,Object>();
        config.getServletContext().setAttribute("IOC",IOCmap);

    }


    //初始化IOC容器
    public void init(String packets) throws Exception {

        //根据逗号分出包，遍历包中的类，找出@Controllerd标记的类，并注入IOC容器中
        String[] packetArray =  packets.split(",");


        //IOC初始化service
        for (String packetpath:packetArray) {
            String filePath = config.getServletContext().getRealPath("/WEB-INF/src")+"/"+packetpath.replace(".","/");
            File dir  = new File(filePath);
            if(dir.exists())
                service_packet(packetpath, dir);
        }


        //IOC初始化controller
        for (String packetpath:packetArray) {
            String filePath = config.getServletContext().getRealPath("/WEB-INF/src")+"/"+packetpath.replace(".","/");
            File dir  = new File(filePath);
            if(dir.exists())
                controller_packet(packetpath, dir);
        }

    }

    //IOC初始化service
    public   void service_packet(String packetpath,File dir) throws Exception{

        HashMap<String,Object> IOC = (HashMap<String,Object>)config.getServletContext().getAttribute("IOC");

        for (File file:dir.listFiles()) {

            String classpath = packetpath + "." + file.getName().split("\\.")[0];

            Class clazz = Class.forName(classpath);
            Object obj = clazz.newInstance();

            //如果是@Serviced标记的类
            if(clazz.isAnnotationPresent(Serviced.class)){

                //获取类名
                String class_name = clazz.getSimpleName();

                //将 类名与类（"UserService":UserService.class）注入IOC
                IOC.put(class_name,obj);

            }

        }
    }

    //IOC初始化controller
    public  void controller_packet(String packetpath,File dir) throws Exception{

        HashMap<String,Object> IOC = (HashMap<String,Object>)config.getServletContext().getAttribute("IOC");

        //遍历包中的类，找出@Controllerd标记的类，并注入IOC容器中
        for (File file:dir.listFiles()) {

            String classpath = packetpath + "." + file.getName().split("\\.")[0];

            Class clazz = Class.forName(classpath) ;
            Object obj = clazz.newInstance();

            //如果是@Controllerd标记的类
            if(clazz.isAnnotationPresent(Controllerd.class)) {

                //获取被@Autowired标记的成员变量
                Field[] fields = clazz.getDeclaredFields();

                for (Field field:fields) {

                    if(field.isAnnotationPresent(Autowired.class)){

                        String IOC_key = field.getType().getSimpleName() ;
                        field.setAccessible(true);
                        field.set(obj,IOC.get(IOC_key));
                    }
                }


                //获取类映射@Controllerd("/user")
                Controllerd controllerd = obj.getClass().getAnnotation(Controllerd.class);
                String parent_path = controllerd.value(); //  /user

                //获取各个方法映射@RequestMapped("/list")
                Method[] methods = clazz.getDeclaredMethods();
                for (Method method : methods) {

                    //如果加了@RequestMapped注解，获取方法映射@RequestMapped("/list")
                    //将 /user/list 与 类 注入IOC
                    if (method.isAnnotationPresent(RequestMapped.class)) {
                        String child_path = method.getAnnotation(RequestMapped.class).value();
                        String IOC_key = parent_path + child_path;
                        System.out.println("   insert "+IOC_key+"  "+obj);
                        IOC.put(IOC_key, obj);
                    }

                }
            }

        }
    }


}
