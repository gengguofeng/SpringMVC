package com.learn.core.handler;

import com.learn.core.annatation.Controllerd;
import com.learn.core.annatation.RequestMapped;
import com.learn.core.bean.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;

public class RequestMappingHandler {

    private  Model model ;
    private HttpServletRequest req ;
    private HttpServletResponse resp ;

    public RequestMappingHandler(HttpServletRequest req, HttpServletResponse resp,Model model){
        this.model = model ;
        this.req = req ;
        this.resp = resp ;
    }

    //根据请求url，从IOC容器中取出对应的controll，并调用url对应的方法
    public  String   reauestMapping() throws Exception {

        String path = req.getRequestURI();

        HashMap<String,Object> IOC = (HashMap<String,Object>)req.getServletContext().getAttribute("IOC");
        if (IOC.get(path)==null)
            return null ;

        Object iocobj = IOC.get(path);
        Class iocclazz = iocobj.getClass();

        //获取类映射@Controllerd("/user")
        Controllerd controllerd = iocobj.getClass().getAnnotation(Controllerd.class) ;
        String parent_path = controllerd.value(); //  /user

        //获取各个方法映射@RequestMapped("/list"),如果方法映射与path相同，调用该方法
        Method[] methods = iocclazz.getDeclaredMethods();
        for (Method method:methods) {

            if(method.isAnnotationPresent(RequestMapped.class)){
                String child_path = method.getAnnotation(RequestMapped.class).value();
                String IOC_key = parent_path + child_path ;

                //如果方法映射与path相同，调用该方法
                if(path.equals(IOC_key)){

                    //根据controll方法参数，传递相应参数
                    Object[]paramsObj = getMappingMethodParams(method);

                    String forward_path = (String)method.invoke(iocobj,paramsObj) ;
                    return forward_path ;

                }
            }
        }

        return null ;

    }


    //根据controll方法参数，传递相应参数
    private  Object[] getMappingMethodParams(Method method) throws Exception{

        //controll方法的参数
        Parameter[] parameters = method.getParameters();

        //传入的参数
        Object[] paramsObj = new Object[parameters.length];

        //获取controll方法的参数，并将传入的参数实例化
        for(int i=0 ; i<parameters.length;i++){
            String param_type = parameters[i].getType().getName();
            String param_name = parameters[i].getName();

            switch (param_type){
                case "java.lang.String":
                    paramsObj[i] = req.getParameter(param_name);
                    break;
                case "javax.servlet.http.HttpServletRequest":
                    paramsObj[i] = req ;
                    break;
                case "javax.servlet.http.HttpServletResponse" :
                    paramsObj[i] = resp ;
                    break;

                case "com.learn.core.bean.Model" :
                    paramsObj[i] = model ;
                    break;

                //将页面传入的参数封装成对象
                default:
                    Class paramClazz = Class.forName(param_type);
                    Object paramObject = paramClazz.newInstance();

                    Field[] fields = paramClazz.getDeclaredFields();
                    for (Field field:fields) {
                        field.setAccessible(true);
                        field.set(paramObject,req.getParameter(field.getName()));
                    }

                    paramsObj[i] = paramObject ;

            }
        }

        return paramsObj ;

    }

}
