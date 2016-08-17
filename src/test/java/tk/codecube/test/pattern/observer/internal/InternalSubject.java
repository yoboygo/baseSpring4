package tk.codecube.test.pattern.observer.internal;

import java.util.Observable;

public class InternalSubject extends Observable {

	private String time;

	public void setTime(String time) {
		this.time = time;
		change();
	}

	public String getTime() {
		return time;
	}

	public void change() {
		setChanged();
		notifyObservers();
	}
}
