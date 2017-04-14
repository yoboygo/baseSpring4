package tk.codecube.test.queue;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class TradeInfoQueue {
    
    private static Queue<Map<String,String>> queue;
    
    private TradeInfoQueue(){
        
    }
    
    public static Queue<Map<String,String>> instance(){
        if(queue == null){
            
            synchronized (TradeInfoQueue.class) {
                if(queue == null){
//                    queue = new LinkedBlockingQueue<Map<String,String>>();
                    queue = new OverrideQueue<Map<String,String>>(10);
                }
            }
        }
        return queue;
    }
    
}

/**
 * @dec 超出队列的限制之后，自动弹出队列头
 * @author songjl 2017年4月14日 下午2:08:54
 *
 * @param <E>
 */
class OverrideQueue<E> extends ArrayBlockingQueue<E>{

    /**
     * 
     */
    private static final long serialVersionUID = 6390794479597032143L;
    
    public OverrideQueue(int capacity) {
        super(capacity);
    }
    
    @Override
    public boolean add(E e) {
        boolean flag = offer(e);
        if(!flag){
            System.out.println("-丢弃数据" + poll());
            return super.add(e);
        }
        return flag;
    }
}