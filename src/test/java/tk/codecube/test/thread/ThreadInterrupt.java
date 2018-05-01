package tk.codecube.test.thread;

import org.apache.log4j.chainsaw.Main;

/**
 * 
 * @author bpqqo
 *
 */
public class ThreadInterrupt {
	public static void main(String[] args) {
		try {
			//测试终止线程
			
			/*
			MyThread mt = new MyThread();
			mt.start();
			Thread.sleep(2000);
			mt.interrupt();
			*/
			
			
			//测试终止sleep的线程
			/*
			SleepThread st = new SleepThread();
			st.start();
			Thread.sleep(2000);
			st.interrupt();
			*/
			//先停止线程再遇到Sleep
			InterruptBeforeSleep ibs = new InterruptBeforeSleep();
			ibs.start();
			ibs.interrupt();
			System.out.println("Main end");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Main --> end");
	}
}

class MyThread extends Thread{
	
	@Override
	public void run() {
		super.run();
		for (int i = 0; i < 500000; i++){
			if (this.interrupted()){
				System.out.println("已经是停止状态了，准备退出.");
				break;
			}
			System.out.println("i=" + (i + 1));
		}
		System.out.println("线程暂停后依然执行的语句");
	}
}

class SleepThread extends Thread{
	@Override
	public void run() {
		super.run();
		System.out.println("run start");
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			System.out.println("this.isInterrupted()--> " + this.isInterrupted());
			e.printStackTrace();
		}
		System.out.println("run end");
	}
}

class InterruptBeforeSleep extends Thread{
	@Override
	public void run() {
		super.run();
		System.out.println(" run start");
		for (int i = 0; i < 100000; ++i){
			System.out.println(" i = " + i);
		}
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			System.out.println("先停止线程再遇到Interrupt");
			e.printStackTrace();
		}
		System.out.println(" run end");
		
	}
}