/**
 * CarImp2.java
 * 
 * Aimy
 * 上午8:53:14
 */
package tk.codecube.test.springautowried;


/**
 * @author Aimy
 * 2014年11月24日 上午8:53:14
 */
public class CarImp2 implements ICar{

	protected String color = "2";
	/* (non-Javadoc)
	 * @see tk.codecube.test.springautowried.ICar#setColor(java.lang.String)
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
