package tk.codecube.test.pattern;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import org.junit.Test;

import tk.codecube.test.pattern.component.Menu;
import tk.codecube.test.pattern.component.MenuComponent;
import tk.codecube.test.pattern.component.MenuItem;
import tk.codecube.test.pattern.component.Waitress;
import tk.codecube.test.pattern.decorator.Beverage;
import tk.codecube.test.pattern.decorator.Espresso;
import tk.codecube.test.pattern.decorator.HouseBlend;
import tk.codecube.test.pattern.decorator.LowerCaseInputStream;
import tk.codecube.test.pattern.decorator.Milk;
import tk.codecube.test.pattern.decorator.Mocha;
import tk.codecube.test.pattern.decorator.Whip;
import tk.codecube.test.pattern.factory.ChiocagoPizzaStore;
import tk.codecube.test.pattern.factory.NYPizzaStore;
import tk.codecube.test.pattern.factory.Pizza;
import tk.codecube.test.pattern.factory.command.CeilingFan;
import tk.codecube.test.pattern.factory.command.CeilingFanCommand;
import tk.codecube.test.pattern.factory.command.Door;
import tk.codecube.test.pattern.factory.command.GarageDoorOpenCommand;
import tk.codecube.test.pattern.factory.command.Light;
import tk.codecube.test.pattern.factory.command.LightOpenCommand;
import tk.codecube.test.pattern.factory.command.SimpleRemoteController;
import tk.codecube.test.pattern.observer.CurrentSubject;
import tk.codecube.test.pattern.observer.CurrentTimeDisplay;
import tk.codecube.test.pattern.observer.NextSubDisplay;
import tk.codecube.test.pattern.observer.Observer;
import tk.codecube.test.pattern.observer.internal.CurrentTime;
import tk.codecube.test.pattern.observer.internal.InternalSubject;
import tk.codecube.test.pattern.observer.internal.NextTime;

/**
 * @Description 设计模式测试用
 * @author jianlong.song bpqqop@163.com
 * @date 2016年8月16日 下午2:21:16
 */
public class PatternTest {

	/**
	 * @Description 观察者模式
	 * @author jianlong.song bpqqop@163.com
	 * @throws InterruptedException
	 * @date 2016年8月16日 下午2:21:30
	 */
	@Test
	public void testObserver() throws InterruptedException {
		CurrentSubject sub = new CurrentSubject();
		Observer co = new CurrentTimeDisplay(sub);
		Observer no = new NextSubDisplay(sub);

		System.out.println("===============start=================");
		for (int i = 0; i < 3; ++i) {
			sub.setTime(i + "");
			Thread.sleep(2000);
			sub.removeObserver(co);
		}
		System.out.println("===============end===================");
	}

	/**
	 * @Description 使用JDK提供的
	 * @author jianlong.song bpqqop@163.com
	 * @date 2016年8月16日 下午3:40:20
	 * @throws InterruptedException
	 */
	@Test
	public void testObserverInternal() throws InterruptedException {
		InternalSubject isub = new InternalSubject();

		CurrentTime ct = new CurrentTime(isub);
		NextTime nt = new NextTime(isub);
		for (int i = 0; i < 3; ++i) {
			isub.setTime(i + "");
			Thread.sleep(2000);
		}

	}

	/**
	 * @Description 装饰模式测试
	 * @author jianlong.song bpqqop@163.com
	 * @date 2016年8月17日 上午8:34:14
	 */
	@Test
	public void testDecorator() {
		Beverage b1 = new HouseBlend();
		Beverage b2 = new Espresso();
		b1 = new Milk(b1);
		b1 = new Mocha(b1);
		b1 = new Whip(b1);
		b2 = new Mocha(b2);
		b2 = new Mocha(b2);

		System.out.println(b1.getDescript() + " cost " + b1.cost());
		System.out.println(b2.getDescript() + " cost " + b2.cost());

	}

	/**
	 * @Description 自定义的inputStream
	 * @author jianlong.song bpqqop@163.com
	 * @date 2016年8月17日 上午11:11:32
	 * @throws IOException
	 */
	@Test
	public void testCustomInput() throws IOException {
		int c;
		LowerCaseInputStream lcis = new LowerCaseInputStream(
				new BufferedInputStream(new FileInputStream(
						"./src/test/resources/testData.data")));
		while ((c = lcis.read()) != -1) {
			System.out.println((char) c);
		}
		lcis.close();
	}

	/**
	 * @Description 测试工厂模式
	 * @author jianlong.song bpqqop@163.com
	 * @date 2016年8月17日 下午2:39:15
	 */
	@Test
	public void testPatternFactory() {
		NYPizzaStore nyStore = new NYPizzaStore();
		Pizza nyPiz = nyStore.orderPizza("cheese");
		ChiocagoPizzaStore chStore = new ChiocagoPizzaStore();
		Pizza chPiz = chStore.orderPizza("cheese");
	}

	/**
	 * @Description 测试工厂模式-重写
	 * @author jianlong.song bpqqop@163.com
	 * @date 2016年8月17日 下午3:46:45
	 */
	@Test
	public void testPatternFactoryNew() {
		tk.codecube.test.pattern.factory.re.NYPizzaStore nypizStore = new tk.codecube.test.pattern.factory.re.NYPizzaStore();
		tk.codecube.test.pattern.factory.re.Pizza piz = nypizStore
				.orderPizza("cheese");
		tk.codecube.test.pattern.factory.re.ChicagoPizzaStore chpizStore = new tk.codecube.test.pattern.factory.re.ChicagoPizzaStore();
		tk.codecube.test.pattern.factory.re.Pizza piz2 = chpizStore
				.orderPizza("cheese");
	}

	/**
	 * @Description 命令模式
	 * @author jianlong.song bpqqop@163.com
	 * @date 2016年8月18日 下午2:31:03
	 */
	@Test
	public void testPatternCommand() {
		SimpleRemoteController src = new SimpleRemoteController();

		Light l = new Light();
		LightOpenCommand loc = new LightOpenCommand(l);

		Door d = new Door();
		GarageDoorOpenCommand gdoc = new GarageDoorOpenCommand(d);

		CeilingFan cf = new CeilingFan("电风扇~");
		CeilingFanCommand cfc = new CeilingFanCommand(cf);

		src.addDoSomebody(0, loc);
		src.addDoSomebody(1, gdoc);
		src.addDoSomebody(2, cfc);

		src.doButtonPressed(0);
		src.doButtonPressed(1);

		src.resetButternPressed();
		src.resetButternPressed();

		src.doButtonPressed(2);
		src.doButtonPressed(2);
		src.doButtonPressed(2);
		src.doButtonPressed(2);
		src.doButtonPressed(2);
		src.resetButternPressed();
	}

	/**
	 * @Description 组合模式测试
	 * @author jianlong.song bpqqop@163.com
	 * @date 2016年8月22日 下午2:56:22
	 */
	@Test
	public void testComponent() {
		MenuComponent pancakeHouseMenu = new Menu("PANCAKE HOUSE MENU",
				"Breakfast");
		MenuComponent dinnerMenu = new Menu("DINER MENU", "Dinner");
		MenuComponent cafeMenu = new Menu("CAFE MENU", "Lunch");
		
		MenuComponent dessertMenu = new Menu("DESSERT MENU",
				"Dessert of course!");

		MenuComponent allMenus = new Menu("ALL MENUS", "All menus combine!");

		allMenus.add(pancakeHouseMenu);
		allMenus.add(dinnerMenu);
		allMenus.add(cafeMenu);

		dinnerMenu
				.add(new MenuItem(
						"Pasta",
						"Spaghetti with Marinara Sauce, and a slice of sourdough bread",
						true, 3.89));
		dinnerMenu.add(dessertMenu);
		
		dessertMenu.add(new MenuItem("Apple Pie",
				"Apple pie with a flakey crust,topped with vanilla ice cream",
				true, 1.59));
		
		Waitress waitress = new Waitress(allMenus);
		
//		waitress.printMenu();
		waitress.printVegetarianMenu();
		
	}

}
