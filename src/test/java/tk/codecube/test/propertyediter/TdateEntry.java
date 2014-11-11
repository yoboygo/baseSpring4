/**
 * TdateEntry.java
 * 
 * Aimy
 * 下午10:59:04
 */
package tk.codecube.test.propertyediter;

import java.util.Date;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * @author Aimy 2014年10月13日 下午10:59:04
 */
public class TdateEntry {
	
	private Date date;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String toString()
	{
		return ReflectionToStringBuilder.toString(this);
	}
}
