/**
 * CarImp1.java
 * 
 * Aimy
 * 上午8:51:45
 */
package tk.codecube.test.springautowried;

import org.springframework.stereotype.Component;

/**
 * @author Aimy
 * 2014年11月24日 上午8:51:45
 */
@Component("carImp1")
public class CarImp1 implements ICar{

	protected String color = "1";
	/* (non-Javadoc)
	 * @see tk.codecube.test.springautowried.ICar#setColor()
	 */
	@Override
	public void setColor(String color) {
		// TODO Auto-generated method stub
		this.color = color;
	}

	/* (non-Javadoc)
	 * @see tk.codecube.test.springautowried.ICar#getColor()
	 */
	@Override
	public String getColor() {
		// TODO Auto-generated method stub
		return this.color;
	}

}
