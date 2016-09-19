package tk.codecube.test.thread;

import java.util.concurrent.ConcurrentLinkedQueue;

public class PrimeGenerator extends Thread {

	private long number = 17l;
	private ConcurrentLinkedQueue<Integer> ori;
	
	public PrimeGenerator() {
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public void run() {
		while (true) {
			System.out.println("================current number is "+number+"=================");
			if (isPrime(number)) {
				System.out.printf("Number %d is Prime", number);
			}
			if (isInterrupted()) {
				System.out.println("The PrimeGenerator is interrupted!");
				return;
			}
			++number;
		}
	}

	/**
	 * 判断是不是质数
	 * 
	 * @param no
	 * @return
	 */
	private boolean isPrime(long no) {

		if (no < 2)
			return true;

		for (int i = 2; i < Math.sqrt(no); ++i) {
			if (no % i == 0)
				return false;
		}
		return true;
	}

}
