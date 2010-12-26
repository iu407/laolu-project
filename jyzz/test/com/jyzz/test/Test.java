package com.jyzz.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] arr = new String[]{"a","b","a","b","c"};
		
		
		List<String> sList = new ArrayList<String>();
		
		for(int i = 0 ; i < arr.length ; i ++)
		{
			sList.add(arr[i]);
		}
		Collections.sort(sList);
		
		System.out.println(sList);
		
	}
	
	class sMap{
		String alpha ;
		String num;
		
		
		
		public void add(String str){
			
		}
	}


}

