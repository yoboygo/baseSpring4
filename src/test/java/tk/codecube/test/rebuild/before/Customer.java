package tk.codecube.test.rebuild.before;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Customer {

	private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private String name;
	private List<Rentail> rentail;
	
	public Customer(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Rentail> getRentail() {
		if(rentail == null)
		{
			rentail = new ArrayList<Rentail>();
		}
		return rentail;
	}
	public void setRentail(List<Rentail> rentail) {
		this.rentail = rentail;
	}

	/**
	 * 租借
	 * @param e
	 */
	public void addRentail(Rentail e)
	{
		this.getRentail().add(e);
	}
	
	/**
	 * 还
	 * @param e
	 */
	public void removeRentail(Rentail e)
	{
		this.getRentail().remove(e);
	}
	
	/**
	 * 打印订单详情
	 */
	public void printOrderDetail()
	{
		double charge = 0;
		System.out.println("===================Friendly "+this.getName()+"=========================");
		
		for(Rentail e:rentail)
		{
			System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
			System.out.println("Movie: "+e.getMovieTitle());
			System.out.println("Type: "+e.getMovieType());
			System.out.println("Price: "+e.getMoviePrice());
			System.out.println("Start: "+df.format(e.getStartDate().getTime()));
			System.out.println("End: "+df.format(e.getEndDate().getTime()));
			System.out.println("PerDay: "+e.getMoviePricePerDay());
			System.out.println("RentainDays: "+e.getRetailDays());
			System.out.println("RetainCost: "+e.getCharge());
			System.out.println("total:"+(e.getMoviePrice()+e.getCharge()));
			
			charge += e.getMoviePrice()+e.getCharge();
		}
		System.out.println("---------------------------------------totalCost:"+charge);
		System.out.println("=========================End=============================");
		System.out.println();
	}
}
