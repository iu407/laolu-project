package com.jyzz.test;

public class SingletonPerson {
	private SingletonPerson sp ;
	
	
	private SingletonPerson(){
		
	}
	
	public SingletonPerson newInstance(){
		if(sp==null){
			sp =  new SingletonPerson();
		}
		return sp;
	}

}
