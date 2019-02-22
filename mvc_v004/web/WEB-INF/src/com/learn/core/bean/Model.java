package com.learn.core.bean;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Model {

    HashMap<String,Object> map = new HashMap<String,Object>();

    public void setAttribute(String key,Object value){
        map.put(key,value);
    }

    public Object getAttribute(String key){
        return map.get(key);
    }

    public Iterator<Map.Entry<String, Object>>  getEntrySet(){
        return map.entrySet().iterator();
    }

    public boolean isEmpty(){
        return map.isEmpty() ;
    }
}
