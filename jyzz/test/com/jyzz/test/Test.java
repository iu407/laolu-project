package com.jyzz.test;

import java.util.HashSet;
import java.util.Set;


public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		String[] arr = new String[]{"a","b","a","b","c"};
//		
//		
//		List<String> sList = new ArrayList<String>();
//		
//		for(int i = 0 ; i < arr.length ; i ++)
//		{
//			sList.add(arr[i]);
//		}
//		Collections.sort(sList);
//		
//		System.out.println(sList);
//		String dstr = "";
//		File path = new File(dstr);
		Person p1 = new Person("zhnag",1,1);//2个对象
		Person p2 = new Person("wang",2,1);//2个对象
		System.out.println("p1.equals(p2) " + p1.equals(p2));//true
		System.out.println("p1==p2        " + (p1 == p2));//是否可以理解为hashcode，不能这样理解
		
		System.out.println(p1.hashCode());
		System.out.println(p2.hashCode());//这两个对象有相同的id，但是hashcode却不相同
		
		Set<Person> set = new HashSet<Person>();//在set中就能看出hashcode的用处了
		set.add(p1);
		set.add(p2);
		
		System.out.println(set.size());//这两个对象并不相同。系统要打到什么目的，如果认为id相同的对象，就是同一个对象。那我们我们需要重写hashcode方法
		
		
	}
	
	class sMap{
		String alpha ;
		String num;
		
		
		
		public void add(String str){
			
		}
	}


}

