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
                    queue = new ArrayBlockingQueue<Map<String,String>>(10);
                }
            }
        }
        return queue;
    }
    
}

class OverrideQueue<E> extends ArrayBlockingQueue<E>{

    private int capacity;
    private int count;
    
    public OverrideQueue(int capacity) {
        super(capacity);
        this.capacity = capacity;
    }
    
    @Override
    public boolean offer(E e) {
        
        return super.offer(e);
    }
}