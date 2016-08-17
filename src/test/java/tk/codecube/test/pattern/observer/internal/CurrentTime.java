package tk.codecube.test.pattern.observer.internal;

import java.util.Observable;
import java.util.Observer;

public class CurrentTime implements Observer {

	private String time;
	public CurrentTime(Observable ob) {
		ob.addObserver(this);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		
		if(o instanceof InternalSubject)
		{
			InternalSubject sub = (InternalSubject)o;
			this.time =  sub.getTime();
		}
		display();
	}

	public void display()
	{
		System.out.println("Current Time is "+time);
	}
}
