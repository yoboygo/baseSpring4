/**
 * ForumServiceImpl.java
 * 
 * Aimy
 * 上午11:05:05
 */
package tk.codecube.test.aop;


/**
 * @author Aimy
 * 2014年10月11日 上午11:05:05
 */
public class ForumServiceImpl implements ForumService{

	/* (non-Javadoc)
	 * @see tk.codecube.test.aop.ForumService#removeTopic(int)
	 */
	@Override
	public void removeTopic(int topicId) {
//		PerformanceMonitor.begin("tk.codecube.test.aop.ForumServiceImpl.removeTopic");
		System.out.println("模拟删除Topic记录："+topicId);
		try{
		//	Thread.currentThread();
			Thread.sleep(20);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
//		PerformanceMonitor.end();
	}

	/* (non-Javadoc)
	 * @see tk.codecube.test.aop.ForumService#reoveForum(int)
	 */
	@Override
	public void removeForum(int forumId) {
//		PerformanceMonitor.begin("tk.codecube.test.aop.ForumServiceImpl.removeForum");
		System.out.println("模拟删除Forum记录: "+forumId);
		try{
	//		Thread.currentThread();
			Thread.sleep(40);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
//		PerformanceMonitor.end();
	}

	
}
