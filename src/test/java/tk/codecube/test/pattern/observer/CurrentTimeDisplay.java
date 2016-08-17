package tk.codecube.test.pattern.observer;

public class CurrentTimeDisplay implements Observer,Display{

	private String time;
	public CurrentTimeDisplay(Subject sub) {
		sub.registObserver(this);
	}
	@Override
	public void update(String time) {
		this.time = time;
		display();
	}
	@Override
	public void display() {
		System.out.println("Current time is "+time);
	}

}
