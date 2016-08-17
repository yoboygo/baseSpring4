package tk.codecube.test.pattern.observer;

public class CurrentSubject extends Subject {

	private String time;

	@Override
	public void nofityObserver() {
		for (Observer e : observerList) {
			e.update(this.time);
		}
	}

	public void setTime(String time) {
		this.time = time;
		change();
	}

	@Override
	public void change() {
		nofityObserver();
	}

}
