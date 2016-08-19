package tk.codecube.test.pattern.factory.command;

public class CeilingFan {

	public static final int HIGH = 3;
	public static final int MEDIUM = 2;
	public static final int LOW = 1;
	public static final int OFF = 0;

	private String location;
	private int speed;

	public CeilingFan(String location) {
		this.location = location;
		speed = OFF;
	}

	public void on() {
		speed = MEDIUM;
		System.out.println("CeilingFan On speed=" + getSpeed());
	}

	public void off() {
		speed = OFF;
		System.out.println("CeilingFan Off!");
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {

		this.speed = speed;
		System.out.println("Set Speed " + getSpeed());
	}

	public static int getHigh() {
		return HIGH;
	}

	public static int getMedium() {
		return MEDIUM;
	}

	public static int getLow() {
		return LOW;
	}

	public static int getOff() {
		return OFF;
	}

}
