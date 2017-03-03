package tk.codecube.test;

import tk.codecube.test.aop.ForumServiceImpl;

public class SubClass extends ForumServiceImpl {

	public void invokGetClassInSubClass(){
		System.out.println(this.getClass().getName());
	}
}
