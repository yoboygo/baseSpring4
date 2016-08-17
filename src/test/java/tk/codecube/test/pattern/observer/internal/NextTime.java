package tk.codecube.test.pattern.observer.internal;

import java.util.Observable;
import java.util.Observer;

public class NextTime implements Observer {

	private String time;

	public NextTime(Observable ob) {
		ob.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof InternalSubject) {
			InternalSubject isub = (InternalSubject) o;
			this.time = isub.getTime();
			display();
		}

	}

	public void display() {
		System.out.println("Next Time is " + time);
	}
}
