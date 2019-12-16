package com.glaway.ids.functionManage.controller;

import java.util.ArrayList;
import java.util.Iterator;

public class Iteratortest {
	
	
	
    public static void main(String[] args)  {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(10);
        Iterator<Integer> iterator = list.iterator();
        while(iterator.hasNext()){
            Integer integer = iterator.next();
            if(integer==10)
                list.remove(integer);   //注意这个地方
        }
    }
    
    
    
}
