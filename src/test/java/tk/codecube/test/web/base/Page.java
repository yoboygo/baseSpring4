/**
 * Page.java
 * 
 * Aimy
 * 下午3:54:08
 */
package tk.codecube.test.web.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页所用的实体
 * @author Aimy
 * 2015年2月9日 下午3:54:08
 */
public class Page implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 312163587840730430L;
	
	/**
	 * 默认每页的记录数
	 */
	private static int DEFAULT_PAGE_SIZE = 20;
	
	/**
	 * 当前设置的记录数
	 */
	private int pageSize = DEFAULT_PAGE_SIZE;
	
	/**
	 * 本页的第一条记录在结果集List中的位置
	 */
	private long start;
	
	/**
	 * 结果集
	 */
	private List<?> data;
	
	/**
	 * 总记录数
	 */
	private long totalSize;
	

	/**
	 * 带参数的构造函数
	 * @param pageSize
	 * @param start
	 * @param totalSize
	 * @param data
	 */
	public Page(int pageSize,long start,long totalSize,List<?> data)
	{
		this.pageSize = pageSize;
		this.start = start;
		this.totalSize = totalSize;
		this.data = data;
		this.totalSize = data.size();
	}
	
	/**
	 * 无参的构造函数
	 */
	public Page()
	{
		this(DEFAULT_PAGE_SIZE, 0, 0, new ArrayList<String>());
	}
	
	/**
	 *  获取总页数
	 * @author Aimy
	 * @return
	 * 2015年2月9日 下午4:05:56
	 */
	public long getPageCount()
	{
		if(totalSize%pageSize == 0)
		{
			return totalSize/pageSize;
		}else{
			return totalSize/pageSize+1;
		}
	}
	
	/**
	 * 返回当前的记录是第几页 
	 * @author Aimy
	 * @return
	 * 2015年2月9日 下午4:09:02
	 */
	public long getCurrentPageNo()
	{
		if(start%pageSize == 0)
		{
			return start/pageSize;
		}else{
			return start/pageSize+1;
		}
	}
	
	/**
	 *  判断是否还有下一页
	 * @author Aimy
	 * @return
	 * 2015年2月9日 下午4:12:00
	 */
	public boolean hasNextPage()
	{
		/*
		if(start+pageSize >= totalSize)
			return false;
		return true;
		*/
		
		return this.getCurrentPageNo() < this.getPageCount();
	}
	
	
	/**
	 *  判断是否有上一页
	 * @author Aimy
	 * @return
	 * 2015年3月3日 上午9:34:19
	 */
	public boolean hasPrePage()
	{
		return this.getCurrentPageNo() > 0;
	}
	
	/**
	 *  返回任意一页在List中的位置
	 * @author Aimy
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * 2015年3月3日 上午9:35:52
	 */
	public static long getStartOfPage(long pageNo,int pageSize)
	{
		return (pageNo-1) * pageSize + 1;
	}
	
	public static long getStartOfPage(long pageNo)
	{
		return getStartOfPage(pageNo,DEFAULT_PAGE_SIZE);
	}

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}
	
	
}
