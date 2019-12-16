package com.glaway.ids.functionManage.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
     List<String> tempList=new ArrayList<String>();
     tempList.add("10061");
     tempList.add("10062");
     tempList.add("10063");
     tempList.add("10064");
     tempList.add("10065");
     tempList.add("10061");
		/* for (String string : tempList) {
			System.out.println(string);
		}*/
		Set<String> tempList2=new HashSet<String>();
		tempList2.add("10061");
		tempList2.add("10062");
		tempList2.add("10063");
		tempList2.add("10064");
		tempList2.add("10065");
		tempList2.add("10061");
		System.out.println(tempList2);
		Iterator it=tempList2.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
	}

}
