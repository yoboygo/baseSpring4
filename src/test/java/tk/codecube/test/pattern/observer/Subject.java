package tk.codecube.test.pattern.observer;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {

	List<Observer> observerList = new ArrayList<Observer>();

	/**
	 * @Description 注册观察者
	 * @author jianlong.song bpqqop@163.com 
	 * @date 2016年8月16日 下午2:33:43
	 * @param e
	 */
	public void registObserver(Observer e) {
		observerList.add(e);
	}

	/**
	 * @Description 移除观察者
	 * @author jianlong.song bpqqop@163.com 
	 * @date 2016年8月16日 下午2:33:58
	 * @param e
	 */
	public void removeObserver(Observer e) {
		observerList.remove(e);
	}

	/**
	 * @Description 内容改变的时候
	 * @author jianlong.song bpqqop@163.com 
	 * @date 2016年8月16日 下午2:34:07
	 */
	public abstract void change();
	
	/**
	 * @Description 通知观察者
	 * @author jianlong.song bpqqop@163.com 
	 * @date 2016年8月16日 下午2:34:20
	 */
	public abstract void nofityObserver();

}
