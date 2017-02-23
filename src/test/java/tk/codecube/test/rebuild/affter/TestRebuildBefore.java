package tk.codecube.test.rebuild.affter;

import java.util.Calendar;

import org.junit.Test;

public class TestRebuildBefore {

	/**
	 * 租借测试
	 */
	@Test
	public void testRentail() {

		Movie m1 = new Movie();
		m1.setTitle("爱宠大机密");
		m1.setPrice(new ChildrenMoviePrice(18));

		Movie m2 = new Movie("美国队长：3",MovieType.NEW,25.0);
		Movie m3 = new Movie("八大恶人",MovieType.REGULAR,15.0);

		Customer c1 = new Customer("李雷");
		Customer c2 = new Customer("韩梅梅");

		Calendar start = Calendar.getInstance();
		Calendar end = Calendar.getInstance();
		end.add(Calendar.DAY_OF_YEAR, 7);

		Rentail rt1 = Rentail.builder().buildMovie(m1).buildStartDay(start)
				.buildEndDay(end);
		Rentail rt2 = Rentail.builder().buildMovie(m2).buildStartDay(start)
				.buildEndDay(end);
		Rentail rt3 = Rentail.builder().buildMovie(m3).buildStartDay(start)
				.buildEndDay(end);
		
		c1.addRentail(rt1);
		c1.addRentail(rt2);
		c1.addRentail(rt3);
		
		c2.addRentail(rt2);

		c1.printOrderDetail();
		
		//改变电影的类型时计算结果将会改变
		m2.setType(MovieType.REGULAR);
		c1.printOrderDetail();
		
		c2.printOrderDetail();

	}

}
