package tk.codecube.test.rebuild.before;


public class Movie {
	
	private String title;
	private MovieType type;
	
	/**
	 * 押金
	 */
	private double price;
	private String despcriptioin;
	
	public Movie() {
	}
	
	public Movie(String title,MovieType type,double price) {
		this.title = title;
		this.type = type;
		this.price = price;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDespcriptioin() {
		return despcriptioin;
	}
	public void setDespcriptioin(String despcriptioin) {
		this.despcriptioin = despcriptioin;
	}
	public MovieType getType() {
		return type;
	}
	public void setType(MovieType type) {
		this.type = type;
	}

}
