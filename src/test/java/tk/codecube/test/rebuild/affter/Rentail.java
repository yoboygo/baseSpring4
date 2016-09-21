package tk.codecube.test.rebuild.affter;

import java.util.Calendar;

public class Rentail {

	private Movie movie;
	private Calendar startDate;
	private Calendar endDate;

	public static Rentail builder()
	{
		return new Rentail();
	}
	public Rentail buildMovie(Movie m)
	{
		this.movie = m;
		return this;
	}
	public Rentail buildStartDay(Calendar c)
	{
		this.startDate = c;
		return this;
	}
	public Rentail buildEndDay(Calendar c)
	{
		this.endDate = c;
		return this;
	}
	
	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Calendar getStartDate() {
		return startDate;
	}

	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}

	public Calendar getEndDate() {
		return endDate;
	}

	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}

	/**
	 * 返回押金
	 * 
	 * @return
	 */
	public double getMovieBaseCharge() {
		return this.movie.getBaseCharge();
	}

	public String getMovieTitle() {
		return this.movie.getTitle();
	}

	public MovieType getMovieType() {
		return this.movie.getType();
	}

	/**
	 * 每天的租金
	 * 
	 * @return
	 */
	public double getMovieChargePerDay() {
		return this.movie.getPrice().getChargePerDay();
	}

	/**
	 * 计算租借天数
	 * @return
	 */
	public int getRetailDays()
	{
		return this.endDate.get(Calendar.DAY_OF_YEAR)
				- this.startDate.get(Calendar.DAY_OF_YEAR);
	}
	
	/**
	 * 计算租金
	 * 每天的租金  * 天数
	 * @return
	 */
	public double getRentalCharge() {
		return this.movie.getPrice().getRentalCharge(getRetailDays());
	}

}
