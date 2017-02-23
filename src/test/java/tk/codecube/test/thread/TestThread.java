package tk.codecube.test.thread;

import org.junit.Test;

public class TestThread {

	/**
	 * 测试多线程的质数选择器
	 * @throws InterruptedException 
	 */
	@Test
	public void testPrimeGenerator() throws InterruptedException{
		PrimeGenerator pg = new PrimeGenerator();
		pg.start();
		Thread.sleep(5000);
		pg.interrupt();
	}
}
