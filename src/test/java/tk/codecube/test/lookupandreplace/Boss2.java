/**
 * Boss2.java
 * 
 * Aimy
 * 上午9:51:28
 */
package tk.codecube.test.lookupandreplace;

import java.lang.reflect.Method;

import org.springframework.beans.factory.support.MethodReplacer;

import tk.codecube.test.custombeanlifecycle.Car;

/**
 * @author Aimy
 * 2014年9月19日 上午9:51:28
 */
public class Boss2 implements MethodReplacer{

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.support.MethodReplacer#reimplement(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
	 */
	@Override
	public Object reimplement(Object obj, Method method, Object[] args)
			throws Throwable {
		Car car = new Car();
		car.setBrand("Replaced奥迪");
		return car;
	}

}
