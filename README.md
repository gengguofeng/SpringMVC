# SpringMVC

mvc_v001:实现注解形式请求转发

mvc_v002:实现service层依赖注入

mvc_v003:优化页面参数传递,例如：

   1，@RequestMapped("/run")
     public void getUser(User user){...}
     
   2，@RequestMapped("/run")
     public void getUser(String name,String age){...}
     
   3,@RequestMapped("/run")
     public void getUser(String name,String age,HttpServletRequest req){...}
    
   4,@RequestMapped("/run")
     public void getUser(User user,HttpServletRequest req){...}
    
   等等
 
