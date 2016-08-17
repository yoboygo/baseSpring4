package tk.codecube.test.pattern.observer;

public class NextSubDisplay implements Observer,Display{

	private String time;
	
	/**
	 * @param sub 订阅的主题，用于注册观察者
	 */
	public NextSubDisplay(Subject sub) {
		sub.registObserver(this);
	}
	
	@Override
	public void display() {
		System.out.println("NextTime is "+time);
	}

	@Override
	public void update(String time) {
		this.time = time;
		display();
	}

}
