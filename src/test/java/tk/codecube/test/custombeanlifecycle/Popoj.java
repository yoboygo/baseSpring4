/**
 * Popoj.java
 * 
 * Aimy
 * 下午2:32:54
 */
package tk.codecube.test.custombeanlifecycle;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * @author Aimy 2014年9月11日 下午2:32:54
 */
public class Popoj {

	private String brand;
	private String color;
	private int maxSpeed;

	private String IDCode;

	public String getIDCode() {
		return IDCode;
	}

	public void setIDCode(String iDCode) {
		this.IDCode = iDCode;
	}

	public String getBrand() {
		System.out.println("Popoj.getBrand()");
		return brand;
	}

	public void setBrand(String brand) {
		System.out.println("Popoj.setBrand()");
		this.brand = brand;
	}

	public String getColor() {
		System.out.println("Popoj.getColor()");
		return color;
	}

	public void setColor(String color) {
		System.out.println("Popoj.setColor()");
		this.color = color;
	}

	public int getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
